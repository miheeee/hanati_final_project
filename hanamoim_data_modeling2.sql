DROP TABLE Authentication;
DROP TABLE Scheduled_deposit_date;
DROP TABLE Gathering;
DROP TABLE Scheduled_end;
DROP TABLE code;
DROP TABLE Participant;
DROP TABLE Transaction;
DROP TABLE Account;
DROP TABLE Member;

/**********************************/
/* Table Name: Member */
/**********************************/
CREATE TABLE Member(
      id VARCHAR(20) NOT NULL,
      password VARCHAR(20) NOT NULL,
      name VARCHAR(20) NOT NULL,
      tel VARCHAR(12) NOT NULL,
      email VARCHAR(50) NOT NULL,
      addr VARCHAR(400) NOT NULL
);

/**********************************/
/* Table Name: Account */
/**********************************/
CREATE TABLE Account(
      account_no VARCHAR(14) NOT NULL,
      password VARCHAR(30) NOT NULL,
      holder VARCHAR(20) NOT NULL,
      balance NUMERIC(12) DEFAULT 0,
      product_name VARCHAR(40) NOT NULL,
      id VARCHAR(20) NOT NULL
);

/**********************************/
/* Table Name: Transaction */
/**********************************/
CREATE TABLE Transaction(
      transaction_no NUMERIC(10) NOT NULL,
      time DATE DEFAULT sysdate NOT NULL,
      classification VARCHAR(30) NOT NULL,
      amount NUMERIC(12) NOT NULL,
      balance NUMERIC(12) NOT NULL,
      account_no VARCHAR(14) NOT NULL,
      indication VARCHAR(40),
      counterpart VARCHAR(50),
      counterpart_account_no VARCHAR(15),
      counterpart_bank VARCHAR(30),
      paid_member VARCHAR(30),
      memo VARCHAR(45)
);

/**********************************/
/* Table Name: Participant */
/**********************************/
CREATE TABLE Participant(
      account_no VARCHAR(14) NOT NULL,
      id VARCHAR(20) NOT NULL,
      type CHAR(3) NOT NULL,
      name VARCHAR(30) NOT NULL,
      account_change VARCHAR(1) DEFAULT 'Y',
      transfer VARCHAR(1) DEFAULT 'N',
      deposit_date VARCHAR(1) DEFAULT 'N',
      register_date DATE DEFAULT sysdate
);

/**********************************/
/* Table Name: code */
/**********************************/
CREATE TABLE code(
      code CHAR(3) NOT NULL,
      code_name VARCHAR(30) NOT NULL,
      code_type VARCHAR(30) NOT NULL
);

/**********************************/
/* Table Name: ScheduledEnd */
/**********************************/
CREATE TABLE Scheduled_end(
      account_no VARCHAR(14) NOT NULL,
      end_date DATE NOT NULL
);

/**********************************/
/* Table Name: Gathering */
/**********************************/
CREATE TABLE Gathering(
      safe_account_no VARCHAR(15) NOT NULL,
      account_no VARCHAR(14) NOT NULL,
      name VARCHAR(40) NOT NULL,
      password VARCHAR(30) NOT NULL,
      balance NUMERIC(12) DEFAULT 0 NOT NULL,
      tagS NUMERIC(2) NOT NULL,
      tagL NUMERIC(2) NOT NULL,
      start_date DATE DEFAULT sysdate,
      id VARCHAR(20) NOT NULL
);

/**********************************/
/* Table Name: ScheduledDepositDate */
/**********************************/
CREATE TABLE Scheduled_deposit_date(
      account_no VARCHAR(14) NOT NULL,
      id VARCHAR(20) NOT NULL,
      regular_day NUMERIC(2) NOT NULL
);

/**********************************/
/* Table Name: Authentication */
/**********************************/
CREATE TABLE Authentication(
      safe_account_no VARCHAR(15),
      Authentication_num NUMERIC(6)
);


ALTER TABLE Member ADD CONSTRAINT IDX_Member_PK PRIMARY KEY (id);

ALTER TABLE Account ADD CONSTRAINT IDX_Account_PK PRIMARY KEY (account_no);
ALTER TABLE Account ADD CONSTRAINT IDX_Account_FK0 FOREIGN KEY (id) REFERENCES Member (id);

ALTER TABLE Transaction ADD CONSTRAINT IDX_Transaction_PK PRIMARY KEY (transaction_no);
ALTER TABLE Transaction ADD CONSTRAINT IDX_Transaction_FK0 FOREIGN KEY (account_no) REFERENCES Account (account_no);

ALTER TABLE Participant ADD CONSTRAINT IDX_Participant_PK PRIMARY KEY (account_no, id);
ALTER TABLE Participant ADD CONSTRAINT IDX_Participant_FK0 FOREIGN KEY (id) REFERENCES Member (id);
ALTER TABLE Participant ADD CONSTRAINT IDX_Participant_FK1 FOREIGN KEY (account_no) REFERENCES Account (account_no);

ALTER TABLE code ADD CONSTRAINT IDX_code_PK PRIMARY KEY (code);

ALTER TABLE Scheduled_end ADD CONSTRAINT IDX_Scheduled_end_PK PRIMARY KEY (account_no);
ALTER TABLE Scheduled_end ADD CONSTRAINT IDX_Scheduled_end_FK0 FOREIGN KEY (account_no) REFERENCES Account (account_no);

ALTER TABLE Gathering ADD CONSTRAINT IDX_Gathering_PK PRIMARY KEY (safe_account_no);
ALTER TABLE Gathering ADD CONSTRAINT IDX_Gathering_FK0 FOREIGN KEY (account_no) REFERENCES Account (account_no);
ALTER TABLE Gathering ADD CONSTRAINT IDX_Gathering_FK1 FOREIGN KEY (id) REFERENCES Member (id);

ALTER TABLE Scheduled_deposit_date ADD CONSTRAINT IDX_Scheduled_deposit_date_PK PRIMARY KEY (account_no, id);
ALTER TABLE Scheduled_deposit_date ADD CONSTRAINT IDX_Scheduled_deposit_date_FK0 FOREIGN KEY (account_no) REFERENCES Account (account_no);
ALTER TABLE Scheduled_deposit_date ADD CONSTRAINT IDX_Scheduled_deposit_date_FK1 FOREIGN KEY (id) REFERENCES Member (id);

ALTER TABLE Authentication ADD CONSTRAINT IDX_Authentication_PK PRIMARY KEY (safe_account_no, Authentication_num);
ALTER TABLE Authentication ADD CONSTRAINT IDX_Authentication_FK0 FOREIGN KEY (safe_account_no) REFERENCES Gathering (safe_account_no);
