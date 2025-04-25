package com.banking.banking_system.entity;

@Entity
@Table(name = "loans")
public class Loan {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private String loanType;
    private BigDecimal amount;
    private Float interestRate;

    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // getters/setters
}

