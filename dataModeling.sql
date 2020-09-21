DROP TABLE code;
DROP TABLE Gathering;
DROP TABLE Member;
DROP TABLE Dues;
DROP TABLE Transaction;
DROP TABLE Account;

/**********************************/
/* Table Name: Account */
/**********************************/
CREATE TABLE Account(
		account_no VARCHAR(14) NOT NULL,
		holder VARCHAR(20) NOT NULL,
		balance NUMERIC(12) NOT NULL,
		type CHAR(3) NOT NULL,
		safe_account_no VARCHAR(15) NOT NULL,
		status CHAR(3) NOT DEFAULT '201'
);

/**********************************/
/* Table Name: Transaction */
/**********************************/
CREATE TABLE Transaction(
		transaction_no NUMERIC(10) NOT NULL,
		reg_date DATE NOT NULL,
		classification CHAR(2) NOT NULL,
		amount NUMERIC(12) NOT NULL,
		balance NUMERIC(12) NOT NULL,
		account_no VARCHAR(14) NOT NULL,
		counterpart VARCHAR(50) NOT NULL,
		memo VARCHAR(45),
		counterpart_account_no VARCHAR(15),
		counterpart_bank VARCHAR(30)
);

/**********************************/
/* Table Name: Dues */
/**********************************/
CREATE TABLE Dues(
		transaction_no NUMERIC(10) NOT NULL,
		reg_date DATE NOT NULL,
		account_no VARCHAR(14) NOT NULL,
		amount NUMERIC(12) NOT NULL,
		counterpart VARCHAR(50) NOT NULL,
		member VARCHAR(40)
);

/**********************************/
/* Table Name: Member */
/**********************************/
CREATE TABLE Member(
		id VARCHAR(20) NOT NULL,
		password VARCHAR(20) NOT NULL,
		name VARCHAR(20) NOT NULL,
		tel VARCHAR(12),
		email VARCHAR(50),
		addr VARCHAR(400)
);

/**********************************/
/* Table Name: Gathering */
/**********************************/
CREATE TABLE Gathering(
		account_no VARCHAR(14) NOT NULL,
		id VARCHAR(20) NOT NULL,
		type CHAR(3) NOT NULL
);

/**********************************/
/* Table Name: code */
/**********************************/
CREATE TABLE code(
		code CHAR(3) NOT NULL,
		code_name VARCHAR(30) NOT NULL,
		code_type VARCHAR(30) NOT NULL
);

ALTER TABLE Account ADD CONSTRAINT IDX_Account_PK PRIMARY KEY (account_no);

ALTER TABLE Transaction ADD CONSTRAINT IDX_Transaction_PK PRIMARY KEY (transaction_no);
ALTER TABLE Transaction ADD CONSTRAINT IDX_Transaction_FK0 FOREIGN KEY (account_no) REFERENCES Account (account_no);

ALTER TABLE Dues ADD CONSTRAINT IDX_Dues_PK PRIMARY KEY (transaction_no);
ALTER TABLE Dues ADD CONSTRAINT IDX_Dues_FK0 FOREIGN KEY (transaction_no) REFERENCES Transaction (transaction_no);
ALTER TABLE Dues ADD CONSTRAINT IDX_Dues_FK1 FOREIGN KEY (account_no) REFERENCES Account (account_no);

ALTER TABLE Member ADD CONSTRAINT IDX_Member_PK PRIMARY KEY (id);

ALTER TABLE Gathering ADD CONSTRAINT IDX_Gathering_PK PRIMARY KEY (account_no, id);
ALTER TABLE Gathering ADD CONSTRAINT IDX_Gathering_FK0 FOREIGN KEY (id) REFERENCES Member (id);
ALTER TABLE Gathering ADD CONSTRAINT IDX_Gathering_FK1 FOREIGN KEY (account_no) REFERENCES Account (account_no);

ALTER TABLE code ADD CONSTRAINT IDX_code_PK PRIMARY KEY (code);

SELECT * FROM code;
SELECT * FROM Gathering;
SELECT * FROM Member;
SELECT * FROM Dues;
SELECT * FROM Transaction;
SELECT * FROM Account;
