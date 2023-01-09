set names utf8mb4;
set foreign_key_checks = 0;

create database election;

use election;

drop table if exists t_user;
create table t_user (
                        id bigint primary key auto_increment comment '自增主键',
                        email varchar(64) not null comment '用户邮件',
                        id_card varchar(10) not null comment '香港身份证',
                        admin tinyint(1) default false comment '是否为管理员, 1/true 管理员身份 0/false 普通用户',
                        create_time datetime comment '数据创建时间',
                        modified_time datetime comment '数据最后一次更新时间, ',
                        deleted tinyint(1) default false comment '数据逻辑删除状态, 1/true: 已删除 0/false: 未删除 默认0/未删除'
) comment '平台用户表';

drop table if exists t_candidate;
create table t_candidate (
                             id bigint primary key auto_increment comment '自增主键',
                             nickname varchar(64) not null comment '候选人昵称',
                             create_time datetime comment '数据创建时间',
                             modified_time datetime comment '数据最后一次更新时间, ',
                             deleted tinyint(1) default false comment '数据逻辑删除状态, 1/true: 已删除 0/false: 未删除 默认0/未删除'
) comment '平台候选人';



