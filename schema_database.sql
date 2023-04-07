create database iclinic;
use iclinic;

create table funcionario(	
    codFuncionario int primary key,
    nomeFuncionario varchar(255),	
    nomeCargo varchar(255),
    dataInsercao date default now()
);

create table turnos(
	idTurno int primary key auto_increment,  
    tipoTurno varchar(255),
    codFuncionario int,
    dataInsercao date default now(),
    foreign key (codFuncionario) references funcionario (codFuncionario)
);


