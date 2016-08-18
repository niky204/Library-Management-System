use cs320stu54;
drop table if exists typelist;
drop table if exists libraryentry;
drop table if exists studententry;
drop table if exists users;

create table typelist(
	id integer auto_increment primary key,
    type varchar(255)
);

insert into typelist values(1,'Book');
insert into typelist values(2,'Tablet');


create table users (
	id       integer auto_increment primary key,
    username varchar(32),
	password varchar(32)	
);

insert into users values(1,'cysun','abcd');


create table libraryentry (
    id             integer auto_increment primary key,
	type           integer references type(id) ,
	name           varchar(255),
	additionalinfo varchar(255),
	available      boolean,
    overdue	   	   boolean,
	noofcopies     Integer
);
insert into libraryentry values (1, 2,'Samsung Galaxy Tab 10.1','Android 3.0',true,false,1 );
insert into libraryentry values (2, 1,'Cracking the code interview','2011',false,false,1 );
insert into libraryentry values (3, 1,'Cracking the code interview','2011',true,false,1 );
insert into libraryentry values (4, 2,'Nexus 7 (2nd Gen)','Android 4.4.2',true,false,1 );
insert into libraryentry values (5, 2,'Nexus 7 (2nd Gen)','Android 4.4.2',false,true,1 );

create table studententry(
	sid     	  integer references libraryentry(id),
	id      	  integer auto_increment primary key,
	cin     	  varchar(255),
    nameofstudent varchar(255),
	dateborrowed  varchar(255),
	duedate		  varchar(255),
	datereturned  varchar(255)
);   

insert into studententry values (1,1,'3400','niki','8/09/2014','12/09/2014','11/09/2014');
insert into studententry values (1,2,'3500','neha','3/09/2014','7/09/2014','6/09/2014');
