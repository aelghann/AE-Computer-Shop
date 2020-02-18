DROP DATABASE IF EXISTS AECP;

CREATE DATABASE AECP;

USE AECP;


CREATE TABLE User (
  ID INT NOT NULL AUTO_INCREMENT,
  UserID VARCHAR(50),
  Password VARCHAR(200),
  FirstName VARCHAR(50),
  LastName VARCHAR(50),
  EmailAddress VARCHAR(50),
  Address1 VARCHAR(50),
  Address2 VARCHAR(50),
  City VARCHAR(50),
  State VARCHAR(50),
  PostCode VARCHAR(50),
  Country VARCHAR(50),
  Question VARCHAR(50),
  
  UNIQUE(EmailAddress),
  UNIQUE(userID),
  
  PRIMARY KEY (ID)
  
);


INSERT INTO aecp.user 
  (UserID,Password,FirstName,LastName,EmailAddress,Address1,Address2,City,State,PostCode,Country,Question)
VALUES
  ("U011","3e01e24cf321d2abd1a226f40982cdf1c4399a80827667da29d17ec51101899d","Double","Energy","DE@gmail.com","5941 Crystals St","Suit 767","ICity","Solid","35942","MB","charlotte"), 
  ("U012","4329f64051b3050b5dd2c919d4beb92644766877f7b3532cc397a89cbf18ae04","DDouble","EEnergy","DEE@gmail.com","5941 Crystals St","Suit 767","ICity","Solid","35942","MB","charlotte"),
  ("U013","2f51f630d7f415847e55080ef9924d80f971996fa095657104d9790008a09878","DDDouble","EEEnergy","DEEE@gmail.com","5941 Crystals St","Suit 767","ICity","Solid","35942","MB","charlotte"),
  ("U01222","2c66e14c11ac16eef1f0fc8b2eb3b1ccee84acd729e3d8429a0f2f859c067fd7","DDouble","EEnergy","DEEEE@gmail.com","5941 Crystals St","Suit 767","ICity","Solid","35942","MB","charlotte"),
  ("U01333","9a4c7e7c39dc3c8373498e26f3dd3f09a00aa702de57e3e52cfc1acbd24734a7","DDDouble","EEEnergy","DEEEEEE@gmail.com","5941 Crystals St","Suit 767","ICity","Solid","35942","MB","charlotte"),
  ("name","d58db75356630653f7a4b948dd7eac6bcc28d1ad39b528d59611f0673b4391c9","DDDouble","EEEnergy","name@gmail.com","5941 Crystals St","Suit 767","ICity","Solid","35942","MB","charlotte");


CREATE TABLE Item
(
	ItemCode VARCHAR(50),
    ItemName VARCHAR(50),
	CatalogCategory VARCHAR(1),
	Description VARCHAR(500),
	Rating VARCHAR(250),
	ImageUrl VARCHAR(500), 
	
    PRIMARY KEY (ItemCode)


);



INSERT INTO Item
(ItemCode,ItemName,CatalogCategory,Description,Rating,ImageURL)
VALUES
("GPU1", "High Scale Gpu", "G", "NVIDIA GTX Quadro capable of pumping display to 4 monitors and is a great choice for multitasking", "&ensp;&#9733;&ensp;&#9733;&ensp;&#9733;&ensp;&#9733;&ensp;&#9733;", "images/GPU1.png"),
("GPU2", "Higher Scale Gpu", "G", "NVIDIA GTX Quadro 9988x capable of computing big graphical information and have a crushing g-computing power of 18 Ghz.", "&ensp;&#9733;&ensp;&#9733;&ensp;&#9733;", "images/GPU2.png"),
("GPU3", "Hologram Capable Gpu", "G", "This is the NVIDIA Tesla K40 graphics card developed by Lennnnovo Group.\n\n
                 \n
                 It has 120 Gb of GDDR5X memory technology and it is capable of displaying a crystal clear hologram. The size of the hologram can scale as big as a movie theatre screen while maintaining the same display quality.\n
                 \n
                 \n
                 The K40 is extremely expensive and is only sold to big research corps. .\n
                 \n", "&ensp;&#9733;&ensp;&#9733;&ensp;&#9733;&ensp;&#9733;", "images/GPU3.png"),
("CPU1", "High Scale Cpu", "C", "Intel Xeon E7 is an entry level CPU. Most of the time it is purchased in paits for better synergy. Two of these Cpu yields 64 core processors in total", "&ensp;&#9733;&ensp;&#9733;&ensp;&#9733;", "images/CPU1.png"),
("CPU2", "Higher Scale Cpu", "C", "Intel Xeon E7-8800 V2 is the big brother of teh Xeon E7 yielding 128 core processors, but it has higher energy consumption", "&#9733;&ensp;&#9733;&ensp;&#9733;&ensp;&#9733;", "images/CPU2.png"),
("CPU3", "Custom Made Cpu", "C", "Using the Intel Xeon processors as bae material, we can create job specific processors offering out standing preformance and maybe an voer kill for the job as well. There isno beter way to prepare for teh future.", "&#9733;&ensp;&#9733;&ensp;&#9733;&ensp;&#9733;&ensp;&#9733;", "images/CPU3.png");


CREATE TABLE Salt
(
UserID VARCHAR(50),
Salt VARCHAR(200),
PRIMARY KEY (UserID)
);

INSERT INTO Salt
  (UserID,Salt)
VALUES
  ("U011","P+yplQfDGT+BSAGkaby8Sywqv24JQkDkRI+PBHP0KFU="), 
  ("U012","PFow+ORmieFFHAIGUHLZ1MGEa6AcmYbNjyifjqy9Z0E="),
  ("U013","QLYWOloEfNPmC+19843UxAuWukc8Klau93peP09xIfA="),
  ("U01222","0SfDQ/O3Re2J8SHJEtgKI+Z4fnvkgCTwmBKf02tejso="),
  ("name","jYYRElHlPjSsTQimWMIPlA0qGc4J7Tnlobb7J8KA2wU="),
  ("U01333","FLilFOTa+8cFv916KFfCTjmPB+da1sRhYGjRCCcJsi0=");
  
  


