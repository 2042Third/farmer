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
SET organic = (@var1 = 'Y');
SET bakedgoods = (@var2 = 'Y');
SET cheese = (@var3 = 'Y');
SET crafts = (@var4 = 'Y');
SET flowers = (@var5 = 'Y');
SET eggs = (@var6 = 'Y');
SET seafood = (@var7 = 'Y');
SET herbs = (@var8 = 'Y');
SET vegetables = (@var9 = 'Y');
SET honey = (@var10 = 'Y');
SET jams = (@var11 = 'Y');
SET maple = (@var12 = 'Y');
SET meat = (@var13 = 'Y');
SET nursery = (@var14 = 'Y');
SET nuts = (@var15 = 'Y');
SET plants = (@var16 = 'Y');
SET poultry = (@var17 = 'Y');
SET prepared = (@var18 = 'Y');
SET soap = (@var19 = 'Y');
SET trees = (@var20 = 'Y');
SET wine = (@var21 = 'Y');
SET coffee = (@var22 = 'Y');
SET beans = (@var23 = 'Y');
SET fruits = (@var24 = 'Y');
SET grains = (@var25 = 'Y');
SET juices = (@var26 = 'Y');
SET mushrooms = (@var27 = 'Y');
SET petfood = (@var28 = 'Y');
SET tofu = (@var29 = 'Y');
SET wildharvested = (@var30 = 'Y');
SHOW WARNINGS;
SELECT * FROM farmers;
