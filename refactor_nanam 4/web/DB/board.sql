
    
CREATE TABLE board (
    qseq    NUMBER(5) PRIMARY KEY,
    subject VARCHAR2(1000),
    content VARCHAR2(1000),
    id      VARCHAR2(20),
    rep     CHAR(1) DEFAULT 'N', 
    adjustIndate  DATE    DEFAULT SYSDATE
);

create SEQUNCE qseq start with 1;