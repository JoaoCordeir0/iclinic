create database iclinic;
use iclinic;

create table funcionario(	
    idFuncionario int primary key auto_increment,
	codFuncionario varchar(50),
    nomeFuncionario varchar(255),	
    nomeCargo varchar(255),
    dataInsercao date default now()
);

create table turnos(
	idTurno int primary key auto_increment,
    diaTurno date,
    tipoTurno varchar(255),
    idFuncionario int,
    dataInsercao date default now(),
    foreign key (idFuncionario) references funcionario (idFuncionario)
);
