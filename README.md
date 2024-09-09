# Proyecto: Cadena de Supermercados

## Descripción

Este proyecto implementa una solución para gestionar una cadena de supermercados, permitiendo llevar un control sobre la venta de productos, el stock y los ingresos. Además, incluye funcionalidades para consultar los productos más vendidos y los supermercados con mayores ingresos.
La aplicación está construida utilizando Kotlin como lenguaje de programación, con el framework Spring Boot para la creación de servicios REST. La base de datos utilizada es H2 en memoria, lo que facilita la ejecución de pruebas sin depender de una base de datos externa.

## Funcionalidades
### Supermercado
Registrar una venta de un producto: Permite registrar la venta de un producto específico, actualizando el stock disponible y registrando los ingresos.  
Obtener la cantidad vendida de un producto: Devuelve la cantidad de unidades vendidas de un producto específico en un supermercado.  
Obtener ingresos por ventas de un producto: Devuelve el monto total de ingresos obtenidos por la venta de un producto.  
Obtener ingresos totales: Devuelve los ingresos totales de todas las ventas realizadas en un supermercado.  

### Cadena de Supermercados

Obtener los 5 productos más vendidos: Devuelve una lista con los cinco productos más vendidos en toda la cadena de supermercados.  
Obtener ingresos totales: Devuelve los ingresos totales obtenidos de todas las ventas realizadas en toda la cadena de supermercados.  
Obtener el supermercado con mayor cantidad de ingresos por ventas: Devuelve el supermercado con más ingresos totales, junto con su ID y el monto total.  

### Funcionalidad Opcional

Supermercados abiertos según horario: Dado un día y hora, devuelve una lista de los supermercados que están abiertos en ese momento.

## Endpoints
- POST /api/supermercado/{idSupermercado}/productos/{idProducto}/vender: Registra la venta de un producto en un supermercado. Se pasa la cantidad del producto como queryParam.
- GET /api/supermercado/{idSupermercado}/productos/{idProducto}/cantidadVendida: Devuelve la cantidad vendida de un producto en un supermercado.
- GET /api/supermercado/{idSupermercado}/productos/{idProducto}/ingresosProducto: Devuelve el total de ingresos generados por la venta del producto en el supermercado.
- GET /api/supermercado/{idSupermercado}/ingresos : Devuelve el total de ingresos generados por las ventas en el supermercado.

- GET /api/cadena/top5Productos: Devuelve los 5 productos más vendidos en toda la cadena.
- GET /api/cadena/ingresosTotales : Devuelve el total de ingresos generados por todos los supermercados.
- GET /api/cadena/supermercadoTopIngresos: Devuelve el supermercado con más ingresos.

### Endpoint del objetivo opcional
- GET /api/cadena/abierto : Devuelve una cadena de texto con los nombres e IDs de los supermercados abiertos, separados por comas.

## Tecnologías Utilizadas
Kotlin: Lenguaje de programación principal.  
Spring Boot: Framework para crear la API REST.  
H2 Database: Base de datos en memoria utilizada para pruebas y almacenamiento temporal de datos.  
JUnit y Mockito: Para la creación y ejecución de pruebas unitarias e integrales.  

## Estructura del Proyecto
Modelos: Definición de las entidades Producto, Supermercado, y Stock, que representan los datos del sistema.  
Servicios: Implementación de la lógica de negocio para gestionar las ventas, el stock y los ingresos.  
Repositorios: Interfaces que extienden JpaRepository para interactuar con la base de datos H2.  
Controladores: Exponen los servicios como endpoints REST para ser consumidos a través de peticiones HTTP.  
Excepciones personalizadas: Para manejar los diferentes errores, como SupermercadoNotFoundException, ProductoNotFoundException, y StockInsuficienteException.  

## Enfoque Utilizado
Diseño de la lógica de negocio: Se diseñó un servicio para cada supermercado que administra las ventas, el stock y los ingresos. La lógica se encapsuló en métodos específicos dentro de las clases de servicio.  
Simulación de la base de datos: Usamos una base de datos en memoria (H2) para almacenar los productos, supermercados y el stock. Esto permitió una configuración más sencilla para las pruebas.  
Validación de datos y manejo de excepciones: Cada operación verifica la existencia de los recursos (supermercado, producto) antes de continuar. Si un recurso no existe, se lanza una excepción personalizada con el mensaje adecuado.  
