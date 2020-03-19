
create table life_style 
(
   user_id              varchar(50)                    not null   
);

alter table life_style
   add constraint PK_life_style primary key clustered (user_id);