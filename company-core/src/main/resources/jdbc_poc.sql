create database aemtraining;
use aemtraining;

create table aem_test_entities(
    id integer primary key auto_increment,
    name varchar(50) not null
);

insert into aem_test_entities(name) values ('dumy entity one');
insert into aem_test_entities(name) values ('dumy entity two');
insert into aem_test_entities(name) values ('dumy entity three');
insert into aem_test_entities(name) values ('dumy entity four');

select * from aem_test_entities;