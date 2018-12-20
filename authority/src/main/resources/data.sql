SET NAMES utf8 ;
LOCK TABLES `ac_permission` WRITE;
ALTER TABLE `ac_permission` DISABLE KEYS ;
INSERT INTO `ac_permission` VALUES (1,_binary '',NULL,'角色管理',5,NULL,'role:*','menu','/role','2018-12-10 14:10:12','brave',_binary '\0','brave2','2018-12-17 15:52:18'),(2,_binary '',NULL,'用户管理',5,NULL,'user:*','menu','/user','2018-12-10 14:10:46','brave',_binary '\0','brave2','2018-12-17 15:52:22'),(4,_binary '',NULL,'权限管理',5,NULL,'permission:*','menu','/permission','2018-12-10 14:11:47','brave',_binary '\0','brave2','2018-12-17 15:52:31'),(5,_binary '',NULL,'Index',NULL,NULL,'index:view','menu','/','2018-12-14 10:29:59','brave2',_binary '\0','brave2','2018-12-17 15:59:14'),(6,_binary '',NULL,'角色分配',2,NULL,'user_role:save','button','','2018-12-14 11:33:52','brave2',_binary '\0','brave2','2018-12-17 16:02:05'),(7,_binary '',NULL,'角色新增',1,NULL,'role:create','button','','2018-12-14 11:41:10','brave',_binary '\0','brave2','2018-12-17 16:02:08'),(8,_binary '',NULL,'角色修改',1,NULL,'role:update','button','','2018-12-14 11:41:41','brave',_binary '\0','brave2','2018-12-17 16:02:12'),(9,_binary '',NULL,'角色删除',1,NULL,'role:delete','button','','2018-12-14 11:42:02','brave',_binary '\0','brave2','2018-12-17 16:02:16'),(10,_binary '',NULL,'角色列表',1,NULL,'role:view','request','','2018-12-14 11:44:40','brave',_binary '\0','brave2','2018-12-17 16:02:19'),(11,_binary '',NULL,'用户新增',2,NULL,'user:create','button','','2018-12-14 11:45:09','brave',_binary '\0','brave2','2018-12-17 16:02:21'),(12,_binary '',NULL,'用户修改',2,NULL,'user:update','button','','2018-12-14 11:45:35','brave',_binary '\0','brave2','2018-12-17 16:02:25'),(13,_binary '',NULL,'用户删除',2,NULL,'role:delete','button','','2018-12-14 11:45:57','brave',_binary '\0','brave2','2018-12-17 16:02:27'),(14,_binary '',NULL,'用户列表',2,NULL,'role:view','request','','2018-12-14 11:46:15','brave',_binary '\0','brave2','2018-12-17 16:02:29'),(15,_binary '',NULL,'用户修改密码',2,NULL,'user:alterpassword','button','','2018-12-14 11:47:24','brave',_binary '\0','brave','2018-12-19 15:11:28'),(20,_binary '',NULL,'权限新增',4,NULL,'permission:create','button','','2018-12-14 13:07:28','brave',_binary '\0','brave2','2018-12-17 16:02:56'),(21,_binary '',NULL,'权限修改',4,NULL,'permission:update','button','','2018-12-14 13:07:55','brave',_binary '\0','brave2','2018-12-17 16:03:00'),(22,_binary '',NULL,'权限删除',4,NULL,'permission:delete','button','','2018-12-14 13:08:12','brave',_binary '\0','brave2','2018-12-17 16:03:03'),(23,_binary '',NULL,'权限列表',4,NULL,'permission:view','request','','2018-12-14 13:08:26','brave',_binary '\0','brave2','2018-12-17 16:03:06'),(24,_binary '',NULL,'角色权限查看',1,NULL,'role_permission:view','button','','2018-12-14 13:13:29','brave',_binary '\0','brave2','2018-12-17 16:03:08'),(25,_binary '',NULL,'角色权限保存',1,NULL,'role_permission:save','button','','2018-12-14 13:13:57','brave',_binary '\0','brave2','2018-12-17 16:03:10');
ALTER TABLE `ac_permission` ENABLE KEYS;
UNLOCK TABLES;

--
-- Dumping data for table `ac_role`
--

LOCK TABLES `ac_role` WRITE;
ALTER TABLE `ac_role` DISABLE KEYS;
INSERT INTO `ac_role` VALUES (1,'2018-12-06 17:00:43','brave',_binary '\0','brave','2018-12-14 08:29:05',_binary '','admin','admin'),(2,'2018-12-06 19:10:18','brave',_binary '\0','brave','2018-12-19 15:20:19',_binary '','用户','user');
ALTER TABLE `ac_role` ENABLE KEYS ;
UNLOCK TABLES;

--
-- Dumping data for table `ac_user`
--

LOCK TABLES `ac_user` WRITE;
ALTER TABLE `ac_user` DISABLE KEYS;
INSERT INTO `ac_user` VALUES (1,'2018-12-05 00:00:00','brave',_binary '\0','brave2','2018-12-19 16:14:21','123','181205G4BCSGKM14',1,'brave'),(3,'2018-12-05 17:57:37','brave',NULL,'brave','2018-12-05 21:14:21','123','181205G4C07WAW94',1,'brave2');
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
