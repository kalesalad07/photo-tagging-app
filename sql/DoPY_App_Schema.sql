create database if not exists DOPY;
use DOPY;

-- creating entities
create table if not exists STUDENT (ID varchar(20), NAME varchar(50), primary key (ID));
create table if not exists CLUB_DEPT (ID int, NAME varchar(50), primary key (ID));
create table if not exists HOSTELS (HOSTEL_NAME varchar(20), ROOM_NUM int, primary key (HOSTEL_NAME, ROOM_NUM));
create table if not exists ADMIN (ADMIN_NAME varchar(50), ADMIN_ID int, primary key (ADMIN_ID));
alter table admin add column password varchar(50);
create table if not exists PHOTOS (PHOTO_ID int, ROLL_NUM int, DRIVE_LINK varchar(100), FRAME_NUM int, DATE_TIME datetime, PRICE int, CLUB_DEPT int, primary key (PHOTO_ID));
create table if not exists CAMERA (CAMERA_ID int, BATTERY_LEFT int, STORAGE_LEFT int, primary key (CAMERA_ID));
create table if not exists CAMERA_LIST (CAMERA_NAME varchar(50), primary key (CAMERA_NAME));

-- creating relations
create table if not exists LIVES_IN 
(HOSTEL_NAME varchar(20),
 ROOM_NUM int, 
 S_ID varchar(20),
 primary key (S_ID),
 foreign key (S_ID) references STUDENT(ID),
 foreign key (HOSTEL_NAME, ROOM_NUM) references HOSTELS(HOSTEL_NAME, ROOM_NUM));
 
create table if not exists CAMERA_TYPE 
(CAMERA_NAME varchar(50),
 CAMERA_ID int,
 primary key (CAMERA_ID),
 foreign key (CAMERA_NAME) references CAMERA_LIST(CAMERA_NAME),
 foreign key (CAMERA_ID) references CAMERA(CAMERA_ID));
 
create table if not exists TAGGED_IN 
(S_ID varchar(20),
 PHOTO_ID int,
 primary key (S_ID, PHOTO_ID),
 foreign key (S_ID) references STUDENT(id),
 foreign key (PHOTO_ID) references PHOTOS(PHOTO_ID));
 
create table if not exists CONTROL_DESK
(CLICKER_ID int,
 DETAILER_ID int,
 PHOTO_ID int,
 CAMERA_ID int,
 primary key (PHOTO_ID),
 foreign key (CLICKER_ID) references ADMIN(ADMIN_ID),
 foreign key (DETAILER_ID) references ADMIN(ADMIN_ID),
 foreign key (PHOTO_ID) references PHOTOS(PHOTO_ID),
 foreign key (CAMERA_ID) references CAMERA(CAMERA_ID));
 
create table if not exists MEMBERSHIP
(C_ID int, 
 S_ID varchar(20), 
 primary key (C_ID, S_ID), 
 foreign key (S_ID) references STUDENT(ID), 
 foreign key (C_ID) references CLUB_DEPT(ID));

create table if not exists POR
(C_ID int, 
 S_ID varchar(20), 
 primary key (C_ID, S_ID), 
 foreign key (S_ID) references STUDENT(ID), 
 foreign key (C_ID) references CLUB_DEPT(ID));
 alter table POR add password varchar(50);
 
 create table if not exists DELIVERIES
(ADMIN_ID int, 
 HOSTEL_NAME varchar(20),
 ROOM_NUM int, 
 primary key (ADMIN_ID, HOSTEL_NAME, ROOM_NUM), 
 foreign key (ADMIN_ID) references ADMIN(ADMIN_ID),
 foreign key (HOSTEL_NAME, ROOM_NUM) references HOSTELS(HOSTEL_NAME, ROOM_NUM));