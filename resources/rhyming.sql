create table rhyming_dictionary
(
	id int auto_increment,
	word varchar(30) not null,
	syllables varchar(100) not null,
	last_syllable varchar(10) not null,
	
	primary key(id)
);

-- To get the database dump
--  mysqldump -u root -p Poetry > poetry_dump.sql