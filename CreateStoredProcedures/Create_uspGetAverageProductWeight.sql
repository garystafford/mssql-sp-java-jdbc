USE [AdventureWorks]
GO

/****** Object:  StoredProcedure [dbo].[uspGetAverageProductWeight]    Script Date: 08/25/2012 09:29:05 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =====================================================
-- Author:		Gary A. Stafford
-- Create date: 08/21/2012
-- Description:	Returns average weight of all products
--				where unit of weight is pounds.
-- =====================================================
CREATE PROCEDURE [dbo].[uspGetAverageProductWeight] 
AS
BEGIN
	SET NOCOUNT ON;

	SELECT ROUND(AVG([Weight]), 2) 
	FROM Production.Product
	WHERE ([Weight] > 0) AND (WeightUnitMeasureCode = 'LB')
END

GO


