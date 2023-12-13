CREATE SCHEMA `TasksSystem` DEFAULT CHARACTER SET utf8;

CREATE TABLE `Users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_Name` varchar(45) NOT NULL,
  `last_Name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `Tasks` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `head` varchar(45) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `status` varchar(45) NOT NULL,
  `priority` varchar(45) NOT NULL,
  `author` varchar(45) NOT NULL,
  `executor` varchar(45) NOT NULL,
  `user_Id` bigint NOT NULL REFERENCES Users(id),
  PRIMARY KEY (`id`)
);

CREATE TABLE `Comments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(1000) NOT NULL,
  `task_Id` bigint NOT NULL REFERENCES Tasks(id),
  PRIMARY KEY (`id`)
);

CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO roles (name)
VALUES
('ROLE_USER'), ('ROLE_ADMIN');

create table users_roles (
user_id bigint not null,
role_id bigint not null,
primary key(user_id, role_id),
foreign key(user_id) references Users(id),
foreign key(role_id) references roles(id)
);

