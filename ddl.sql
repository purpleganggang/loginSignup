create user `loginSignup`@`localhost` identified by '1234';
create database loginSignup CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
grant all privileges on loginSignup.* to `loginSignup`@`localhost` ;
