-- Inserting records
-- inserting photos
-- roll 1
insert into photos (PHOTO_ID, ROLL_NUM, DRIVE_LINK, FRAME_NUM, DATE_TIME, PRICE, CLUB_DEPT) values (1, 1, "/Users/deepan_roy/Desktop/DoPY/Roll1/2000R07.JPG", 1, now(), 15, 1);
insert into photos (PHOTO_ID, ROLL_NUM, DRIVE_LINK, FRAME_NUM, DATE_TIME, PRICE, CLUB_DEPT) values (2, 1, "/Users/deepan_roy/Desktop/DoPY/Roll1/2000R09.JPG", 2, now(), 15, 1);
insert into photos (PHOTO_ID, ROLL_NUM, DRIVE_LINK, FRAME_NUM, DATE_TIME, PRICE, CLUB_DEPT) values (3, 1, "/Users/deepan_roy/Desktop/DoPY/Roll1/2000R10.JPG", 3, now(), 15, NULL);
insert into photos (PHOTO_ID, ROLL_NUM, DRIVE_LINK, FRAME_NUM, DATE_TIME, PRICE, CLUB_DEPT) values (4, 1, "/Users/deepan_roy/Desktop/DoPY/Roll1/2000R13.JPG", 4, now(), 15, 1);
insert into photos (PHOTO_ID, ROLL_NUM, DRIVE_LINK, FRAME_NUM, DATE_TIME, PRICE, CLUB_DEPT) values (5, 1, "/Users/deepan_roy/Desktop/DoPY/Roll1/2000R358.JPG", 5, now(), 15, NULL);
insert into photos (PHOTO_ID, ROLL_NUM, DRIVE_LINK, FRAME_NUM, DATE_TIME, PRICE, CLUB_DEPT) values (6, 1, "/Users/deepan_roy/Desktop/DoPY/Roll1/2000R363.JPG", 6, now(), 15, 1);
insert into photos (PHOTO_ID, ROLL_NUM, DRIVE_LINK, FRAME_NUM, DATE_TIME, PRICE, CLUB_DEPT) values (7, 1, "/Users/deepan_roy/Desktop/DoPY/Roll1/2000R366.JPG", 7, now(), 15, 2);
insert into photos (PHOTO_ID, ROLL_NUM, DRIVE_LINK, FRAME_NUM, DATE_TIME, PRICE, CLUB_DEPT) values (8, 1, "/Users/deepan_roy/Desktop/DoPY/Roll1/2000R370.JPG", 8, now(), 15, 2);
insert into photos (PHOTO_ID, ROLL_NUM, DRIVE_LINK, FRAME_NUM, DATE_TIME, PRICE, CLUB_DEPT) values (9, 1, "/Users/deepan_roy/Desktop/DoPY/Roll1/2000R372.JPG", 9, now(), 15, NULL);
insert into photos (PHOTO_ID, ROLL_NUM, DRIVE_LINK, FRAME_NUM, DATE_TIME, PRICE, CLUB_DEPT) values (10, 1, "/Users/deepan_roy/Desktop/DoPY/Roll1/2000R378.JPG", 10, now(), 15, NULL);
insert into photos (PHOTO_ID, ROLL_NUM, DRIVE_LINK, FRAME_NUM, DATE_TIME, PRICE, CLUB_DEPT) values (11, 1, "/Users/deepan_roy/Desktop/DoPY/Roll1/2000R382.JPG", 11, now(), 15, NULL);
insert into photos (PHOTO_ID, ROLL_NUM, DRIVE_LINK, FRAME_NUM, DATE_TIME, PRICE, CLUB_DEPT) values (12, 1, "/Users/deepan_roy/Desktop/DoPY/Roll1/2000R383.JPG", 12, now(), 15, 1);
insert into photos (PHOTO_ID, ROLL_NUM, DRIVE_LINK, FRAME_NUM, DATE_TIME, PRICE, CLUB_DEPT) values (13, 1, "/Users/deepan_roy/Desktop/DoPY/Roll1/2000R384.JPG", 13, now(), 15, NULL);



update admin set password = admin_id where admin_id > 0;

select * from admin;
select id from student where id = "2022A7PS0084" and password = "2022A7PS0084";
insert into tagged_in (photo_id, s_id) values (1, "2022A7PS0084"), (2, "2022A7PS0084"), (3, "2022A7PS0084");

-- add password
insert into por (c_id, s_id, password) values 
(1, "2022A7PS0180", "password1"),
(2, "2022A7PS0084", "password2");

-- deliveries
-- view deliveries table
select * from deliveries;
-- add delivery
-- admin_id, hostel_name, room_num come as input 
insert into deliveries (admin_id, hostel_name, room_num) values (1, "SK", 4133);
-- remove delivery 
-- admin_id, hostel_name, room_num come as input 
delete from deliveries where admin_id = 1 and hostel_name = "SK" and room_num = 4133;

insert into control_desk (clicker_id, detailer_id, photo_id, camera_id) values
(1, 2, 1, 1),
(3, 4, 2, 2),
(1, 2, 3, 3);
insert into control_desk (clicker_id, detailer_id, photo_id, camera_id) values (1, 2, 4, 1);

insert into club_dept (id, name) values (3, "Coding Club");

-- selecting from tables;
select * from student;
select * from club_dept;
select * from HOSTELS;
select * from ADMIN;
select * from PHOTOS;
select * from CAMERA;
select * from CAMERA_LIST;
select * from LIVES_IN;
select * from CAMERA_TYPE;
select * from TAGGED_IN;
select * from CONTROL_DESK;
select * from MEMBERSHIP;
select * from POR;
select * from DELIVERIES;

-- insert into POR
insert into POR (c_id, s_id, password) values (4, "2022A7PS0001", "2022A7PS0001");
insert into POR (c_id, s_id, password) values (5, "2022A7PS0002", "2022A7PS0002");
insert into POR (c_id, s_id, password) values (6, "2022A7PS0003", "2022A7PS0003");
insert into POR (c_id, s_id, password) values (7, "2022A7PS0004", "2022A7PS0004");
insert into POR (c_id, s_id, password) values (8, "2022A7PS0005", "2022A7PS0005");
insert into POR (c_id, s_id, password) values (9, "2022A7PS0006", "2022A7PS0006");
insert into POR (c_id, s_id, password) values (10, "2022A7PS0007", "2022A7PS0007");
insert into POR (c_id, s_id, password) values (11, "2022A7PS0008", "2022A7PS0008");
insert into POR (c_id, s_id, password) values (12, "2022A7PS0009", "2022A7PS0009");
insert into POR (c_id, s_id, password) values (13, "2022A7PS0010", "2022A7PS0010");
insert into POR (c_id, s_id, password) values (14, "2022A7PS0011", "2022A7PS0011");
insert into POR (c_id, s_id, password) values (15, "2022A7PS0012", "2022A7PS0012");
insert into POR (c_id, s_id, password) values (16, "2022A7PS0084", "password2");


insert into club_dept (id, name) values (4, "Department of Sponsorship and Marketing");
insert into club_dept (id, name) values (5, "Department of Informalz");
insert into club_dept (id, name) values (6, "Basketball Team");
insert into club_dept (id, name) values (7, "Anant");
insert into club_dept (id, name) values (8, "CRISS Robotics");
insert into club_dept (id, name) values (9, "Embryo");


insert into club_dept (id, name) values (17, "Gurukul");
insert into club_dept (id, name) values (18, "CEL");
insert into club_dept (id, name) values (19, "Postman API");
insert into club_dept (id, name) values (20, "180 DC");

insert into club_dept (id, name) values (10, "Bosm Sponz");
insert into club_dept (id, name) values (11, "DVM");
insert into club_dept (id, name) values (12, "Pcra");
insert into club_dept (id, name) values (13, "Music Club");
insert into club_dept (id, name) values (14, "Sovesa");
insert into club_dept (id, name) values (15, "Recnacc");
insert into club_dept (id, name) values (16, "REC");

(select 1, hostel_name, room_num from lives_in where s_id in (select s_id from tagged_in) and hostel_name = 'VK') ;

insert into deliveries values (1, "SK", 4133);
insert into deliveries ((select 1, hostel_name, room_num from lives_in where s_id in (select s_id from tagged_in) and hostel_name = 'VK'));
insert into deliveries ((select 2, hostel_name, room_num from lives_in where s_id in (select s_id from tagged_in) and hostel_name = 'SR'));