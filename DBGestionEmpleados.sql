/* Aaron Vasquez 1B DAM Proyecto individual JavaFX UT8,9,10 */

CREATE DATABASE gestionempleados;
USE gestionempleados;


CREATE TABLE empleados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    puesto VARCHAR(255),
    salario INT,
    fecha DATE
);


select * from empleados;

/*
SET SQL_SAFE_UPDATES = 0;
delete from empleados;
SET SQL_SAFE_UPDATES = 1;
*/