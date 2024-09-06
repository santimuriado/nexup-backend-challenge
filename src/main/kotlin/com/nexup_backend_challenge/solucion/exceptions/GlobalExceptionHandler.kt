package com.nexup_backend_challenge.solucion.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(SupermercadoNotFoundException::class)
    fun handleSupermercadoNotFound(exception: SupermercadoNotFoundException): ResponseEntity<String> {
        // Retornar un error 404 Not Found cuando no se encuentra un supermercado
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.message)
    }

    @ExceptionHandler(ProductoNotFoundException::class)
    fun handleProductoNotFound(exception: ProductoNotFoundException): ResponseEntity<String> {
        // Retornar un error 404 Not Found cuando no se encuentra un producto
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.message)
    }

    @ExceptionHandler(ProductoNoDisponibleException::class)
    fun handleProductoNoDisponible(exception: ProductoNoDisponibleException): ResponseEntity<String> {
        // Retornar un error 404 Not Found cuando no se encuentra un producto en un supermercado
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.message)
    }

    @ExceptionHandler(StockInsuficienteException::class)
    fun handleStockInsuficiente(exception: StockInsuficienteException): ResponseEntity<String> {
        // Retornar un error 400 Bad Request cuando hay stock insuficiente de un producto
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleInvalidArguments(exception: IllegalArgumentException): ResponseEntity<String> {
        // Retornar un error 400 Bad Request cuando hay un argumento inválido
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(exception: Exception): ResponseEntity<String> {
        // Capturar todas las demás excepciones y retornar un error 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno: ${exception.message}")
    }
}