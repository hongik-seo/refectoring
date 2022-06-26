
TRUNCATE table member;

SELECT * FROM member m LEFT OUTER JOIN point p ON m.id = p.pointid where m.id='hongcha89';

SELECT
    *
FROM member;

desc point;

delete from cart;

select* from cart;

update member set (allpoint) = (select point.initpoint from point where pointid='hongcha11' );





/*
insert all 
into member VALUES('name','id','pwd','pwdFind','email','phone','zipcod','detailaddress','address')
into point VALUES('pointid','pointphone',0,0)
SELECT *
from dual;
*/

INSERT INTO point(pointid,pointphone) SELECT member.id, member.phone FROM  member WHERE  member.id='hongcha89';


--免贸: https://sdevstudy.tistory.com/7 [.]





ROLLBACK;
    
Select * from member;

INSERT into member

DELETE From point;
commit;

desc member;

drop table book;

SELECT
    *
FROM member;

    drop table pointResult;
    CREATE TABLE pointResult AS SELECT * from member WHERE member.id is not null;
    
    Delete from point;
    
    commit;
    
    SELECT
        *
    FROM point;
    
 CREATE sequence mseq start WITH 1; 

    drop TABLE member;

  CREATE TABLE member(
    
    name VARCHAR2(20) not null,
	id VARCHAR2(20) not null primary key,
	pwd VARCHAR2(20) not null,
    pwdFind VARCHAR2(20) not null,
	email VARCHAR2(40),
	phone VARCHAR2(20) not null ,
    zipcode VARCHAR2(20),
    detailAddress VARCHAR2(100),
    address VARCHAR2(100),
	adjustIndate DATE DEFAULT SYSDATE,
    allpoint          INTEGER DEFAULT 0
);



alter table member add initPoint INTEGER DEFAULT 0 ;

DELETE from point;

commit;

desc point;

commit;

SELECT
    *
FROM member;



SELECT
    *
FROM point;

commit;

CREATE SEQUENCE pseq START WITH 1;

drop SEQUENCE pseq;
drop table point;

create table point(

pointid VARCHAR2(20) primary key, 
    pointPhone VARCHAR2(20) not null ,       
POINTTICKETNUM     NUMBER(38),    
POINTSIZE          NUMBER(38),    
INITPOINT          INTEGER DEFAULT 0,

POINTCHARGEDATE    DATE DEFAULT sysdate
);

alter table point drop column pointPhone;

drop table point;



UPDATE point SET initpoint=3000;
 
UPDATE point SET initpoint=(initpoint+pointsize) where pointid='hongcha89';

SELECT *FROM point;

drop table member;

commit;

drop table book;
CREATE SEQUENCE bookseq start WITH 1;

create table book(
bookseq              integer default 0,
BOOKTITLE            VARCHAR2(100) NOT NULL,  
BOOKAUTHOR           VARCHAR2(100) NOT NULL, 
BOOKPRICE            INTEGER NOT NULL,     
BOOKSIZE             VARCHAR2(100)NOT NULL,  
BOOKPAGE             VARCHAR2(100)NOT NULL,  
BOOKDATE             VARCHAR2(100)NOT NULL,  
ISBN                 VARCHAR2(100) PRIMARY key,  
BOOKCONTENT          VARCHAR2(500)NOT NULL,  
BOOKLIST             VARCHAR2(30)NOT NULL,   
IMAGEPATH                    VARCHAR2(4000), 
IMAGENAME                    VARCHAR2(300),  
BOOKINDEX                    VARCHAR2(4000), 
BOOKAUTHORINTRODUCE          VARCHAR2(4000) 



);

drop table cart;

drop SEQUENCE bookseq;

CREATE SEQUENCE cartseq START WITH 1;

drop SEQUENCE cartseq;

commit;

create table cart(

    cartseq       INTEGER,
    cartid        varchar2(100),
    cartBOOKTITLE VARCHAR2(100) ,
    cartbookPrice INTEGER,
    cartbookImageName VARCHAR2(100),
    cartDate date DEFAULT sysdate
    
    
);

desc book;

SELECT
    *
FROM member;

drop table cart;

--alter table cart add (cartBookList VARCHAR2(30));

SELECT
    *
FROM cart;

desc cart;


commit;

drop table cart;




alter table book MODIFY  (booktitle not null);

commit;

RENAME pay TO cart;


ALTER TABLE cart RENAME COLUMN TOTALCARTPRICE  TO CARTTOTALPRICE ;

alter table cart drop column payaddress;


alter table cart add (cartBookList varchar2(100));

alter table pay add FOREIGN KEY(paybookTitle) REFERENCES book(bookTitle);

desc member;

SELECT
    *
FROM cart;

DELETE From cart;

insert  into member (name,id,pwd,pwdFind,phone) VALUES('辑全劳','scott','tiger','tiger','010-2052-7905'); 
commit;


desc point;

SELECt * From point;

update point set  pointsize=30, initpoint=(initPoint+pointsize) where pointid='hongcha89' And pointphone='010-2052-7958';



DELETE from point;

commit;


UPDATE point SET initpoint=(initpoint+pointsize)  WHERE pointId='hongcha89';

ALTER table point add (initPoint integer default 0);


MERGE INTO point
USING DUAL
   ON (pointId = 'hongcha89')
WHEN MATCHED THEN
    UPDATE SET initpoint=(initpoint+pointsize) 
WHEN NOT MATCHED THEN
    INSERT (pointsize,pointticketNum) 
    VALUES (10,4567);
    
    SELECT
        *
    FROM point;
commit;

desc member;

SELECT
    *
FROM member;

insert into member(name,id,pwd,pwdFind,phone) values('包府磊','scott','tiger','tiger','010-3936-6304');

commit;

create SEQUENCE cartseq START WITH 1;


--INSERT INTO member(id,phone,name,pwd,pwdFind,email,  zipcode, detailaddress,address) values('包府磊','010-3936-6304','scott','tiger','tiger','h','h','h','h')
desc point;

insert INTO member(initpoint ) select point.initpoint from point,member where member.id=point.pointid; 

update member set  pointsize=10, point.initpoint=(point.initPoint+point.pointSize)from member  where point.pointid = member.id And point.pointphone=member.phone;

select * from member;

SELECT 

FROM member

where aid is not null;

desc member;



insert ALL
    into member  
    where 1!=1;
    