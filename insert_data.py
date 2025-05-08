# import crypt
import bcrypt
import pandas as pd
from sqlalchemy import create_engine, Table, MetaData
from sqlalchemy.orm import sessionmaker
from sqlalchemy import text
from datetime import datetime, date, timedelta
import random

# URL của file CSV trên GitHub (sử dụng link raw)


# Cấu hình kết nối cơ sở dữ liệu
DATABASE_URI = 'postgresql://postgres:datnt@localhost:5432/banking_db'

# Kết nối đến cơ sở dữ liệu PostgreSQL
engine = create_engine(DATABASE_URI)
Session = sessionmaker(bind=engine)
session = Session()

# Đọc dữ liệu từ file CSV
csv_file = "Comprehensive_Banking_Database.csv"
df = pd.read_csv(csv_file)


def insert_customer_data(row):
    # Ép phone về dạng chuỗi để so sánh và insert đúng cách
    phone = str(row['Contact Number'])

    # Kiểm tra xem email và phone đã tồn tại chưa
    existing_customer = session.execute(
        text("SELECT 1 FROM customers WHERE email = :email OR phone = :phone LIMIT 1"),
        {'email': row['Email'], 'phone': phone}
    ).fetchone()

    if existing_customer:
        return

    # Dữ liệu khách hàng cần insert
    customer_data = {
        'identityNumber': generate_identity_number(row['Customer ID']),
        'full_name': f"{row['First Name']} {row['Last Name']}",
        'email': row['Email'],
        'phone': row['Contact Number'],
        'address': row['Address'],
        'username': row['First Name'].lower(),
        'password_hash': hash_password(f"{row['First Name']} {row['Last Name']}"),
        'date_of_birth': '1980-01-01',
        'status': 'ACTIVE',
        'created_at': datetime.now(),
        'updated_at': datetime.now()
    }

    # Chèn dữ liệu
    session.execute(text("""
        INSERT INTO customers (identity_number, full_name, email, phone, address, username, password_hash, date_of_birth, status, created_at, updated_at)
        VALUES (:identityNumber, :full_name, :email, :phone, :address, :username, :password_hash, :date_of_birth, :status, :created_at, :updated_at)
    """), customer_data)

    # Lưu thay đổi
    session.commit()


def insert_account_data(row):
    # Chèn vào bảng accounts
    account_data = {
        'customer_id': random.randint(1, 2139),
        # Placeholder cho account_number
        'account_number': f"ACC{random.randint(10000000, 99999999)}",
        'account_type': row['Account Type'],
        'balance': row['Account Balance'],
        'status': 'ACTIVE',
        'created_at': datetime.now(),
        'updated_at': datetime.now()
    }
    session.execute(
        text('''
            INSERT INTO accounts (
                customer_id, account_number, account_type, balance, status, created_at, updated_at
            ) VALUES (
                :customer_id, :account_number, :account_type, :balance, :status, :created_at, :updated_at
            )
        '''), account_data
    )


def insert_transaction_data(row):
    # Chèn vào bảng transactions
    transaction_data = {
        # Placeholder cho transaction_code
        'transaction_code': f"TXN{random.randint(100000, 999999)}{int(row['TransactionID']):04d}",
        'from_account_id': random.randint(1, 5000) if row['Transaction Type'] != 'Deposit' else None,
        'to_account_id': random.randint(1, 5000) if row['Transaction Type'] != 'Withdrawal' else None,
        'type': row['Transaction Type'],
        'amount': row['Transaction Amount'],
        'description': row['Anomaly'],  # Có thể thêm mô tả nếu có
        'status': 'SUCCESS',
        'timestamp': row['Transaction Date'],
        'created_at': datetime.now(),
        'updated_at': datetime.now()
    }
    session.execute(
        text('''
            INSERT INTO transactions (
                transaction_code, from_account_id, to_account_id, type, amount, description, status, timestamp, created_at, updated_at
            )   VALUES (
                :transaction_code, :from_account_id, :to_account_id, :type, :amount, :description, :status, :timestamp, :created_at, :updated_at
            )        
        '''), transaction_data
    )


def insert_loan_data(row, index):
    # Chèn vào bảng loans
    if index < 2139:
        loan_status = 'Rejected'
    elif index < 4278:
        loan_status = 'Approved'
    else:
        loan_status = 'Closed'
    loan_data = {
        'customer_id': (index % 2139) + 1,
        'loan_type': row['Loan Type'],
        'amount': row['Loan Amount'],
        'interest_rate': row['Interest Rate'],
        'start_date': row['Approval/Rejection Date'],
        'end_date': '2026-01-01',
        'status': loan_status,
        'created_at': datetime.now(),
        'updated_at': datetime.now()
    }
    session.execute(
        text('''
            INSERT INTO loans (
                customer_id, loan_type, amount, interest_rate, start_date, end_date, status, created_at, updated_at
            ) VALUES (
                :customer_id, :loan_type, :amount, :interest_rate, :start_date, :end_date, :status, :created_at, :updated_at
            )
        '''), loan_data
    )


generated_card_numbers = set()


def generate_unique_card_number(card_type):
    if card_type == "Visa":
        prefix = "4"
        length = 16
    elif card_type == "MasterCard":
        prefix = str(random.choice([51, 52, 53, 54, 55]))
        length = 16
    elif card_type == "AMEX":
        prefix = str(random.choice([34, 37]))
        length = 15
    else:
        prefix = "9"  # default for unknown
        length = 16

    remaining_length = length - len(prefix)
    while True:
        card_number = prefix + \
            ''.join([str(random.randint(0, 9))
                    for _ in range(remaining_length)])
        if card_number not in generated_card_numbers:
            generated_card_numbers.add(card_number)
            return card_number


def insert_card_data(row, index):
    expiry_date = date.today().replace(
        day=1) + timedelta(days=random.randint(365, 1825))  # từ 1 đến 5 năm
    card_data = {
        'account_id': index+1,
        'card_type': row['Card Type'],
        'card_number': generate_unique_card_number(row['Card Type']),
        'card_identifier': f"CID{random.randint(1000, 9999)}",
        'expiry_date': expiry_date,
        'cvv': f"{random.randint(100, 999)}",  # CVV 3 chữ số
        'status': 'ACTIVE',
        'created_at': datetime.now(),
        'updated_at': datetime.now()
    }

    session.execute(
        text('''
            INSERT INTO cards (
                account_id, card_type, card_number, card_identifier, expiry_date,
                cvv, status, created_at, updated_at
            ) VALUES (
                :account_id, :card_type, :card_number, :card_identifier, :expiry_date,
                :cvv, :status, :created_at, :updated_at
            )
        '''), card_data
    )


def generate_identity_number(customer_id):
    # Tạo 8 số ngẫu nhiên cho phần đầu
    random_part = random.randint(10000000, 99999999)
    # Đảm bảo có 4 chữ số, bổ sung số 0 nếu cần
    last_part = str(customer_id).zfill(4)

    # Kết hợp lại thành identityNumber
    return f"{random_part}{last_part}"


def hash_password(full_name):
    # Chuyển đổi full_name thành byte
    password_bytes = full_name.encode('utf-8')

    # Tạo salt
    salt = bcrypt.gensalt()

    # Hash mật khẩu bằng thuật toán bcrypt
    hashed_password = bcrypt.hashpw(password_bytes, salt)

    # Trả về mật khẩu đã được hash dưới dạng chuỗi
    return hashed_password.decode('utf-8')


# Xử lý các bản ghi từ CSV
for index, row in df.iterrows():
    # Thêm customer
    insert_customer_data(row)
    session.commit()
    # Thêm account
    insert_account_data(row)
    session.commit()
    # Thêm transaction
    insert_transaction_data(row)
    session.commit()
    # Thêm loan (nếu có)
    insert_loan_data(row, index)
    session.commit()
    # Thêm card (nếu có)
    insert_card_data(row, index)
    session.commit()

# Đóng session
session.close()
