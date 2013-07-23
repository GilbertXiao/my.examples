-- Loads EMP records from first 23 characters
   -- Creates and loads PROJ records for each PROJNO listed
   -- for each employee
   LOAD DATA
   INFILE 'ulcase5.dat'
   BADFILE 'ulcase5.bad'
   DISCARDFILE 'ulcase5.dsc'
   REPLACE
       INTO TABLE emp
   (empno   POSITION(1:4)     INTEGER EXTERNAL,
   ename    POSITION(6:15)    CHAR,
   deptno   POSITION(17:18)   CHAR,
   mgr      POSITION(20:23)   INTEGER EXTERNAL)
     INTO TABLE proj
   -- PROJ has two columns, both not null: EMPNO and PROJNO
     WHEN projno != '   '
   (empno   POSITION(1:4)     INTEGER EXTERNAL,
     projno   POSITION(25:27)   INTEGER EXTERNAL)   -- 1st proj
     INTO TABLE proj
     WHEN projno != '   '
   (empno   POSITION(1:4)     INTEGER EXTERNAL,
     projno   POSITION(29:31    INTEGER EXTERNAL)   -- 2nd proj

     INTO TABLE proj
     WHEN projno != '   '
   (empno   POSITION(1:4)    INTEGER EXTERNAL,
    projno  POSITION(33:35)  INTEGER EXTERNAL)   -- 3rd proj 

