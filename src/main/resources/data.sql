-- Insertar productos
INSERT INTO producto (id, nombre, precio) VALUES (1, 'Manzanas', 1.50);
INSERT INTO producto (id, nombre, precio) VALUES (2, 'Naranjas', 1.20);
INSERT INTO producto (id, nombre, precio) VALUES (3, 'Leche', 0.95);
INSERT INTO producto (id, nombre, precio) VALUES (4, 'Pan', 1.10);
INSERT INTO producto (id, nombre, precio) VALUES (5, 'Huevos', 2.50);
INSERT INTO producto (id, nombre, precio) VALUES (6, 'Queso', 3.75);
INSERT INTO producto (id, nombre, precio) VALUES (7, 'Jugo de Naranja', 2.00);
INSERT INTO producto (id, nombre, precio) VALUES (8, 'Cereal', 3.10);
INSERT INTO producto (id, nombre, precio) VALUES (9, 'Pollo', 5.00);
INSERT INTO producto (id, nombre, precio) VALUES (10, 'Pasta', 1.25);

-- Insertar supermercados
INSERT INTO supermercado (id, nombre, horario_apertura, horario_cierre) VALUES (1, 'Supermercado Central', '08:00:00', '22:00:00');
INSERT INTO supermercado (id, nombre, horario_apertura, horario_cierre) VALUES (2, 'Supermercado Norte', '09:00:00', '21:00:00');
INSERT INTO supermercado (id, nombre, horario_apertura, horario_cierre) VALUES (3, 'Supermercado Sur', '10:00:00', '20:00:00');

-- Insertar días en que los supermercados están abiertos
INSERT INTO supermercado_dia_abierto (supermercado_id, dia_abierto) VALUES (1, 'LUNES');
INSERT INTO supermercado_dia_abierto (supermercado_id, dia_abierto) VALUES (1, 'MARTES');
INSERT INTO supermercado_dia_abierto (supermercado_id, dia_abierto) VALUES (1, 'MIERCOLES');
INSERT INTO supermercado_dia_abierto (supermercado_id, dia_abierto) VALUES (1, 'JUEVES');
INSERT INTO supermercado_dia_abierto (supermercado_id, dia_abierto) VALUES (1, 'VIERNES');
INSERT INTO supermercado_dia_abierto (supermercado_id, dia_abierto) VALUES (1, 'SABADO');

INSERT INTO supermercado_dia_abierto (supermercado_id, dia_abierto) VALUES (2, 'LUNES');
INSERT INTO supermercado_dia_abierto (supermercado_id, dia_abierto) VALUES (2, 'MARTES');
INSERT INTO supermercado_dia_abierto (supermercado_id, dia_abierto) VALUES (2, 'MIERCOLES');
INSERT INTO supermercado_dia_abierto (supermercado_id, dia_abierto) VALUES (2, 'VIERNES');
INSERT INTO supermercado_dia_abierto (supermercado_id, dia_abierto) VALUES (2, 'SABADO');
INSERT INTO supermercado_dia_abierto (supermercado_id, dia_abierto) VALUES (2, 'DOMINGO');

INSERT INTO supermercado_dia_abierto (supermercado_id, dia_abierto) VALUES (3, 'MARTES');
INSERT INTO supermercado_dia_abierto (supermercado_id, dia_abierto) VALUES (3, 'JUEVES');
INSERT INTO supermercado_dia_abierto (supermercado_id, dia_abierto) VALUES (3, 'SABADO');
INSERT INTO supermercado_dia_abierto (supermercado_id, dia_abierto) VALUES (3, 'DOMINGO');

-- Insertar stock
-- Supermercado Central
INSERT INTO stock (id, producto_id, supermercado_id, cantidad, cantidad_vendida) VALUES (1, 1, 1, 100, 10);  -- Manzanas
INSERT INTO stock (id, producto_id, supermercado_id, cantidad, cantidad_vendida) VALUES (2, 2, 1, 120, 15);  -- Naranjas
INSERT INTO stock (id, producto_id, supermercado_id, cantidad, cantidad_vendida) VALUES (3, 3, 1, 200, 20);  -- Leche
INSERT INTO stock (id, producto_id, supermercado_id, cantidad, cantidad_vendida) VALUES (4, 4, 1, 50, 5);    -- Pan
INSERT INTO stock (id, producto_id, supermercado_id, cantidad, cantidad_vendida) VALUES (5, 5, 1, 60, 12);   -- Huevos

-- Supermercado Norte
INSERT INTO stock (id, producto_id, supermercado_id, cantidad, cantidad_vendida) VALUES (6, 1, 2, 80, 8);    -- Manzanas
INSERT INTO stock (id, producto_id, supermercado_id, cantidad, cantidad_vendida) VALUES (7, 6, 2, 90, 9);    -- Queso
INSERT INTO stock (id, producto_id, supermercado_id, cantidad, cantidad_vendida) VALUES (8, 7, 2, 110, 25);  -- Jugo de Naranja
INSERT INTO stock (id, producto_id, supermercado_id, cantidad, cantidad_vendida) VALUES (9, 8, 2, 130, 30);  -- Cereales
INSERT INTO stock (id, producto_id, supermercado_id, cantidad, cantidad_vendida) VALUES (10, 9, 2, 70, 0);   -- Pollo

-- Supermercado Sur
INSERT INTO stock (id, producto_id, supermercado_id, cantidad, cantidad_vendida) VALUES (11, 2, 3, 150, 5);  -- Naranjas
INSERT INTO stock (id, producto_id, supermercado_id, cantidad, cantidad_vendida) VALUES (12, 3, 3, 180, 20); -- Leche
INSERT INTO stock (id, producto_id, supermercado_id, cantidad, cantidad_vendida) VALUES (13, 4, 3, 100, 50); -- Pan
INSERT INTO stock (id, producto_id, supermercado_id, cantidad, cantidad_vendida) VALUES (14, 5, 3, 90, 15);  -- Huevos
INSERT INTO stock (id, producto_id, supermercado_id, cantidad, cantidad_vendida) VALUES (15, 10, 3, 100, 10);-- Pasta

-- Datos iniciales para probar las funcionalidades de ventas
INSERT INTO stock (id, producto_id, supermercado_id, cantidad, cantidad_vendida) VALUES (16, 1, 3, 90, 0); -- Producto sin ventas todavia
