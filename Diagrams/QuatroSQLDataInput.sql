--INSERT INTO Course
--VALUES (N'TestCourse1', N'Subject1', N'IntroText1', N'Beginner'),
--(N'TestCourse2', N'Subject2', N'IntroText2', N'Beginner'),
--(N'TestCourse3', N'Subject3', N'IntroText3', N'Beginner'),
--(N'TestCourse4', N'Subject4', N'IntroText4', N'Beginner'),
--(N'TestCourse5', N'Subject5', N'IntroText5', N'Beginner');

--INSERT INTO InterestingToCourse
--VALUES (N'TestCourse1', N'TestCourse2'),
--(N'TestCourse2', N'TestCourse3'),
--(N'TestCourse3', N'TestCourse4'),
--(N'TestCourse4', N'TestCourse5'),
--(N'TestCourse5', N'TestCourse1');

--INSERT INTO Certificate
--VALUES 
--(N'Jan', 7.5),
--(N'Geoffrey', 9),
--(N'Wassim', 1),
--(N'Eefje', 7),
--(N'Tippe', 10);

--INSERT INTO Module
--VALUES 
--(N'Module1', 1, N'Kees', N'bla@live.com', N'TestCourse1', 1),
--(N'Module2', 1.5, N'Henk', N'ik@hotmail.com', N'TestCourse1', 2),
--(N'Module3', 1, N'Marja', N'test@gmail.com', N'TestCourse2', 1),
--(N'Module4', 2, N'Gerda', N'ja@aol.com', N'TestCourse3', 1),
--(N'Module5', 3, N'Sarah', N'nee@yahoo.com', N'TestCourse4', 1),
--(N'Module6', 2, N'Lars', N'lars@live.com', N'TestCourse2', 2),
--(N'Module7', 1.3, N'Karin', N'karin@hotmail.com', N'TestCourse2', 3);

--INSERT INTO Webcast
--VALUES 
--(N'Webcast1', 11, N'Kees', N'David van der Steen', N'Unilever'),
--(N'Webcast2', 15, N'Henk', N'Pim van der Spek', N'ASML'),
--(N'Webcast3', 23, N'Marja', N'Pieter Vos', N'Shell'),
--(N'Webcast4', 7, N'Gerda', N'Stefan Steen', N'Vodafone'),
--(N'Webcast5', 29, N'Sarah', N'Kees Naaktgeboren', N'KLM');

--INSERT INTO ContentItem
--VALUES 
--(N'Active', N'Description4', '11-04-2020', 1, null),
--(N'Concept', N'Description5', '02-10-2020', null, 5),
--(N'Active', N'Description1', '01-01-2020', null, 1),
--(N'Archived', N'Description2', '01-01-2021', 2, null),
--(N'Active', N'Description3', '12-08-2020', null, 2),
--(N'Active', N'Description6', '11-04-2020', 3, null),
--(N'Active', N'Description7', '02-19-2020', 6, null),
--(N'Active', N'Description8', '02-10-2020', 7, null),
--(N'Active', N'Description9', '08-19-2020', 4, null),
--(N'Active', N'Description10', '07-11-2020', 5, null),
--(N'Active', N'Description11', '10-29-2018', null, 3),
--(N'Active', N'Description12', '07-31-2017', null, 4);

--INSERT INTO Student
--VALUES 
--(N'student@live.com', N'Michael', N'Hovenier', '01-31-1995', N'M', N'Gekkenstraat', N'5B', N'1358BD', N'Almere', N'Nederland'),
--(N'student2@live.com', N'Lotte', N'van der Heijden', '03-15-1998', N'F', N'Gestoordenstraat', N'80', N'2314BD', N'Utrecht', N'Nederland');

--INSERT INTO Signup
--VALUES 
--('01-01-2018', N'TestCourse1', N'student@live.com', null),
--('01-05-2019', N'TestCourse2', N'student2@live.com', 5);

--INSERT INTO Progress
--VALUES 
--(N'student@live.com', 1, 100),
--(N'student@live.com', 4, 12),
--(N'student@live.com', 6, 50),
--(N'student@live.com', 7, 66),
--(N'student@live.com', 8, 12),
--(N'student@live.com', 2, 70),
--(N'student@live.com', 3, 30),
--(N'student2@live.com', 1, 50),
--(N'student2@live.com', 4, 1),
--(N'student2@live.com', 6, 100),
--(N'student2@live.com', 7, 97),
--(N'student2@live.com', 8, 37),
--(N'student2@live.com', 2, 35),
--(N'student2@live.com', 5, 42);

SELECT *
FROM Course;

SELECT *
FROM InterestingToCourse;

SELECT *
FROM Certificate;

SELECT *
FROM Module;

SELECT *
FROM Webcast;

SELECT *
FROM ContentItem;

SELECT *
FROM Student;

SELECT *
FROM Signup;

SELECT *
FROM Progress

--Voor een geselecteerde cursus, geef per module de gemiddelde voortgang in percentage van de totale lengte, voor alle accounts.
SELECT Name AS Course, Module.ModuleID, AVG(ContentPerc) AS AverageProgress
FROM Course
	JOIN Module
	ON Course.Name = Module.Course
	JOIN ContentItem
	ON Module.ModuleID = ContentItem.ModuleID
	JOIN Progress
	ON ContentItem.ContentItemID = Progress.ContentItemID
WHERE Course.Name = 'TestCourse2'
GROUP BY Name, Module.ModuleID;

--Voor een geselecteerd account en geselecteerde cursus, geef per module de voortgang als percentage.
SELECT Email AS Account, Name AS Course, Module.ModuleID, ContentPerc AS Progress
FROM Progress
	JOIN ContentItem
	ON Progress.ContentItemID = ContentItem.ContentItemID
	JOIN Module
	ON ContentItem.ModuleID = Module.ModuleID
	JOIN Course
	ON Module.Course = Course.Name
WHERE Progress.Email = 'student@live.com' AND Course.Name = 'TestCourse1';

--Geef een top 3 van meest bekeken webcasts.
SELECT TOP 3 Webcast.WebcastID, Webcast.Title, COUNT(Progress.ContentItemID) AS Viewed
FROM Webcast
	JOIN ContentItem
	ON Webcast.WebcastID = ContentItem.WebcastID
	JOIN Progress
	ON ContentItem.ContentItemID = Progress.ContentItemID
GROUP BY Webcast.WebcastID, Webcast.Title
ORDER BY Viewed DESC;

--Voor een geselecteerde cursus, geef hoeveel cursisten deze in het geheel behaald hebben.
SELECT Course.Name AS Course, COUNT(Signup.CertificateID) AS Passed
FROM Course 
	JOIN Signup
	ON Course.Name = Signup.Course
WHERE Course.Name = 'TestCourse2'
GROUP BY Name;
-------------------------------------------------------------------------------------
--Voor een cursist kan aangegeven worden wat de voortgang in een module is.
--Wanneer er al een modulerecord in progress is:
--(Te kiezen uit modules)
SELECT Progress.*
FROM Progress
	JOIN ContentItem
	ON Progress.ContentItemID = ContentItem.ContentItemID
	JOIN Module
	ON ContentItem.ModuleID = Module.ModuleID
WHERE Email = 'student@live.com';
--(Update de gekozen module)
UPDATE Progress
SET ContentPerc = 100
WHERE Email = 'student@live.com' AND ContentItemID = 1;

--Wanneer er geen modulerecord in progress is:
--(Te kiezen uit modules)
SELECT Title
FROM Module
WHERE Title NOT IN (
SELECT Title
FROM Progress
	JOIN ContentItem
	ON Progress.ContentItemID = ContentItem.ContentItemID
	JOIN Module
	ON ContentItem.ModuleID = Module.ModuleID
WHERE Email = 'student@live.com');
--(Insert de gekozen module)
INSERT INTO Progress
VALUES ('student@live.com', 1 , 100);
-------------------------------------------------------------------------------------
--Voor een cursus kan aangegeven worden hoeveel procent van een webcast bekeken is.
--Wanneer er al een webcastrecord in progress is:
--(Te kiezen uit webcasts)
SELECT Progress.*
FROM Progress
	JOIN ContentItem
	ON Progress.ContentItemID = ContentItem.ContentItemID
	JOIN Webcast
	ON ContentItem.WebcastID = Webcast.WebcastID
WHERE Email = 'student@live.com';
--(Update de gekozen webcast)
UPDATE Progress
SET ContentPerc = 100
WHERE Email = 'student@live.com' AND ContentItemID = 2;

--Wanneer er geen webcastrecord in progress is, kiezen uit:
--(Te kiezen uit webcasts)
SELECT *
FROM Webcast
WHERE Title NOT IN (
SELECT Title
FROM Progress
	JOIN ContentItem
	ON Progress.ContentItemID = ContentItem.ContentItemID
	JOIN Webcast
	ON ContentItem.WebcastID = Webcast.WebcastID
WHERE Email = 'student@live.com');
--(Insert de gekozen webcast)
INSERT INTO Progress
VALUES ('student@live.com', 5 , 100);

--DBCC CHECKIDENT ('[ContentItem]', RESEED, 0);
--DELETE FROM ContentItem;