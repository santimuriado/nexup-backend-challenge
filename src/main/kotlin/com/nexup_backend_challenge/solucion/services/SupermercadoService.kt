package com.nexup_backend_challenge.solucion.services

import com.nexup_backend_challenge.solucion.repositories.ProductoRepository
import com.nexup_backend_challenge.solucion.repositories.StockRepository
import com.nexup_backend_challenge.solucion.repositories.SupermercadoRepository
import org.springframework.stereotype.Service


@Service
class SupermercadoService(
    private val supermercadoRepository: SupermercadoRepository,
    private val productoRepository: ProductoRepository,
    private val stockRepository: StockRepository
) {

    fun registrarVenta(idSupermercado: Long, idProducto: Long, cantidad: Int) : Double {

        val supermercado = supermercadoRepository.findById(idSupermercado).orElseThrow { Exception("Supermercado no encontrado") }
        val producto = productoRepository.findById(idProducto).orElseThrow { Exception("Producto no encontrado") }

        val stock = stockRepository.findBySupermercadoAndProducto(supermercado,producto) ?: throw Exception("Producto no disponible en este Supermercado")

        if(stock.cantidad < cantidad) throw Exception("Stock insuficiente")

        stock.cantidadVendida += cantidad
        stock.cantidad -= cantidad
        stockRepository.save(stock)

        return producto.precio * cantidad;

    }


}