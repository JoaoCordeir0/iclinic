create database iclinic;
use iclinic;

create table cargo(
	idCargo int primary key auto_increment,
    nomeCargo varchar(50),
    dataInsercao date default now()
);

insert into cargo (nomeCargo) values ("Médico");
insert into cargo (nomeCargo) values ("Enfermeiro");
insert into cargo (nomeCargo) values ("Residente");

create table funcionario(
	codFuncionario int primary key auto_increment,
    nomeFuncionario varchar(255),	
    idCargo int,
    dataInsercao date default now(),
	foreign key (idCargo) references cargo (idCargo)
);

create table turnos(
	idTurno int primary key auto_increment,
    dataInicio date,
    dataFim date,
    codFuncionario int,
    foreign key (codFuncionario) references funcionario (codFuncionario)
);