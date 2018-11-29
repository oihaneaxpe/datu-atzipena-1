CREATE DATABASE IkasleakDB_ORMprobak;
USE IkasleakDB_ORMprobak
CREATE TABLE IF NOT EXISTS Ikaslea(
                zenbakia integer PRIMARY KEY,
                izena text,
                abizena1 text,
                abizena2 text,
                microsoftKontua text);

INSERT INTO Ikaslea VALUES(1,'Leo','Messi','','');
