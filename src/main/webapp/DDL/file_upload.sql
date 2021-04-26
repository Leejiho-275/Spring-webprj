-- 첨부파일 정보를 가지는 테이블 생성
create table file_upload(
file_name varchar2(150),
reg_date date default sysdate,
bno number(10) not null
);

-- PK, FK 부여
alter table file_upload
add constraint pk_file_name
primary key (file_name);

alter table file_upload
add constraint fk_file_upload_tbl_free_board
foreign key (bno)
references tbl_free_board (bno)
on delete cascade;