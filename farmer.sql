DROP DATABASE farmerdata;
CREATE SCHEMA farmerdata;
USE farmerdata;

FLUSH privileges;

SHOW GLOBAL VARIABLES LIKE 'local_infile';
SET GLOBAL local_infile = 'ON';
SHOW GLOBAL VARIABLES LIKE 'local_infile';

USE farmerdata;

CREATE TABLE `farmers` (
  `id` INT NOT NULL,
  `name` VARCHAR(255) DEFAULT NULL,
  `website` VARCHAR(255) DEFAULT NULL,
  `facebook` VARCHAR(255) DEFAULT NULL,
  `twitter` VARCHAR(255) DEFAULT NULL,
  `youtube` VARCHAR(255) DEFAULT NULL, 
  `othermedia` VARCHAR(255) DEFAULT NULL,
  `street` VARCHAR(255) DEFAULT NULL,
  `city` VARCHAR(255) DEFAULT NULL,
  `county` VARCHAR(255) DEFAULT NULL,
  `state`  VARCHAR(255) DEFAULT NULL,
  `zip` VARCHAR(255) DEFAULT NULL,
  `season1date` VARCHAR(255) DEFAULT NULL,
  `season1time` VARCHAR(255) DEFAULT NULL,
  `season2date` VARCHAR(255) DEFAULT NULL,
  `season2time` VARCHAR(255) DEFAULT NULL,
  `season3date` VARCHAR(255) DEFAULT NULL,
  `season3time` VARCHAR(255) DEFAULT NULL,
  `season4date` VARCHAR(255) DEFAULT NULL,
  `season4time` VARCHAR(255) DEFAULT NULL,
  `x` INT NOT NULL,
  `y` INT NOT NULL,
  `location` VARCHAR(255) DEFAULT NULL ,
  `credit` VARCHAR(255) DEFAULT NULL,
  `wic` VARCHAR(255) DEFAULT NULL,
  `wiccash` VARCHAR(255) DEFAULT NULL,
  `sfmnp` VARCHAR(255) DEFAULT NULL,
  `snap`VARCHAR(255) DEFAULT NULL,
  `organic` BOOLEAN DEFAULT NULL,
  `bakedgoods` BOOLEAN DEFAULT NULL,
  `cheese` BOOLEAN DEFAULT NULL,
  `crafts` BOOLEAN DEFAULT NULL,
  `flowers` BOOLEAN DEFAULT NULL,
  `eggs` BOOLEAN DEFAULT NULL,
  `seafood` BOOLEAN DEFAULT NULL,
  `herbs` BOOLEAN DEFAULT NULL,
  `vegetables` BOOLEAN DEFAULT NULL,
  `honey` BOOLEAN DEFAULT NULL,
  `jams` BOOLEAN DEFAULT NULL,
  `maple` BOOLEAN DEFAULT NULL,
  `meat` BOOLEAN DEFAULT NULL,
  `nursery` BOOLEAN DEFAULT NULL,
  `nuts` BOOLEAN DEFAULT NULL,
  `plants` BOOLEAN DEFAULT NULL,
  `poultry` BOOLEAN DEFAULT NULL,
  `prepared` BOOLEAN DEFAULT NULL,
  `soap` BOOLEAN DEFAULT NULL,
  `trees` BOOLEAN DEFAULT NULL,
  `wine` BOOLEAN DEFAULT NULL,
  `coffee` BOOLEAN DEFAULT NULL,
  `beans` BOOLEAN DEFAULT NULL,
  `fruits` BOOLEAN DEFAULT NULL,
  `grains` BOOLEAN DEFAULT NULL,
  `juices` BOOLEAN DEFAULT NULL,
  `mushrooms` BOOLEAN DEFAULT NULL,
  `petfood` BOOLEAN DEFAULT NULL,
  `tofu` BOOLEAN DEFAULT NULL,
  `wildharvested` BOOLEAN DEFAULT NULL,
  `updatetime` VARCHAR(255) DEFAULT NULL
);
-- ["SET "+part1[i]+" = (@var"+str(i+1)+" = \'Y\');\n" for i in range(len(part1))]
SET GLOBAL local_infile = 'ON';

LOAD DATA LOCAL INFILE '/home/laptopadmin/Downloads/Export.csv' INTO TABLE farmers 
FIELDS TERMINATED BY ',' ENCLOSED BY '\"'
LINES TERMINATED BY '\n' (id, name,
  website,facebook,twitter,youtube,othermedia,street,city,county,state,zip,season1date,season1time,season2date,season2time,season3date,season3time,season4date,season4time,x,y,location,credit,wic,wiccash,sfmnp,snap
, @var1, @var2, @var3, @var4, @var5, @var6, @var7, @var8, @var9, @var10, @var11, @var12, @var13, @var14, @var15, @var16, @var17, @var18, @var19, @var20, @var21, @var22, @var23, @var24, @var25, @var26, @var27, @var28, @var29, @var30
,updatetime)
SET organic = (@var1 = 'Y')
AND bakedgoods = (@var2 = 'Y')
AND cheese = (@var3 = 'Y')
AND crafts = (@var4 = 'Y')
AND flowers = (@var5 = 'Y')
AND eggs = (@var6 = 'Y')
AND seafood = (@var7 = 'Y')
AND herbs = (@var8 = 'Y')
AND vegetables = (@var9 = 'Y')
AND honey = (@var10 = 'Y')
AND jams = (@var11 = 'Y')
AND maple = (@var12 = 'Y')
AND meat = (@var13 = 'Y')
AND nursery = (@var14 = 'Y')
AND nuts = (@var15 = 'Y')
AND plants = (@var16 = 'Y')
AND poultry = (@var17 = 'Y')
AND prepared = (@var18 = 'Y')
AND soap = (@var19 = 'Y')
AND trees = (@var20 = 'Y')
AND wine = (@var21 = 'Y')
AND coffee = (@var22 = 'Y')
AND beans = (@var23 = 'Y')
AND fruits = (@var24 = 'Y')
AND grains = (@var25 = 'Y')
AND juices = (@var26 = 'Y')
AND mushrooms = (@var27 = 'Y')
AND petfood = (@var28 = 'Y')
AND tofu = (@var29 = 'Y')
AND wildharvested = (@var30 = 'Y');
SHOW WARNINGS;
SELECT * FROM farmers;
