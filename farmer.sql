DROP DATABASE farmerdata;
CREATE SCHEMA farmerdata;
USE farmerdata;

BULK INSERT farmers
FROM 'Export.csv'
WITH
(
    FIRSTROW = 2, -- as 1st one is header
    FIELDTERMINATOR = ',',  --CSV field delimiter
    ROWTERMINATOR = '\n',   --Use to shift the control to next row
    TABLOCK
)
