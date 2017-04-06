 CREATE DATABASE jk DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE dept_p (
  DEPT_ID VARCHAR(40) PRIMARY KEY NOT NULL,
  DEPT_NAME VARCHAR(40) DEFAULT NULL,
  PARENT_ID VARCHAR(40) DEFAULT NULL,
  STATE INTEGER(11) DEFAULT NULL
);

INSERT INTO DEPT_P
  (DEPT_ID, DEPT_NAME, PARENT_ID, STATE)
VALUES
  ('100', '������ó����', NULL, 1);
INSERT INTO dept_p
  (DEPT_ID, DEPT_NAME, PARENT_ID, STATE)
VALUES
  ('3d00290a-1af0-4c28-853e-29fbf96a2722', '�г���', '100', 1);
INSERT INTO dept_p
  (DEPT_ID, DEPT_NAME, PARENT_ID, STATE)
VALUES
  ('4028827c4fb6202a014fb6209c730000', '�����ܲð�', NULL, 1);
INSERT INTO dept_p
  (DEPT_ID, DEPT_NAME, PARENT_ID, STATE)
VALUES
  ('4028827c4fb633bd014fb64467470000', '����', NULL, NULL);
INSERT INTO dept_p
  (DEPT_ID, DEPT_NAME, PARENT_ID, STATE)
VALUES
  ('4028827c4fb645b0014fb64668550000',
   '���˲�cgx',
   '4028827c4fb6202a014fb6209c730000',
   1);
INSERT INTO dept_p
  (DEPT_ID, DEPT_NAME, PARENT_ID, STATE)
VALUES
  ('73f3fa2f-66a2-4d16-8306-78d89003031b', '���粿', '100', 1);
INSERT INTO dept_p
  (DEPT_ID, DEPT_NAME, PARENT_ID, STATE)
VALUES
  ('97f88a8c-90fc-4d52-aed7-2046f62fb498', '�ܾ���', '100', 1);
INSERT INTO dept_p
  (DEPT_ID, DEPT_NAME, PARENT_ID, STATE)
VALUES
  ('aeb1c7d3-9a54-4f73-b0ec-0325b83aef45', '���˲�', '100', 1);