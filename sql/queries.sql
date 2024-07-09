-- STUDENT
-- view clubs/depts
-- student ID is same as the ID you logged in with
select name from club_dept inner join membership on membership.s_id = "2022A7PS0004" and membership.c_id = club_dept.id;

-- view club/depts photos
select name from club_dept inner join membership on membership.c_id = club_dept.id where membership.s_id = "2022A7PS0004";
select drive_link from photos inner join club_dept on photos.club_dept = club_dept.id where club_dept.id = 2;
select drive_link from photos inner join tagged_in on photos.photo_id = tagged_in.photo_id where s_id = "2022A7PS0084";

-- bill
-- student ID is same as the ID you logged in with
select count(photo_id) as num from tagged_in where tagged_in.s_id = "2022A7PS0084";

-- POR
-- query to check password
select name from por inner join club_dept on por.c_id = club_dept.id where s_id = "2022A7PS0180" and password = "password1";

-- view clubs/depts you're a POR of
-- student ID is same as the ID you logged in with
select name from club_dept inner join POR on POR.s_id = "2022A7PS0004" and POR.c_id = club_dept.id;
-- parse through list of club_dept IDs using a Java array or sm
-- select file paths of selected club/dept
select DRIVE_LINK from photos where club_dept = (select id from club_dept where name = "Dance Club");

-- edit clubs/depts
-- view clubs/depts list
-- c_id will come from the button they clicked
select name, id from student inner join membership on student.id = membership.s_id where membership.c_id = (select id from club_dept where name = "Dance Club");

-- add
-- c_id will come from the button they clicked; s_id will be input
insert into membership (c_id, s_id) values ((select id from club_dept where name = "Dance Club"), "2022B5PS0726");
delete from membership where c_id = (select id from club_dept where name = "Dance Club") and s_id = "2022B5PS0726";

-- remove 
-- c_id will come from the button they clicked; s_id will be input
delete from membership where s_id = "2022B5PS0726";

-- ADMIN
-- select * from admin where admin_id = (select max(admin_id) from admin);
-- control desk
-- viewing camera log
select control_desk.clicker_id, control_desk.detailer_id, photos.roll_num, control_desk.camera_id, photos.date_time 
from control_desk inner join photos on control_desk.photo_id = photos.photo_id
where photos.date_time in (
select max(photos.date_time) 
from control_desk inner join photos on control_desk.photo_id = photos.photo_id
group by camera_id)
order by date_time desc;

-- viewing photo log
select photos.PHOTO_ID, photos.ROLL_NUM, photos.DRIVE_LINK, photos.FRAME_NUM, photos.DATE_TIME,
 control_desk.clicker_id, control_desk.detailer_id, control_desk.camera_id
from control_desk inner join photos on control_desk.photo_id = photos.photo_id;
-- tagging
-- tagging student ID w/ student IDs
-- student ID is user input, photo ID is the current photo the user is viewing
insert into tagged_in (s_id, photo_id) values ("2022A7PS00012", 1);
insert into tagged_in (s_id, photo_id) values ("2022A7PS0012", (select photo_id from photos where DRIVE_LINK = '/Users/deepan_roy/Desktop/DoPY/Roll1/2000R07.JPG'));
-- viewing tagged students in a photo
select s_id from tagged_in where photo_id in (select PHOTO_ID from photos where DRIVE_LINK = '/Users/deepan_roy/Desktop/DoPY/Roll1/2000R07.JPG'); 
-- remove a tagged student from a photo
delete from tagged_in where s_id = "2022A7PS0012" and photo_id in (select PHOTO_ID from photos where DRIVE_LINK = '/Users/deepan_roy/Desktop/DoPY/Roll1/2000R07.JPG') ;
