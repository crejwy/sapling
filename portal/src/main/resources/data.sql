SET NAMES utf8 ;


LOCK TABLES `ac_permission` WRITE;
ALTER TABLE `ac_permission` DISABLE KEYS ;
INSERT INTO `ac_permission` (id,available,name,parent_id,permission,resource_type,url,modifydt,modifier,is_deleted,creator,createdt)
VALUES (1,true,'角色管理',5,'role:*','menu','/role','2018-12-10 14:10:12','brave',false,'brave2','2018-12-17 15:52:18'),
(2,true,'用户管理',5,'user:*','menu','/user','2018-12-10 14:10:46','brave',false,'brave2','2018-12-17 15:52:22'),
(4,true,'权限管理',5,'permission:*','menu','/permission','2018-12-10 14:11:47','brave',false,'brave2','2018-12-17 15:52:31'),
(5,true,'Index',null,'index:view','menu','/','2018-12-14 10:29:59','brave2',false,'brave2','2018-12-17 15:59:14'),
(6,true,'角色分配',2,'user_role:save','button','','2018-12-14 11:33:52','brave2',false,'brave2','2018-12-17 16:02:05'),
(7,true,'角色新增',1,'role:create','button','','2018-12-14 11:41:10','brave',false,'brave2','2018-12-17 16:02:08'),
(8,true,'角色修改',1,'role:update','button','','2018-12-14 11:41:41','brave',false,'brave2','2018-12-17 16:02:12'),
(9,true,'角色删除',1,'role:delete','button','','2018-12-14 11:42:02','brave',false,'brave2','2018-12-17 16:02:16'),
(10,true,'角色列表',1,'role:view','request','','2018-12-14 11:44:40','brave',false,'brave2','2018-12-17 16:02:19'),
(11,true,'用户新增',2,'user:create','button','','2018-12-14 11:45:09','brave',false,'brave2','2018-12-17 16:02:21'),
(12,true,'用户修改',2,'user:update','button','','2018-12-14 11:45:35','brave',false,'brave2','2018-12-17 16:02:25'),
(13,true,'用户删除',2,'role:delete','button','','2018-12-14 11:45:57','brave',false,'brave2','2018-12-17 16:02:27'),
(14,true,'用户列表',2,'role:view','request','','2018-12-14 11:46:15','brave',false,'brave2','2018-12-17 16:02:29'),
(15,true,'用户修改密码',2,'user:alterpassword','button','','2018-12-14 11:47:24','brave',false,'brave','2018-12-19 15:11:28'),
(20,true,'权限新增',4,'permission:create','button','','2018-12-14 13:07:28','brave',false,'brave2','2018-12-17 16:02:56'),
(21,true,'权限修改',4,'permission:update','button','','2018-12-14 13:07:55','brave',false,'brave2','2018-12-17 16:03:00'),
(22,true,'权限删除',4,'permission:delete','button','','2018-12-14 13:08:12','brave',false,'brave2','2018-12-17 16:03:03'),
(23,true,'权限列表',4,'permission:view','request','','2018-12-14 13:08:26','brave',false,'brave2','2018-12-17 16:03:06'),
(24,true,'角色权限查看',1,'role_permission:view','button','','2018-12-14 13:13:29','brave',false,'brave2','2018-12-17 16:03:08'),
(25,true,'角色权限保存',1,'role_permission:save','button','','2018-12-14 13:13:57','brave',false,'brave2','2018-12-17 16:03:10');
ALTER TABLE `ac_permission` ENABLE KEYS;
UNLOCK TABLES;

--
-- Dumping data for table `ac_role`
--

LOCK TABLES `ac_role` WRITE;
ALTER TABLE `ac_role` DISABLE KEYS;
INSERT INTO `ac_role`
VALUES
(1,'2018-12-06 17:00:43','brave',false,'brave','2018-12-14 08:29:05',true,'admin','admin'),
(2,'2018-12-06 19:10:18','brave',false,'brave','2018-12-19 15:20:19',true,'用户','user');
ALTER TABLE `ac_role` ENABLE KEYS ;
UNLOCK TABLES;

--
-- Dumping data for table `ac_user`
--

LOCK TABLES `ac_user` WRITE;
ALTER TABLE `ac_user` DISABLE KEYS;
INSERT INTO `ac_user` VALUES
(1,'2018-12-05 00:00:00','brave',false,'brave2','2018-12-19 16:14:21','123','181205G4BCSGKM14',1,'brave'),
(3,'2018-12-05 17:57:37','brave',false,'brave','2018-12-05 21:14:21','123','181205G4C07WAW94',1,'brave2');
ALTER TABLE `ac_user` ENABLE KEYS;
UNLOCK TABLES;

--
-- Dumping data for table `sys_role_permission`
--

LOCK TABLES `sys_role_permission` WRITE;
ALTER TABLE `sys_role_permission` DISABLE KEYS;
INSERT INTO `sys_role_permission` VALUES (1,1),(2,1),(1,2),(1,4),(1,5),(2,5),(1,6),(2,6),(1,7),(2,7),(1,8),(2,8),(1,9),(2,9),(1,10),(2,10),(1,11),(2,11),(1,12),(2,12),(1,13),(2,13),(1,14),(2,14),(1,15),(2,15),(1,20),(2,20),(1,21),(2,21),(1,22),(2,22),(1,23),(2,23),(1,24),(1,25);
ALTER TABLE `sys_role_permission` ENABLE KEYS;
UNLOCK TABLES;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
ALTER TABLE `sys_user_role` DISABLE KEYS ;
INSERT INTO `sys_user_role` VALUES (3,1),(1,2);
ALTER TABLE `sys_user_role` ENABLE KEYS ;
UNLOCK TABLES;
