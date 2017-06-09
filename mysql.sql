-- 在本项目中涉及到的数据库方面的问题
-- 异常：Incorrect string value: '\xE6\x99\xB6\xE6\x99\xA8'
-- 修改表的编码格式
ALTER TABLE product_c DEFAULT CHARACTER SET utf8;  
-- 修改列的编码
ALTER TABLE  product_c CHANGE  DESCRIPTION  DESCRIPTION VARCHAR( 200 ) CHARACTER SET utf8 COLLATE utf8_general_ci 
ALTER TABLE  product_c CHANGE  FACTORY_NAME  FACTORY_NAME VARCHAR( 250 ) CHARACTER SET utf8 COLLATE utf8_general_ci 
