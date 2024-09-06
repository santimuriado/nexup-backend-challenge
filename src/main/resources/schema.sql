-- Crear tabla de productos
CREATE TABLE producto (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(255) NOT NULL,
  precio DECIMAL(10, 2) NOT NULL
);

-- Crear tabla de supermercados
CREATE TABLE supermercado (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(255) NOT NULL,
  horario_apertura TIME NOT NULL,
  horario_cierre TIME NOT NULL
);

-- Tabla para los días en que cada supermercado está abierto
CREATE TABLE supermercado_dia_abierto (
  supermercado_id BIGINT NOT NULL,
  dia_abierto VARCHAR(10) NOT NULL,
  FOREIGN KEY (supermercado_id) REFERENCES supermercado(id)
);

-- Crear tabla de stock (relación entre productos y supermercados)
CREATE TABLE stock (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  producto_id BIGINT NOT NULL,
  supermercado_id BIGINT NOT NULL,
  cantidad INT NOT NULL,
  cantidad_vendida INT NOT NULL,
  FOREIGN KEY (producto_id) REFERENCES producto(id),
  FOREIGN KEY (supermercado_id) REFERENCES supermercado(id)
);
