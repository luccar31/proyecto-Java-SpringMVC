insert into Producto (cantidad, costo, idProveedor, nombre) values (1000, 2000, 3, 'lavarropas'),(1000, 6000, 3, 'notebook'),(1000, 18000, 5, 'proyector'),(1000, 5000, 5, 'auriculares');
insert into Empleado (name, rol, sueldo) values ('lucas', 'gerente', 120000),('jorge', 'empleado', 80000);
insert into Usuario (email, password, rol) values ('ivo@gmail.com', '0', 'dueño'),('hola@example.com', '123456', 'dueño');
insert into Cliente (fecha, nombre, mail, notifEnable) values ('2022-10-20', 'ivo', 'ivospruth@hotmail.com', true), ('2022-10-21', 'ivan', 'ivospruth1999@gmail.com', true);
insert into Oferta (fecha, idEmpleado, mensaje, valida) values ('2022-10-20', 1, '10 porciento de decuescuento en Electronica', true), ('2022-10-21', 1, '50 porciento off para clientes visa', true);
insert into Venta (cantidadProducto, cantidadProducto2, fecha, idEmpleado, idProducto, idProducto2, total) values ( 2, 2,CURDATE(),3, 3, 4, 500.0), ( 2, 2, CURDATE(), 3, 1, 2, 1500.0);