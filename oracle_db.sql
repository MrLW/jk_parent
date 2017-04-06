-- �������ռ�
create tablespace jk  datafile 'D:\software\database\oracle\tablespace\jk.DBF' size 50M autoextend on next 10M 

-- �����û��������ռ�
create user lw1 identified by 123456 default tablespace jk

-- ��Ȩ
grant dba to lw1

-- grant connect,resource to lw1

-- �л��û�

--DROP TABLE dept_p;

CREATE TABLE dept_p (
  DEPT_ID varchar2(40) PRIMARY KEY NOT NULL,
  DEPT_NAME varchar2(40) default NULL,
  PARENT_ID varchar2(40) default NULL,
  STATE number(11) default NULL
);

comment on column dept_p.STATE is '1�������ã�0����ͣ�ã�Ĭ��Ϊ1';

insert into DEPT_P
  (DEPT_ID, DEPT_NAME, PARENT_ID, STATE)
values
  ('100', '������ó����', NULL, 1);
insert into dept_p
  (DEPT_ID, DEPT_NAME, PARENT_ID, STATE)
values
  ('3d00290a-1af0-4c28-853e-29fbf96a2722', '�г���', '100', 1);
insert into dept_p
  (DEPT_ID, DEPT_NAME, PARENT_ID, STATE)
values
  ('4028827c4fb6202a014fb6209c730000', '�����ܲð�', NULL, 1);
insert into dept_p
  (DEPT_ID, DEPT_NAME, PARENT_ID, STATE)
values
  ('4028827c4fb633bd014fb64467470000', '����', NULL, NULL);
insert into dept_p
  (DEPT_ID, DEPT_NAME, PARENT_ID, STATE)
values
  ('4028827c4fb645b0014fb64668550000',
   '���˲�cgx',
   '4028827c4fb6202a014fb6209c730000',
   1);
insert into dept_p
  (DEPT_ID, DEPT_NAME, PARENT_ID, STATE)
values
  ('73f3fa2f-66a2-4d16-8306-78d89003031b', '���粿', '100', 1);
insert into dept_p
  (DEPT_ID, DEPT_NAME, PARENT_ID, STATE)
values
  ('97f88a8c-90fc-4d52-aed7-2046f62fb498', '�ܾ���', '100', 1);
insert into dept_p
  (DEPT_ID, DEPT_NAME, PARENT_ID, STATE)
values
  ('aeb1c7d3-9a54-4f73-b0ec-0325b83aef45', '���˲�', '100', 1);