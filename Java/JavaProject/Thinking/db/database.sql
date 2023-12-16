create database thinking;

use thinking;

create table article
(
    articleId        int auto_increment
        primary key,
    articleTitle     varchar(200) null,
    articleContent   text         null,
    articleIssueTime datetime     null,
    humanId          int          null,
    typeId int
);

create index articleTitleIndex
    on article (articleTitle(20));

create table human
(
    humanId       int auto_increment
        primary key,
    humanName     varchar(200) null,
    humanNiceName varchar(200) null,
    humanPassword varchar(200) null,
    humanEmail    varchar(200) null,
    humanDescribe text
);

create index humanLoginIndex
    on human (humanName(20), humanPassword(20));

create table reply
(
    replyId int primary key auto_increment,
    replyContent text,
    replyTime datetime,
    humanId int,
    articleId int
);

create table type
(
    typeId int primary key  auto_increment,
    typeName varchar(200)
);

-- 管理员
create table admin
(
    adminId int primary key auto_increment,
    adminName varchar(100),
    adminPassword varchar(100),
    adminHead text
);

-- 角色
create  table roles
(
    roleId int primary key auto_increment,
    roleName varchar(100)
);

-- 角色关系
create table roleRelation
(
    roleRelationId int primary key auto_increment,
    roleId int,
    adminId int
);

create table imageStore
(
    imageStoreId int primary key auto_increment,
    imageStoreAddress text,
    imageStoreDate datetime
);



