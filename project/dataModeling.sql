DROP TABLE Scheduled_termination;
DROP TABLE code;
DROP TABLE Participant;
DROP TABLE Dues;
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
		tel VARCHAR(12),
		email VARCHAR(50),
		addr VARCHAR(400)
);

/**********************************/
/* Table Name: Account */
/**********************************/
CREATE TABLE Account(
		account_no VARCHAR(14) NOT NULL,
		holder VARCHAR(20) NOT NULL,
		balance NUMERIC(12) NOT NULL,
		type CHAR(3) NOT NULL,
		safe_account_no VARCHAR(15),
		status CHAR(3) DEFAULT 201,
		alias VARCHAR(40),
		id VARCHAR(20) NOT NULL
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
/* Table Name: Participant */
/**********************************/
CREATE TABLE Participant(
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

/**********************************/
/* Table Name: Scheduled_termination */
/**********************************/
CREATE TABLE Scheduled_termination(
		account_no VARCHAR(14) NOT NULL,
		due_date DATE NOT NULL
);


ALTER TABLE Member ADD CONSTRAINT IDX_Member_PK PRIMARY KEY (id);

ALTER TABLE Account ADD CONSTRAINT IDX_Account_PK PRIMARY KEY (account_no);
ALTER TABLE Account ADD CONSTRAINT IDX_Account_FK0 FOREIGN KEY (id) REFERENCES Member (id);

ALTER TABLE Transaction ADD CONSTRAINT IDX_Transaction_PK PRIMARY KEY (transaction_no);
ALTER TABLE Transaction ADD CONSTRAINT IDX_Transaction_FK0 FOREIGN KEY (account_no) REFERENCES Account (account_no);

ALTER TABLE Dues ADD CONSTRAINT IDX_Dues_PK PRIMARY KEY (transaction_no);
ALTER TABLE Dues ADD CONSTRAINT IDX_Dues_FK0 FOREIGN KEY (transaction_no) REFERENCES Transaction (transaction_no);
ALTER TABLE Dues ADD CONSTRAINT IDX_Dues_FK1 FOREIGN KEY (account_no) REFERENCES Account (account_no);

ALTER TABLE Participant ADD CONSTRAINT IDX_Participant_PK PRIMARY KEY (account_no, id);
ALTER TABLE Participant ADD CONSTRAINT IDX_Participant_FK0 FOREIGN KEY (id) REFERENCES Member (id);
ALTER TABLE Participant ADD CONSTRAINT IDX_Participant_FK1 FOREIGN KEY (account_no) REFERENCES Account (account_no);

ALTER TABLE code ADD CONSTRAINT IDX_code_PK PRIMARY KEY (code);

ALTER TABLE Scheduled_termination ADD CONSTRAINT IDX_Scheduled_termination_PK PRIMARY KEY (account_no);
ALTER TABLE Scheduled_termination ADD CONSTRAINT IDX_Scheduled_termination_FK0 FOREIGN KEY (account_no) REFERENCES Account (account_no);

commit;



select sysdate from dual;
select sysdate+3 from dual;

		insert into scheduled_termination
		values('41291062112345', sysdate+3);

select * from scheduled_termination;
delete from scheduled_termination;

--commit;
--
insert into member values('user', 'user', 'ÇÑ¹ÌÈñ', '01012345678', 'user@naver.com', '°æ±âµµ ±¤¸í½Ã Ã¶»ê3µ¿');
--insert into member values('hong', 'hong', 'È«±æµ¿', '01023456789', 'hong@naver.com', '°æ±âµµ ±¤¸í½Ã Ã¶»ê3µ¿');
--insert into member values('kim', 'kim', '±èÃ¶¼ö', '01045216512', 'kim@naver.com', '°æ±âµµ ±¤¸í½Ã Ã¶»ê1µ¿');
--
--select * from member;
--
--
select * from account;
--
--delete from participant where id='user';
--commit;
--select * from participant;
--
insert into account values('41291062112345', 'ÇÑ¹ÌÈñ', '5000', '102', '', '201', '', 'user');
--insert into account values('41291062167890', 'ÇÑ¹ÌÈñ', '20000', '102', '', '201', '', 'user');
--insert into account values('41291062521344', 'È«±æµ¿', '25000', '102', '', '201', '', 'hong');
--insert into account values('41291062732120', 'È«±æµ¿', '37000', '102', '', '201', '', 'hong');
--
--insert into account values('41291062167891', 'ÇÑ¹ÌÈñ', '20000', '102', '', '201', '', 'user');
--insert into account values('41291062167892', 'È«±æµ¿', '20000', '102', '', '201', '', 'hong');
--insert into account values('41291062167893', 'È«±æµ¿', '20000', '101', '79792135212', '201', 'È«¸ðÀÓ', 'hong');
--insert into account values('41291062167894', 'ÇÑ¹ÌÈñ', '20000', '101', '79792135213', '201', 'ÇÑ¸ðÀÓ', 'user');
--
--insert into gathering values('41291062167894', 'user', '301');
--insert into gathering values('41291062167893', 'user', '302');
--insert into gathering values('41291062167893', 'hong', '301');
--insert into gathering values('41291062167894', 'hong', '302');
--
--delete from account;
--
--select a.alias, a.balance, a.safe_account_no
--from account a
--where a.account_no in 
--    (select g.account_no
--     from gathering g
--     where id = 'user');
-- 
--
--commit;
--
--		select alias, balance, account_no
--		from account
--		where id = 'user' and type='101';
