CREATE OR REPLACE TRIGGER TRG_ADD_TRANSACTION
AFTER
INSERT ON TRANSACTION
FOR EACH ROW 
DECLARE acnt_no	VARCHAR2(14);		
BEGIN
	IF :NEW.classification = '401' THEN
        SELECT ACCOUNT_NO INTO acnt_no 
        FROM PARTICIPANT 
        WHERE ACCOUNT_NO = :NEW.ACCOUNT_NO AND NAME = :NEW.COUNTERPART 
            OR NAME LIKE '%'||DECODE(:NEW.INDICATION, NULL, 'A', :NEW.INDICATION)||'%';
   END IF;

    IF acnt_no IS NOT NULL THEN
        INSERT INTO DUES VALUES(:NEW.TRANSACTION_NO, :NEW.TIME, :NEW.ACCOUNT_NO, :NEW.AMOUNT, :NEW.COUNTERPART, DECODE(:NEW.INDICATION, NULL, :NEW.COUNTERPART, :NEW.INDICATION));
    END IF;
   
END;
/