## 2022-04-13
- regenerate sheet project as a msa architecture.

## 2022-04-19
- create findAll api
- apply filterable api

## 2022-04-21
- arrange the way how to configure mysql in local
- implements query api
- 
## 2022-04-24
- sheet detail api
- troubleshoot longQueryOptionProvider
- refactor fingering and note as a no entity
- troubleshoot redishash setter unsupportedexception

## 2022-04-25
- create sheet postdetail api
- create barelperser
- implements fingeringByChord api

## 2022-04-26
- implements DefaultBarElParser
- troubleshoot infinity call of sheet detail
- troubleshoot DefaultBarElParser

## TODO
- someday change qclass as a normal class type not querydsl class type. it makes the dependency with querydsl
- query api with unknown target should be thrown unknown target exception.
- build redis data storage for fingering entity.
- how should encapsulate 100% about Filterable and Condition
  - what i am saying is that i really wanna separate the consumer part and the producer part
  - but from now on, my code the consumer part should know the implementation object of filterable or condition like simplecondition
  - i want to hide it from the consumer. how should do it?
- concern about transaction processing
- create test redis environment with embedded redis
- implements fingering and chord instances

## CONFIGURATION PROJECT

### MYSQL 

```
> docker pull mysql

> docker images

> docker run --name sheetshow-container -e MYSQL_ROOT_PASSWORD=sheetshow -d -p 3306:3306 mysql:latest

> docker exec -it sheetshow-container bash

> mysql -u root -p
Enter password : <password>

> CREATE DATABASE sheetshow
```

### MYSQL KOREAN ENCODING

```
> apt-get update

> apt-get install vim

> vi /etc/mysql/my.cnf
```

```
[client]
default-character-set=utf8

[mysql]
default-character-set=utf8

[mysqld]
pid-file        = /var/run/mysqld/mysqld.pid
socket          = /var/run/mysqld/mysqld.sock
datadir         = /var/lib/mysql
secure-file-priv= NULL
collation-server = utf8_unicode_ci
init-connect='SET NAMES utf8'
character-set-server = utf8
```

```
> ALTER DATABASE sheetshow DEFAULT CHARACTER SET utf8;
```