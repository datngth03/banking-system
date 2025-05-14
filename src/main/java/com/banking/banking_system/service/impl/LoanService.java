package com.banking.banking_system.service.impl;

import com.banking.banking_system.model.LoanPaymentSchedule;
import com.banking.banking_system.dto.request.LoanRequest;
import com.banking.banking_system.entity.Loan;
import com.banking.banking_system.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public Loan createLoan(LoanRequest loanRequest) {
        Loan loan = new Loan();
        loan.setCustomerId(loanRequest.getCustomerId());
        loan.setLoanType(loanRequest.getLoanType());
        loan.setAmount(loanRequest.getAmount());
        loan.setInterestRate(loanRequest.getInterestRate());
        loan.setStartDate(loanRequest.getStartDate());
        loan.setEndDate(loanRequest.getEndDate());
        loan.setStatus("ACTIVE");
        loan.setCreatedAt(LocalDateTime.now());
        loan.setUpdatedAt(LocalDateTime.now());

        return loanRepository.save(loan);
    }


    public List<LoanPaymentSchedule> generatePaymentSchedule(Loan loan) {

        BigDecimal emi = calculateEMI(loan.getAmount(), loan.getInterestRate(), loan.getEndDate().getMonthValue());
        List<LoanPaymentSchedule> paymentSchedules = new ArrayList<>();

        LocalDate currentDate = loan.getStartDate();

        while (currentDate.isBefore(loan.getEndDate())) {
            LoanPaymentSchedule schedule = new LoanPaymentSchedule(currentDate, emi);
            paymentSchedules.add(schedule);
            currentDate = currentDate.plusMonths(1);
        }
        return paymentSchedules;
    }

    private BigDecimal calculateEMI(BigDecimal principal, float annualInterestRate, int months) {
        float monthlyInterestRate = annualInterestRate / 12 / 100;
        BigDecimal emi = principal.multiply(BigDecimal.valueOf(monthlyInterestRate))
                .multiply(BigDecimal.valueOf(Math.pow(1 + monthlyInterestRate, months)))
                .divide(BigDecimal.valueOf(Math.pow(1 + monthlyInterestRate, months) - 1), RoundingMode.HALF_UP);
        return emi;
    }

    public List<Loan> getActiveLoans(Long customerId) {
        return loanRepository.findByCustomerIdAndStatus(customerId, "ACTIVE");
    }

    public Loan getLoanById(Long loanId) {
        return loanRepository.findById(loanId).orElse(null);
    }
}
