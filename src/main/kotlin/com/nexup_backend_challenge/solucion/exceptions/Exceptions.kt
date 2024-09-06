package com.nexup_backend_challenge.solucion.exceptions

class SupermercadoNotFoundException(mensaje: String) : RuntimeException(mensaje)

class ProductoNotFoundException(mensaje: String) : RuntimeException(mensaje)

class ProductoNoDisponibleException(mensaje: String) : RuntimeException(mensaje)

class StockInsuficienteException(mensaje: String) : RuntimeException(mensaje)