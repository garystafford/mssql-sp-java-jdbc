USE [AdventureWorks]
GO

/****** Object:  StoredProcedure [HumanResources].[uspGetEmployeesByLastName]    Script Date: 08/25/2012 09:29:23 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO



-- =====================================================
-- Author:		Gary A. Stafford
-- Create date: 08/20/2012
-- Description:	Returns a list of all employee names 
--				whose last name is starts with value.
-- =====================================================
CREATE PROCEDURE [HumanResources].[uspGetEmployeesByLastName] 
	@lastNameStartsWith VARCHAR(20) = 'A'
AS
BEGIN
	SET NOCOUNT ON;

	SELECT Title, FirstName, MiddleName, LastName, Suffix
	FROM Person.Person
	WHERE (PersonType = 'EM') AND (LastName LIKE @lastNameStartsWith + '%')
END


GO


