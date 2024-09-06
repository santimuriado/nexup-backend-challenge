package com.nexup_backend_challenge.solucion.controllers

import com.nexup_backend_challenge.solucion.services.SupermercadoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/supermercado")
class SupermercadoController (private val supermercadoService: SupermercadoService) {

    @PostMapping("/{idSupermercado}/productos/{idProducto}/vender")
    fun registrarVenta(@PathVariable idSupermercado: Long,
                       @PathVariable idProducto: Long,
                       @RequestParam cantidad: Int) : ResponseEntity<Double> {
        val valorVenta = supermercadoService.registrarVenta(idSupermercado,idProducto,cantidad);
        return ResponseEntity.status(HttpStatus.OK).body(valorVenta);
    }

    @GetMapping("/{idSupermercado}/productos/{idProducto}/cantidadVendida")
    fun getCantidadVendida(@PathVariable idSupermercado: Long,
                           @PathVariable idProducto: Long): ResponseEntity<Int> {
        val cantidadVendida = supermercadoService.getCantidadVendida(idSupermercado,idProducto);
        return ResponseEntity.status(HttpStatus.OK).body(cantidadVendida)
    }

    @GetMapping("/{idSupermercado}/productos/{idProducto}/ingresosProducto")
    fun getIngresosProducto(@PathVariable idSupermercado: Long,
                            @PathVariable idProducto: Long) : ResponseEntity<Double> {
        val ingresosProducto = supermercadoService.getIngresosProducto(idSupermercado,idProducto)
        return ResponseEntity.status(HttpStatus.OK).body(ingresosProducto)
    }

    @GetMapping("/{idSupermercado}/ingresos")
    fun getIngresosPorSupermercado(@PathVariable idSupermercado: Long) : ResponseEntity<Double> {
        val ingresosPorSupermercado = supermercadoService.getIngresosPorSupermercado(idSupermercado)
        return ResponseEntity.status(HttpStatus.OK).body(ingresosPorSupermercado)
    }

}