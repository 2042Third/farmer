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
    `organic` VARCHAR(3) DEFAULT 'N',
  `bakedgoods` VARCHAR(3) DEFAULT 'N',
  `cheese` VARCHAR(3) DEFAULT 'N',
  `crafts` VARCHAR(3) DEFAULT 'N',
  `flowers` VARCHAR(3) DEFAULT 'N',
  `eggs` VARCHAR(3) DEFAULT 'N',
  `seafood` VARCHAR(3) DEFAULT 'N',
  `herbs` VARCHAR(3) DEFAULT 'N',
  `vegetables` VARCHAR(3) DEFAULT 'N',
  `honey` VARCHAR(3) DEFAULT 'N',
  `jams` VARCHAR(3) DEFAULT 'N',
  `maple` VARCHAR(3) DEFAULT 'N',
  `meat` VARCHAR(3) DEFAULT 'N',
  `nursery` VARCHAR(3) DEFAULT 'N',
  `nuts` VARCHAR(3) DEFAULT 'N',
  `plants` VARCHAR(3) DEFAULT 'N',
  `poultry` VARCHAR(3) DEFAULT 'N',
  `prepared` VARCHAR(3) DEFAULT 'N',
  `soap` VARCHAR(3) DEFAULT 'N',
  `trees` VARCHAR(3) DEFAULT 'N',
  `wine` VARCHAR(3) DEFAULT 'N',
  `coffee` VARCHAR(3) DEFAULT 'N',
  `beans` VARCHAR(3) DEFAULT 'N',
  `fruits` VARCHAR(3) DEFAULT 'N',
  `grains` VARCHAR(3) DEFAULT 'N',
  `juices` VARCHAR(3) DEFAULT 'N',
  `mushrooms` VARCHAR(3) DEFAULT 'N',
  `petfood` VARCHAR(3) DEFAULT 'N',
  `tofu` VARCHAR(3) DEFAULT 'N',
  `wildharvested` VARCHAR(3) DEFAULT 'N',
  `updatetime` VARCHAR(255) DEFAULT NULL,
  `reviewcount` INT DEFAULT 0,
  `reviewscore` INT DEFAULT 0
);
-- ["SET "+part1[i]+" = (@var"+str(i+1)+" = \'Y\');\n" for i in range(len(part1))]
SET GLOBAL local_infile = 'ON';

LOAD DATA LOCAL INFILE '/home/laptopadmin/Downloads/Export.csv' INTO TABLE farmers 
FIELDS TERMINATED BY ',' ENCLOSED BY '\"'
LINES TERMINATED BY '\n' (id, name,
  website,facebook,twitter,youtube,othermedia,street,city,county,state,zip,season1date,season1time,season2date,season2time,season3date,season3time,season4date,season4time,x,y,location,credit,wic,wiccash,sfmnp,snap
,organic,bakedgoods,cheese,crafts,flowers,eggs,seafood,herbs,vegetables,honey,jams,maple,meat,nursery,nuts,plants,poultry,prepared,soap,trees,wine,coffee,beans,fruits,grains,juices,mushrooms,petfood,tofu,wildharvested
,updatetime);
-- SET organic = (@var1 = 'Y')
-- AND bakedgoods = (@var2 = 'Y')
-- AND cheese = (@var3 = 'Y')
-- AND crafts = (@var4 = 'Y')
-- AND flowers = (@var5 = 'Y')
-- AND eggs = (@var6 = 'Y')
-- AND seafood = (@var7 = 'Y')
-- AND herbs = (@var8 = 'Y')
-- AND vegetables = (@var9 = 'Y')
-- AND honey = (@var10 = 'Y')
-- AND jams = (@var11 = 'Y')
-- AND maple = (@var12 = 'Y')
-- AND meat = (@var13 = 'Y')
-- AND nursery = (@var14 = 'Y')
-- AND nuts = (@var15 = 'Y')
-- AND plants = (@var16 = 'Y')
-- AND poultry = (@var17 = 'Y')
-- AND prepared = (@var18 = 'Y')
-- AND soap = (@var19 = 'Y')
-- AND trees = (@var20 = 'Y')
-- AND wine = (@var21 = 'Y')
-- AND coffee = (@var22 = 'Y')
-- AND beans = (@var23 = 'Y')
-- AND fruits = (@var24 = 'Y')
-- AND grains = (@var25 = 'Y')
-- AND juices = (@var26 = 'Y')
-- AND mushrooms = (@var27 = 'Y')
-- AND petfood = (@var28 = 'Y')
-- AND tofu = (@var29 = 'Y')
-- AND wildharvested = (@var30 = 'Y');
SHOW WARNINGS;
SELECT * FROM farmers;
