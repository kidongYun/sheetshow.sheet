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

## 2022-04-27
- concern about transaction processing
- configure redis for test environment
- troubleshoot bootJar error
- build the test environment

## 2022-05-06
- Troubleshoot about SLF4J

## 2022-05-11
- refactor defaultBarElParser
- troubleshoot defaultBarElParser
- troubleshoot the situation the key is diminished when chord key is same with others
- troubleshoot error can't find sheetexception class in test
- dto file in rest module should be change named payload
- 
## 2022-05-14
- configure Dockerfile
- configure docker-compose
- troubleshoot profile issue in docker image
- troubleshoot test error with illegalstateexception

## TODO
- someday change qclass as a normal class type not querydsl class type. it makes the dependency with querydsl
- query api with unknown target should be thrown unknown target exception.
- how should encapsulate 100% about Filterable and Condition
  - what i am saying is that i really wanna separate the consumer part and the producer part
  - but from now on, my code the consumer part should know the implementation object of filterable or condition like simplecondition
  - i want to hide it from the consumer. how should do it?
- create test redis environment with embedded redis
- implements fingering and chord instances
- sheet should have new column named 'status'
- Troubleshoot GET sheet list api return empty list.

## CONFIGURATION PROJECT

### DOCKER-COMPOSE

```
// launch application
> docker-compose --env-file ./config/.env.test up

// stop application
> docker-compose down
```

### DOCKER BUILD AND RUN

```
> docker build -t sheetshow-sheet:1.0.7-SNAPSHOT --build-arg SPRING_PROFILES_ACTIVE=local .

> docker run sheetshow-sheet:1.0.7-SNAPSHOT
```

### MYSQL 

```
> docker pull mysql

> docker images

> docker run --name sheetshow-sheet-mysql-container -e MYSQL_ROOT_PASSWORD=sheet -d -p 3306:3306 mysql:latest

> docker exec -it sheetshow-sheet-mysql-container bash

> mysql -u root -p
Enter password : <password>

> CREATE DATABASE sheetshow_sheet;
```

### REDIS

```
> docker pull redis

> docker run -p 6379:6379 --name sheetshow-redis-container -d redis

> docker exec -i -t redis_boot redis-cli
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