
CREATE SEQUENCE SQ_LOG_VALUES
INCREMENT BY 1
START WITH 1
 MAXVALUE 999999
 MINVALUE 1
 CACHE 20
/

/*==============================================================*/
/* Table: LOG                                                */
/*==============================================================*/
CREATE TABLE LOG_VALUES 
(
   ID                   NUMBER(8)            NOT NULL,
   MESSAGE              VARCHAR2(100)            NOT NULL,
   MESSAGE_TYPE          VARCHAR2(1)         NOT NULL
)
/

ALTER TABLE LOG_VALUES
   ADD CONSTRAINT PK_LOG_VALUES PRIMARY KEY (ID)
/



CREATE TRIGGER TIB_LOG_VALUES BEFORE INSERT
ON LOG_VALUES FOR EACH ROW
DECLARE
    INTEGRITY_ERROR  EXCEPTION;
    ERRNO            INTEGER;
    ERRMSG           CHAR(200);
    DUMMY            INTEGER;
    FOUND            BOOLEAN;

BEGIN
    --  COLUMN "ID" USES SEQUENCE SQ_LOG_VALUES
    SELECT SQ_LOG_VALUES.NEXTVAL INTO :NEW.ID FROM DUAL;

--  ERRORS HANDLING
EXCEPTION
    WHEN INTEGRITY_ERROR THEN
       RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
/



