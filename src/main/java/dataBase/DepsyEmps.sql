--
-- TABLA DEPARTAMENTOS MySQL
--
CREATE TABLE departamentos (
    dept_no  SMALLINT(2) NOT NULL PRIMARY KEY,
    dnombre  VARCHAR(15),
    loc      VARCHAR(15)
);

-- INSERCIÓN DE FILAS EN DEPARTAMENTOS

INSERT INTO departamentos VALUES (10,'CONTABILIDAD','SEVILLA');
INSERT INTO departamentos VALUES (20,'INVESTIGACIÓN','MADRID');
INSERT INTO departamentos VALUES (30,'VENTAS','BARCELONA');
INSERT INTO departamentos VALUES (40,'PRODUCCIÓN','BILBAO');
COMMIT;

-- ---------------------------------------------------------------------------
-- TABLA EMPLEADOS - MySQL
-- ---------------------------------------------------------------------------
CREATE TABLE empleados (
    emp_no    SMALLINT(4)  NOT NULL PRIMARY KEY,
    apellido  VARCHAR(10),
    oficio    VARCHAR(10),
    dir       SMALLINT,
    fecha_alt DATE      ,
    salario   FLOAT(6,2),
    comision  FLOAT(6,2),
    dept_no   SMALLINT(2) NOT NULL,
    FOREIGN KEY(dept_no) REFERENCES departamentos(dept_no)
);

INSERT INTO empleados VALUES (7369,'SÁNCHEZ','EMPLEADO',7902,'1990-12-17',
                              1040,NULL,20);
INSERT INTO empleados VALUES (7499,'ARROYO','VENDEDOR',7698,'1990-02-20',
                              1500,390,30);
INSERT INTO empleados VALUES (7521,'SALA','VENDEDOR',7698,'1991-02-22',
                              1625,650,30);
INSERT INTO empleados VALUES (7566,'JIMÉNEZ','DIRECTOR',7839,'1991-04-02',
                              2900,NULL,20);
INSERT INTO empleados VALUES (7654,'MARTÍN','VENDEDOR',7698,'1991-09-29',
                              1600,1020,30);
INSERT INTO empleados VALUES (7698,'NEGRO','DIRECTOR',7839,'1991-05-01',
                              3005,NULL,30);
INSERT INTO empleados VALUES (7782,'CEREZO','DIRECTOR',7839,'1991-06-09',
                              2885,NULL,10);
INSERT INTO empleados VALUES (7788,'GIL','ANALISTA',7566,'1991-11-09',
                              3000,NULL,20);
INSERT INTO empleados VALUES (7839,'REY','PRESIDENTE',NULL,'1991-11-17',
                              4100,NULL,10);
INSERT INTO empleados VALUES (7844,'TOVAR','VENDEDOR',7698,'1991-09-08',
                              1350,0,30);
INSERT INTO empleados VALUES (7876,'ALONSO','EMPLEADO',7788,'1991-09-23',
                              1430,NULL,20);
INSERT INTO empleados VALUES (7900,'JIMENO','EMPLEADO',7698,'1991-12-03',
                              1335,NULL,30);
INSERT INTO empleados VALUES (7902,'FERNÁNDEZ','ANALISTA',7566,'1991-12-03',
                              3000,NULL,20);
INSERT INTO empleados VALUES (7934,'MUÑOZ','EMPLEADO',7782,'1992-01-23',
                              1690,NULL,10);
COMMIT;