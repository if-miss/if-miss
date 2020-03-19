
/*==============================================================*/
/* Table: life_user 用户表                                       */
/*==============================================================*/
create table life_user 
(
   user_id              varchar(50)                    not null,
   user_account         varchar(50)                    not null,
   user_password        varchar(50)                    not null,
   user_name            varchar(50)                    not null,
   user_sex             tinyint                        not null,
   user_phone           varchar(20)                    null,
   user_email           varchar(50)                    null,
   update_time          datetime                       not null
);

alter table life_user
   add constraint PK_LIFE_USER primary key clustered (user_id);

/*==============================================================*/
/* Table: life_user_login   用户登录表                                    */
/*==============================================================*/
create table life_user_login 
(
   user_id              varchar(50)                    null,
   login_account        varchar(50)                    null,
   login_way            tinyint                        null,
   login_id             varchar(50)                    not null
);

alter table life_user_login
   add constraint PK_LIFE_USER_LOGIN primary key clustered (login_id);

alter table life_user_login
   add constraint FK_LIFE_USE_USER_LOGI_LIFE_USE foreign key (user_id)
      references life_user (user_id)
      on update restrict
      on delete restrict;
      
--菜单管理      
INSERT INTO `gt_swb_menu` (`MENUID`, `MENUCODE`, `MENUNAME`, `MENUORDER`, `ISSHOW`, `MENUGRPID`) VALUES ('uuid-menugrp-usercode-user', 'userlist', '用户管理', '50', '1', 'uuid-menugrp-usercode');      
      
