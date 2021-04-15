create sequence seq_reply;

create table tbl_reply(
rno number(10),
bno number(10) not null,
reply varchar2(1000) not null,
replyer varchar2(50) not null,
reply_date date default sysdate,
update_date date default sysdate
);

alter table tbl_reply
add constraint pk_reply
primary key (rno);

alter table tbl_reply
add constraint fk_reply_free_board
foreign key (bno)
REFERENCES tbl_free_board (bno);