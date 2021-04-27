
-- 회원 관리 테이블
create table member(
account varchar2(50),
password varchar2(150) not null,
name varchar(50) not null,
email VARCHAR2(100) not null unique,
auth varchar2(20) default 'common',
reg_date date default sysdate,
constraint pk_member primary key(account)
);

insert into member (account, password, name, email, auth)
values ('admin', '1234', '관리자', 'admin@gmail.com','admin');

commit;

select * from member;