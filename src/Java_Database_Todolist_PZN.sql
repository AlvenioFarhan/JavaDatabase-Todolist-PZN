create table todolist(
id int not null auto_increment,
todo varchar(255) not null,
primary key (id)
)engine=InnoDB;

show tables;

desc todolist;

select * from todolist;