package com.nexup_backend_challenge.solucion.controllers

import com.nexup_backend_challenge.solucion.services.CadenaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/cadena")
class CadenaSuperController(private val cadenaService: CadenaService) {

    @GetMapping("/top5Productos")
    fun getTop5Productos() : ResponseEntity<String> {
        val top5Productos = cadenaService.getTop5Productos()
        return ResponseEntity.status(HttpStatus.OK).body(top5Productos)
    }

    @GetMapping("/ingresosTotales")
    fun getIngresosTotales() : ResponseEntity<Double> {
        val ingresosTotales = cadenaService.getIngresosTotales()
        return ResponseEntity.status(HttpStatus.OK).body(ingresosTotales)
    }
}