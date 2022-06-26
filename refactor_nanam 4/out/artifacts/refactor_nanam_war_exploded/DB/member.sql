
  CREATE TABLE member(
    name VARCHAR2(20) not null,
	id VARCHAR2(20) PRIMARY key,
	pwd VARCHAR2(20) not null,
    pwdFind VARCHAR2(20) not null,
	email VARCHAR2(40),
	phone VARCHAR2(20) not null,
    zipcode VARCHAR2(20),
    detailAddress VARCHAR2(100),
    address VARCHAR2(100),
	adjustIndate DATE DEFAULT SYSDATE
);

commit;
    
    drop table member;