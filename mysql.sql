-- �ڱ���Ŀ���漰�������ݿⷽ�������
-- �쳣��Incorrect string value: '\xE6\x99\xB6\xE6\x99\xA8'
-- �޸ı�ı����ʽ
ALTER TABLE product_c DEFAULT CHARACTER SET utf8;  
-- �޸��еı���
ALTER TABLE  product_c CHANGE  DESCRIPTION  DESCRIPTION VARCHAR( 200 ) CHARACTER SET utf8 COLLATE utf8_general_ci 
ALTER TABLE  product_c CHANGE  FACTORY_NAME  FACTORY_NAME VARCHAR( 250 ) CHARACTER SET utf8 COLLATE utf8_general_ci 
