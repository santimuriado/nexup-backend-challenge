package com.nexup_backend_challenge.solucion.controllers

import com.nexup_backend_challenge.solucion.services.CadenaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalTime


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

    @GetMapping("/supermercadoTopIngresos")
    fun getSupermercadoTopIngresos() : ResponseEntity<String> {
        val supermercadoTop = cadenaService.getSupermercadoTopIngresos()
        return ResponseEntity.status(HttpStatus.OK).body(supermercadoTop)
    }

    @GetMapping("/abierto")
    fun getSupermercadosAbiertos(@RequestParam diaDeLaSemana: String, @RequestParam hora: String) : ResponseEntity<String> {
        val horaLocal = LocalTime.parse(hora)
        val supermercadosAbiertos = cadenaService.getSupermercadosAbiertos(diaDeLaSemana,horaLocal)

        return ResponseEntity.status(HttpStatus.OK).body(supermercadosAbiertos)
    }
}