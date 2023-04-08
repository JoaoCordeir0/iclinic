## Iclinic

```javascript
const integrantes = {
    Henrique Fernandes Magnoli RA: 27097-5
    João Victor Cordeiro RA: 27099-5
    Lucas Gabryel de Oliveira Freitas RA: 27323-3
}
```

### Objetivo do software

Em um hospital, cada funcionário possui um código profissional, nome e cargo.
O cargo pode ser: enfermeiro, médico e residente. Se o cargo for enfermeiro o código é COREN, se for médico ou residente o código é CRM.

A cada mês deve ser previsto em cada dia o quadro de funcionários. Neste hospital, os
funcionários são alocados para dar plantão, no regime 12/36, ou seja, trabalham 12
horas ininterruptas e devem passar em seguida 36 horas de descanso, fora do ambiente
de trabalho. O turno diurno começa às 7h da manhã e termina às 7h da noite. O turno
noturno começa às 7 horas da noite e termina às 7h da manhã.
Seu trabalho deve conter as seguintes funcionalidades:

> a) Cadastro de funcionários

Deve ler as informações do respectivo funcionário e armazená-las num
vetor.
Seu programa não pode possibilitar cadastro de funcionários com o
mesmo código.

> b) Planejamento de turnos

Deve ler um funcionário cadastrado, que dias de abril e que turno ele deseja
realizar.
Deve avisar e recusar a informação de datas e turnos que violem a regra 12/36.

> c) Troca de plantões

O sistema deve possibilitar que um funcionário troque o dia de seu plantão,
desde que ele não viole a regra 12/36.

> d) Impressão dos turnos 

Impressão dos turnos cadastrados em cada dia do mês de abril com o nome dos
profissionais e seus cargos.

#

### Orientações para rodar o projeto

* Utilize o schema_database na raiz do projeto para criar o banco de dados que o sistema irá utilizar;

* Configure as credenciais do seu banco de dados na página Database dentro da pasta Core;

* Execute o arquivo Home para rodar o projeto;
