drop table if exists security_user;
create table security_user
(
    id       int auto_increment
        primary key,
    username varchar(40)  not null,
    password varchar(200) not null,
    roles    varchar(255) null,
    constraint security_user_uk_username
        unique (username)
)
    comment '用户';

