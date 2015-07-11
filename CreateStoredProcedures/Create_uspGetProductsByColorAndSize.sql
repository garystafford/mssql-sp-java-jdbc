USE [AdventureWorks]
GO

/****** Object:  StoredProcedure [Production].[uspGetProductsByColorAndSize]    Script Date: 08/25/2012 09:29:30 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =====================================================
-- Author:		Gary A. Stafford
-- Create date: 08/20/2012
-- Description:	Returns a list of products
--				based on color and size.
-- =====================================================
CREATE PROCEDURE [Production].[uspGetProductsByColorAndSize] 
	@productColor VARCHAR(20),
	@productSize INTEGER
AS
BEGIN
	SET NOCOUNT ON;

	SELECT p.Name AS [Product], p.ProductNumber, p.Color, p.Size, m.Name AS [Model]
	FROM Production.ProductModel AS m INNER JOIN
		Production.Product AS p ON m.ProductModelID = p.ProductModelID
	WHERE (p.Color = @productColor) AND (p.Size = @productSize)
	ORDER BY [Model], [Product]
END


GO


