use db2;
drop table tb_department;
create table tb_department
(
    id          int not null primary key auto_increment comment '部门id',
    name        varchar(20) comment '部门名称',
    description varchar(255) comment '部门描述',
    manager_id  int comment '部门主管id'
) comment '部门表';

-- 建了tb_staff表后，添加外键约束
alter table tb_department
    add foreign key (manager_id) references tb_staff (id) on delete cascade;

-- 插入测试数据
Insert into tb_department (name, description)
values ('技术部', '这是技术部'),
       ('销售部', '这是销售部'),
       ('运维部', '这是运维部'),
       ('人事部', '这是人事部');

drop table tb_user;
create table tb_user
(
    id       int not null primary key auto_increment comment '用户id',
    -- 用户名唯一
    username varchar(15) comment '用户名',
    password varchar(20) comment '密码',
    identity tinyint comment '身份，0-员工，1-管理员'
) comment '用户表';

-- 添加索引
CREATE INDEX idx_user_username ON tb_user (username);
Insert into tb_user (username, password, identity)
values ('回忆起', '123', 1),
       ('路太远', '123', 1);

create table tb_announcement
(
    id               int not null primary key auto_increment comment '公告id',
    title            varchar(50) comment '标题',
    content          varchar(500) comment '内容',
    created_time     varchar(255) comment '添加时间',
    last_update_time varchar(255) comment '最后修改时间',
    user_id          int comment '创建者id',
    foreign key (user_id) references tb_user (id) on delete cascade
) comment '公告表';

Insert into tb_announcement (title, content, created_time, last_update_time)
values ('欢迎使用本系统', '由于不可控力因素今年开工延迟', now(), now());

drop table tb_staff;
create table tb_staff
(
    id            int not null primary key auto_increment comment '员工id',
    name          varchar(255) comment '姓名',
    gender        tinyint comment '性别，0-男，1-女',
    level         tinyint comment '身份，0-员工，1-主管',
    avatar        varchar(255) comment '头像',
    phone         char(11) comment '电话',
    status        tinyint comment '员工状态，0-在职，1-离职，2-休假，3-请假',
    department_id int comment '部门id',
    user_id       int comment '用户id',
    foreign key (department_id) references tb_department (id) on delete cascade,
    foreign key (user_id) references tb_user (id) on delete cascade
) comment '员工表';

Insert into tb_staff (name, gender, level, department_id, user_id)
values ('777', 0, 0, 1, 3);

drop table tb_salary;
create table tb_salary
(
    id                    int not null primary key auto_increment comment '主键',
    month                 varchar(20) comment '月份',
    basic_salary          DECIMAL(10, 2) comment '底薪',
    full_attendance_bonus DECIMAL(10, 2) comment '满勤奖励',
    performance_bonus     DECIMAL(10, 2) comment '业绩奖金',
    remark                varchar(255) comment '备注',
    staff_id              int comment '员工id',
    department_id         int comment '部门id',
    foreign key (staff_id) references tb_staff (id) on delete cascade,
    foreign key (department_id) references tb_department (id) on delete cascade
) comment '薪资表';

-- 添加索引
CREATE INDEX idx_salary_staff_id ON tb_salary (staff_id);

insert tb_salary (month, basic_salary, full_attendance_bonus, performance_bonus, remark, staff_id, department_id) values ('2024-03', 1000, 100, 100, '2024年3月工资', 11, 3);
insert tb_salary (month, basic_salary, full_attendance_bonus, performance_bonus, remark, staff_id, department_id) values ('2024-03', 1000, 100, 100, '2024年3月工资', 12, 3);

drop table tb_attendance;
create table tb_attendance
(
    id           int not null primary key auto_increment comment '主键',
    date         varchar(20) comment '日期',
    status tinyint comment '考勤状态，0-出勤，1-迟到，2-早退，3-请假，4-缺勤',
    staff_id     int  comment '员工id',
    foreign key (staff_id) references tb_staff (id) on delete cascade
) comment '考勤记录表';

-- 添加索引
CREATE INDEX idx_attendance_staff_id ON tb_attendance (staff_id);

insert tb_attendance(date,status,staff_id) values ('2024-03-04',2,11);
delete from tb_attendance where id = 2;

drop table tb_application;
create table tb_application(
                               id int not null primary key auto_increment comment '主键',
                               type tinyint comment '类型，0-请假，1-离职',
                               start_time varchar(20) comment '开始时间',
                               end_time varchar(20) comment '结束时间',
                               reason varchar(255) comment '原因',
                               status tinyint comment '状态，0-待审批，1-同意，2-拒绝',
                               staff_id int comment '员工id',
                               foreign key (staff_id) references tb_staff (id) on delete cascade
) comment '申请表';
-- 离职没有开始和结束时间

-- 添加索引
CREATE INDEX idx_application_staff_id ON tb_application (staff_id);
insert tb_application(type,start_time,end_time,reason,status,staff_id) values (0,'2024-03-04','2024-03-05','去冰岛旅游','0',11);

drop table tb_training;
create table tb_training
(
    id          int not null primary key auto_increment comment '主键',
    name        varchar(50) comment '活动名称',
    description varchar(500) comment '活动描述',
    start_time  varchar(20) comment '开始时间',
    end_time    varchar(20) comment '结束时间',
    admin_id    int comment '发起人id',
    foreign key (admin_id) references tb_user (id) on delete cascade
) comment '培训活动表';

insert tb_training (name, description, start_time, end_time, admin_id)
values ('越野行走', '周六至周天早上八点去湖光岩越野行走，记得带上雨伞', '2024-03-09', '2024-03-10', 10);
insert tb_training (name, description, start_time, end_time, admin_id)
values ('备战预选赛', '第一轮深渊预选赛已经结束，下一轮的第一场将由dou5对阵Gr,请各位选手认真备赛', '2024-03-11',
        '2024-03-17', 10);

drop table tb_training_participation;
create table tb_training_participation
(
    id          int not null primary key auto_increment comment '主键',
    training_id int not null comment '培训活动id',
    staff_id    int not null comment '员工id',
    score       decimal(5, 2) comment '成绩',
    foreign key (training_id) references tb_training (id) on delete cascade,
    foreign key (staff_id) references tb_staff (id) on delete cascade
) comment '培训参与表';

