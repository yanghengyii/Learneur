
create table lesson
(
    id_lesson       bigint comment '主键' primary key auto_increment,
    name            varchar(32) comment '名称',
    description     varchar(200) comment '简要描述',
    link            varchar(50) comment '链接',
    img_path        varchar(50) comment '配图'
) comment '网课' collate = utf8mb4_unicode_ci;


create table book
(
    id_book         bigint comment '主键' primary key auto_increment,
    title           varchar(32) comment '书籍名称',
    img_path        varchar(50) comment '配图',
    author          varchar(32) comment '书籍作者',
    publisher       varchar(32) comment '出版商',
    language        varchar(32) comment '语言'
) comment '书籍' collate = utf8mb4_unicode_ci;


create table video
(
    id_video        bigint comment '主键' primary key auto_increment,
    author          varchar(50) comment '作者',
    BVid            varchar(50) comment 'BV号',
    length          varchar(50) comment '长度',
    pic_path        varchar(100) comment '图片链接',
    description     varchar(100) comment '简介',
    title           varchar(50) comment '标题'

) comment '视频' collate = utf8mb4_unicode_ci;

create table project
(
    id_project      bigint comment '主键' primary key auto_increment,
    name            varchar(50) comment '名称',
    update_time     date comment '更新时间',
    link            varchar(50) comment '跳转链接',
    description     varchar(200) comment '简要信息',
    star_gazers     int comment '收藏',
    forks           int comment '复制',
    home_page       varchar(50) comment '主页',
    readme          varchar(50) comment 'readme'
) comment '项目' collate = utf8mb4_unicode_ci;


create table tutorial
(
    id_tutorial     bigint comment '主键' primary key auto_increment,
    name            varchar(50) comment '名称',
    link            varchar(100) comment '链接',
    description     varchar(200) comment '简介'
)comment '教程' collate = utf8mb4_unicode_ci;


create table knowledge (
    id_knowledge            bigint comment '主键' primary key auto_increment,
    knowledge_name          varchar(50) comment '知识点' not null ,
    knowledge_description   varchar(200) comment '知识点描述'
) comment '知识点' collate = utf8mb4_unicode_ci;


create table knowledge_resource (
    id_resource bigint comment '资源id' not null ,
    id_knowledge bigint comment '知识点id' not null,
    type            int comment '资源类型: 1:网课; 2:书籍; 3:项目; 4:教程; 5:视频'
) comment '知识点与资源关联表' collate = utf8mb4_unicode_ci;

create table note (
    note_id              bigint auto_increment comment '主键'  primary key auto_increment,
    note_title           varchar(128) null comment '文章标题',
    note_author_id       bigint null comment '文章作者id',
    note_KP              varchar(128) null comment '文章知识点',
    note_view_count      int  default 1 null comment '浏览总数',
    note_preview_content varchar(256) null comment '预览内容',
    note_comment_count   int  default 0 null comment '评论总数',
    created_time         datetime null comment '创建时间',
    updated_time         datetime null comment '更新时间',
    note_thumbs_up_count int  default 0 null comment '点赞总数',
    note_content         text null comment '评论内容',
    id_resources         bigint not null comment '资源',
    version              bigint null comment '乐观锁'
) comment '学习笔记' collate = utf8mb4_unicode_ci;

create table user (
    user_id             bigint auto_increment comment '用户ID' primary key,
    username            varchar(32) not null comment '用户名',
    password            varchar(64) not null comment '密码',
    real_name           varchar(32) null comment '真实姓名',
    sex                 char default '0' null comment '性别',
    avatar_url          varchar(512) null comment '头像路径',
    email               varchar(64) null comment '邮箱',
    phone               varchar(11) null comment '电话',
    online              char default '0' null comment '状态',
    register_time       datetime null comment '创建时间',
    last_login_time     datetime null comment '最后登录时间',
    description         varchar(128) null comment '个人简介',
    id_role             bigint not null comment '角色'
) comment '用户' collate = utf8mb4_unicode_ci;

create table thumbUp (
    thumb_up_id         bigint auto_increment comment '点赞id' primary key ,
    user_id             bigint not null comment '用户id',
    note_id             bigint not null comment '笔记id'
) comment '点赞' collate = utf8mb4_unicode_ci;

create table role (
    role_id      bigint auto_increment comment '主键' primary key,
    name         varchar(32) null comment '名称'
) comment '角色' collate = utf8mb4_unicode_ci;

insert into role values (1, 'admin');
insert into role values (2, 'user');
insert into role values (3, 'others');