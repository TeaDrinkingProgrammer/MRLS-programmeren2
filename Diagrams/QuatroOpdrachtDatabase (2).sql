USE [master]
GO
/****** Object:  Database [QuatroOpdracht]    Script Date: 13/12/2020 19:08:02 ******/
CREATE DATABASE [QuatroOpdracht]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QuatroOpdracht', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLDEV2019\MSSQL\DATA\QuatroOpdracht.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'QuatroOpdracht_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLDEV2019\MSSQL\DATA\QuatroOpdracht_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [QuatroOpdracht] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QuatroOpdracht].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QuatroOpdracht] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QuatroOpdracht] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QuatroOpdracht] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QuatroOpdracht] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QuatroOpdracht] SET ARITHABORT OFF 
GO
ALTER DATABASE [QuatroOpdracht] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [QuatroOpdracht] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QuatroOpdracht] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QuatroOpdracht] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QuatroOpdracht] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QuatroOpdracht] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QuatroOpdracht] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QuatroOpdracht] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QuatroOpdracht] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QuatroOpdracht] SET  DISABLE_BROKER 
GO
ALTER DATABASE [QuatroOpdracht] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QuatroOpdracht] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QuatroOpdracht] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QuatroOpdracht] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QuatroOpdracht] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QuatroOpdracht] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QuatroOpdracht] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QuatroOpdracht] SET RECOVERY FULL 
GO
ALTER DATABASE [QuatroOpdracht] SET  MULTI_USER 
GO
ALTER DATABASE [QuatroOpdracht] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QuatroOpdracht] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QuatroOpdracht] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QuatroOpdracht] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [QuatroOpdracht] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'QuatroOpdracht', N'ON'
GO
ALTER DATABASE [QuatroOpdracht] SET QUERY_STORE = OFF
GO
USE [QuatroOpdracht]
GO
/****** Object:  Table [dbo].[ContentItem]    Script Date: 13/12/2020 19:08:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ContentItem](
	[ContentItemID] [int] IDENTITY(1,1) NOT NULL,
	[Status] [nvarchar](50) NULL,
	[Description] [nvarchar](50) NULL,
	[PublicationDate] [nchar](10) NULL,
	[WebcastID] [int] NOT NULL,
	[ModuleID] [int] NOT NULL,
 CONSTRAINT [PK_Content] PRIMARY KEY CLUSTERED 
(
	[ContentItemID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Course]    Script Date: 13/12/2020 19:08:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Course](
	[Name] [nvarchar](50) NOT NULL,
	[Subject] [nvarchar](30) NULL,
	[IntroText] [nvarchar](100) NULL,
	[Level] [nvarchar](20) NULL,
 CONSTRAINT [PK_Course] PRIMARY KEY CLUSTERED 
(
	[Name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CourseModules]    Script Date: 13/12/2020 19:08:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CourseModules](
	[CourseName] [nvarchar](50) NOT NULL,
	[ModuleTitle] [nvarchar](50) NOT NULL,
	[ModuleVersion] [int] NOT NULL,
 CONSTRAINT [PK_Course_modules] PRIMARY KEY CLUSTERED 
(
	[CourseName] ASC,
	[ModuleTitle] ASC,
	[ModuleVersion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[InterestingToCourse]    Script Date: 13/12/2020 19:08:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[InterestingToCourse](
	[CourseName] [nvarchar](50) NOT NULL,
	[InterestingCourseName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Interesting_to_course_1] PRIMARY KEY CLUSTERED 
(
	[CourseName] ASC,
	[InterestingCourseName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Module]    Script Date: 13/12/2020 19:08:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Module](
	[ModuleID] [int] IDENTITY(1,1) NOT NULL,
	[Title] [nvarchar](50) NOT NULL,
	[Version] [int] NOT NULL,
	[ContactName] [nvarchar](50) NULL,
	[ContactEmail] [nvarchar](50) NULL,
	[FollowUpNumber] [int] NOT NULL,
 CONSTRAINT [PK_Module_1] PRIMARY KEY CLUSTERED 
(
	[ModuleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Signup]    Script Date: 13/12/2020 19:08:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Signup](
	[Signupdate] [date] NOT NULL,
	[CourseName] [nvarchar](50) NOT NULL,
	[StudentEmail] [nvarchar](50) NOT NULL,
	[EmployeeName] [nvarchar](50) NULL,
	[Grade] [float] NULL,
	[CertificateID] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_Signup] PRIMARY KEY CLUSTERED 
(
	[Signupdate] ASC,
	[StudentEmail] ASC,
	[CourseName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Student]    Script Date: 13/12/2020 19:08:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Student](
	[Email] [nvarchar](50) NOT NULL,
	[FirstName] [nvarchar](50) NOT NULL,
	[LastName] [nvarchar](50) NOT NULL,
	[DOB] [date] NOT NULL,
	[Gender] [nchar](1) NOT NULL,
	[Street] [nvarchar](50) NOT NULL,
	[Housenumber] [nvarchar](6) NOT NULL,
	[City] [nvarchar](50) NOT NULL,
	[Country] [nvarchar](10) NOT NULL,
 CONSTRAINT [PK_Student] PRIMARY KEY CLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Views]    Script Date: 13/12/2020 19:08:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Views](
	[Email] [nvarchar](50) NOT NULL,
	[ContentID] [int] NOT NULL,
	[ContentPerc] [int] NULL,
 CONSTRAINT [PK_Views] PRIMARY KEY CLUSTERED 
(
	[Email] ASC,
	[ContentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Webcast]    Script Date: 13/12/2020 19:08:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Webcast](
	[WebcastID] [int] IDENTITY(1,1) NOT NULL,
	[Title] [nvarchar](50) NOT NULL,
	[Duration] [int] NOT NULL,
	[URL] [nvarchar](50) NULL,
	[SpeakerName] [nvarchar](50) NULL,
	[Organization] [nvarchar](50) NULL,
 CONSTRAINT [PK_Webcast_1] PRIMARY KEY CLUSTERED 
(
	[WebcastID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Index [IX_Content]    Script Date: 13/12/2020 19:08:03 ******/
CREATE UNIQUE NONCLUSTERED INDEX [IX_Content] ON [dbo].[ContentItem]
(
	[ContentItemID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [AK_Module]    Script Date: 13/12/2020 19:08:03 ******/
CREATE UNIQUE NONCLUSTERED INDEX [AK_Module] ON [dbo].[Module]
(
	[Title] ASC,
	[Version] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [AK2_Module]    Script Date: 13/12/2020 19:08:03 ******/
CREATE NONCLUSTERED INDEX [AK2_Module] ON [dbo].[Module]
(
	[FollowUpNumber] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [AK_Certificate]    Script Date: 13/12/2020 19:08:03 ******/
CREATE UNIQUE NONCLUSTERED INDEX [AK_Certificate] ON [dbo].[Signup]
(
	[CertificateID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [AK_Webcast]    Script Date: 13/12/2020 19:08:03 ******/
CREATE UNIQUE NONCLUSTERED INDEX [AK_Webcast] ON [dbo].[Webcast]
(
	[Title] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[ContentItem]  WITH CHECK ADD  CONSTRAINT [FK_Content_Content] FOREIGN KEY([ModuleID])
REFERENCES [dbo].[Module] ([ModuleID])
GO
ALTER TABLE [dbo].[ContentItem] CHECK CONSTRAINT [FK_Content_Content]
GO
ALTER TABLE [dbo].[ContentItem]  WITH CHECK ADD  CONSTRAINT [FK_Content_Webcast] FOREIGN KEY([WebcastID])
REFERENCES [dbo].[Webcast] ([WebcastID])
GO
ALTER TABLE [dbo].[ContentItem] CHECK CONSTRAINT [FK_Content_Webcast]
GO
ALTER TABLE [dbo].[CourseModules]  WITH CHECK ADD  CONSTRAINT [FK_Course_modules_Course] FOREIGN KEY([CourseName])
REFERENCES [dbo].[Course] ([Name])
GO
ALTER TABLE [dbo].[CourseModules] CHECK CONSTRAINT [FK_Course_modules_Course]
GO
ALTER TABLE [dbo].[CourseModules]  WITH CHECK ADD  CONSTRAINT [FK_CourseModules_CourseModules] FOREIGN KEY([ModuleTitle], [ModuleVersion])
REFERENCES [dbo].[Module] ([Title], [Version])
GO
ALTER TABLE [dbo].[CourseModules] CHECK CONSTRAINT [FK_CourseModules_CourseModules]
GO
ALTER TABLE [dbo].[InterestingToCourse]  WITH CHECK ADD  CONSTRAINT [FK_Interesting_to_course_Course] FOREIGN KEY([InterestingCourseName])
REFERENCES [dbo].[Course] ([Name])
GO
ALTER TABLE [dbo].[InterestingToCourse] CHECK CONSTRAINT [FK_Interesting_to_course_Course]
GO
ALTER TABLE [dbo].[InterestingToCourse]  WITH CHECK ADD  CONSTRAINT [FK_Interesting_to_course_Course2] FOREIGN KEY([CourseName])
REFERENCES [dbo].[Course] ([Name])
GO
ALTER TABLE [dbo].[InterestingToCourse] CHECK CONSTRAINT [FK_Interesting_to_course_Course2]
GO
ALTER TABLE [dbo].[Signup]  WITH CHECK ADD  CONSTRAINT [FK_Signup_Signup] FOREIGN KEY([CourseName])
REFERENCES [dbo].[Course] ([Name])
GO
ALTER TABLE [dbo].[Signup] CHECK CONSTRAINT [FK_Signup_Signup]
GO
ALTER TABLE [dbo].[Signup]  WITH CHECK ADD  CONSTRAINT [FK_Signup_Student] FOREIGN KEY([StudentEmail])
REFERENCES [dbo].[Student] ([Email])
GO
ALTER TABLE [dbo].[Signup] CHECK CONSTRAINT [FK_Signup_Student]
GO
ALTER TABLE [dbo].[Views]  WITH CHECK ADD  CONSTRAINT [FK_Views_Content] FOREIGN KEY([ContentID])
REFERENCES [dbo].[ContentItem] ([ContentItemID])
GO
ALTER TABLE [dbo].[Views] CHECK CONSTRAINT [FK_Views_Content]
GO
ALTER TABLE [dbo].[Views]  WITH CHECK ADD  CONSTRAINT [FK_Views_Student] FOREIGN KEY([Email])
REFERENCES [dbo].[Student] ([Email])
GO
ALTER TABLE [dbo].[Views] CHECK CONSTRAINT [FK_Views_Student]
GO
ALTER TABLE [dbo].[ContentItem]  WITH CHECK ADD  CONSTRAINT [CK_Content] CHECK  (([WebcastID] IS NOT NULL AND [ModuleID] IS NULL OR [ModuleID] IS NOT NULL AND [WebcastID] IS NULL))
GO
ALTER TABLE [dbo].[ContentItem] CHECK CONSTRAINT [CK_Content]
GO
ALTER TABLE [dbo].[ContentItem]  WITH CHECK ADD  CONSTRAINT [CK_Content_Status] CHECK  (([Status]='concept' OR [Status]='active' OR [Status]='archived'))
GO
ALTER TABLE [dbo].[ContentItem] CHECK CONSTRAINT [CK_Content_Status]
GO
ALTER TABLE [dbo].[Course]  WITH CHECK ADD  CONSTRAINT [CK_Course_Level] CHECK  (([Level]='beginner' OR [Level]='gevorderd' OR [Level]='expert'))
GO
ALTER TABLE [dbo].[Course] CHECK CONSTRAINT [CK_Course_Level]
GO
ALTER TABLE [dbo].[Student]  WITH CHECK ADD  CONSTRAINT [CK_Student_Gender] CHECK  (([Gender]='M' OR [Gender]='F'))
GO
ALTER TABLE [dbo].[Student] CHECK CONSTRAINT [CK_Student_Gender]
GO
USE [master]
GO
ALTER DATABASE [QuatroOpdracht] SET  READ_WRITE 
GO
