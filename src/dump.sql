DROP DATABASE IF EXISTS `test`;
CREATE DATABASE  IF NOT EXISTS `test`;
 use `test`;

-- 
-- Table structure for table `books`
-- 
drop table if exists books;
create table books (
   id int,
   name varchar(50),
   authorName varchar(50),
   primary key (id));

--
-- Insert some values to table `books`
--  
insert into books values (1001, 'Java for dummies', 'Tan Ah Teck');
insert into books values (1002, 'More Java for dummies', 'Tan Ah Teck');
insert into books values (1003, 'More Java for more dummies', 'Mohammad Ali');
insert into books values (1004, 'A Cup of Java', 'Kumar');
insert into books values (1005, 'A Teaspoon of Java', 'Kevin Jones');
 