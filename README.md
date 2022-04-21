## 2022-04-13
- regenerate sheet project as a msa architecture.

## 2022-04-19
- create findAll api
- apply filterable api

## 2022-04-21
- arrange the way how to configure mysql in local
- implements query api

## TODO
- someday change qclass as a normal class type not querydsl class type. it makes the dependency with querydsl
- query api with unknown target should be thrown unknown target exception.

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