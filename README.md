# Employee Management System

企业人力资源管理系统

## 登录

员工管理员界面分流

## 管理员

* 从session域中获取username

* 修改密码（前端做了是否输入校验和两次密码输入一致校验）
* 退出登录功能

（管理员不设置头像）

首页（预设未来前端填充公告板和统计图表）

>除了基本的增删改查，以下大多支持多条件（多表）和模糊查询

人事管理

* 公告管理（新增时获取管理员id，使用LocalDateTime获取创建时间和修改时间）
* 薪资管理
* 考勤管理（对状态为非出勤或请假的员工取消当月全勤奖）
* 审批（管理请假审批和离职审批）
* 培训管理

用户管理

* 用户信息（管理员才能注册新用户。为了代码调试方便两个身份均可注册）
* 员工信息（联表查询，关联了用户和部门表）

部门管理（新增时和修改支持对等级为主管的员工进行动态遍历）

数据库连接池Durid

过滤器，未登录直接跳转回登录界面



## 一些坑记录

注意版本适配关系，可以解决很多报错

500报错控制台会有输出！

xml文件不能用--注释(虽然这样写会自动变灰)

有ResultMap时记得删除自动创建时出现的ResultType

JS导入文件路径要正确才能正常渲染。移动文件位置直接复制代码会导致路径错误

！尝试做数据回显失败 只能显示0 1等的数字值，虽然我觉得我写得很有道理

！尝试十字叉丝居中对齐失败

！尝试做出箭头排序效果失败

不要重蹈覆辙：获取x类名x为空时检查xml文件selectTotalCountByCondition内部没有注解直接写成员变量名

public Str getxxxStr之前不要瞎定义成员变量！

在 MySQL 中，表名、列名、索引名等标识符是不区分大小写的

问题：头像下拉框处点击无响应 原因：需要详细阅读element ui文档并按文档演示的写代码，不能理所当然使用@click

问题：员工处selectAll时一直获取不到userId。原因：请求是异步的，走getUserId时还没获取到结果，就已经走selectAll了。解决方法：挂载完成后在this.getUserId()里执行this.selectAll

问题：登录时如果username为中文会显示为问号。解决方法：在servlet写数据之前加一句`response.setContentType("text/plain;charset=utf-8");`

问题：trainingPartic前端传回来的id为空值。解决方法：好蠢啊你用的post方式传过来数据却用get的方式接收...

尝试使用vue项目，但教程多是使用框架，前后端联调失败，遂放弃 不使用vue路由而是location.href进行页面跳转

## ToDo

过滤器检查admin权限。返回login.html时做警告

员工进行头像上传，个人信息修改。以及根据员工id select各种信息
