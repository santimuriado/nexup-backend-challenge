package com.nexup_backend_challenge.solucion.services

import com.nexup_backend_challenge.solucion.exceptions.ProductoNoDisponibleException
import com.nexup_backend_challenge.solucion.exceptions.ProductoNotFoundException
import com.nexup_backend_challenge.solucion.exceptions.StockInsuficienteException
import com.nexup_backend_challenge.solucion.exceptions.SupermercadoNotFoundException
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

        val supermercado = supermercadoRepository.findById(idSupermercado).orElseThrow { SupermercadoNotFoundException("Supermercado no encontrado") }
        val producto = productoRepository.findById(idProducto).orElseThrow { ProductoNotFoundException("Producto no encontrado") }

        val stock = stockRepository.findBySupermercadoAndProducto(supermercado,producto) ?: throw ProductoNoDisponibleException("Producto no disponible en este Supermercado")

        if(stock.cantidad < cantidad) throw StockInsuficienteException("Stock insuficiente")

        stock.cantidadVendida += cantidad
        stock.cantidad -= cantidad
        stockRepository.save(stock)

        return producto.precio * cantidad;

    }


}