select * from Course where CourseId in (select distinct CourseId from TeacherTeaching where TeacherId=95013);
select * from Student where StuId in (select StuId from ChooseCourse where CourseId = 2)
select TeacherId from Teacher where TeacherName='林莉';
select * from Course where CourseId in (select distinct CourseId from TeacherTeaching where TeacherId=95001);
select CourseId from Course where CourseName='WEB程序设计';
select CourseName from Course where CourseId in (select distinct CourseId from TeacherTeaching where TeacherId=95001);
select * from Student where StuId in (select StuId from ChooseCourse where CourseId = 'WEB程序设计');
select * from Student where StuId in (select StuId from ChooseCourse where CourseName = ?)

select * from Student where StuId in (select StuId from ChooseCourse where CourseId = 'WEB程序设计')

select a.AcademyName,s.StuId,s.StuName,s.StuSex,s.Birthday,cc.score from ChooseCourse cc
inner join Student s on s.StuId=cc.StuId 
inner join Academy a on a.AcademyId = s.AcademyId
where CourseId=1;
go

create view view_ShowStudentDetailInfo
as
select a.AcademyName,s.StuId,s.StuName,s.StuSex,s.Birthday,cc.score from ChooseCourse cc
inner join Student s on s.StuId=cc.StuId 
inner join Academy a on a.AcademyId = s.AcademyId
where CourseId=1;
go

select * from view_ShowStudentDetailInfo;

drop view view_ShowStudentDetailInfo;
go

drop proc proc_ShowStudentDetailInfo
go

create proc proc_ShowStudentDetailInfo
@courseId int
as
select a.AcademyName,s.StuId,s.StuName,s.StuSex,s.Birthday,cc.score from ChooseCourse cc
inner join Student s on s.StuId=cc.StuId 
inner join Academy a on a.AcademyId = s.AcademyId
where CourseId=@courseId;
go

declare @courseId int
set @courseId=1;
exec proc_ShowStudentDetailInfo @courseId;
go

