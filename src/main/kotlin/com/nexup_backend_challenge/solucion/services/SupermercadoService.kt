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

        /* Busco Supermercado por ID */
        val supermercado = supermercadoRepository.findById(idSupermercado).orElseThrow { SupermercadoNotFoundException("Supermercado no encontrado") }

        /* Busco Producto por ID */
        val producto = productoRepository.findById(idProducto).orElseThrow { ProductoNotFoundException("Producto no encontrado") }

        /* Busco si el supermercado tiene al producto */
        val stock = stockRepository.findBySupermercadoAndProducto(supermercado,producto) ?: throw ProductoNoDisponibleException("Producto no disponible en este Supermercado")

        /* Veo si hay stock */
        if(stock.cantidad < cantidad) throw StockInsuficienteException("Stock insuficiente")

        stock.cantidadVendida += cantidad
        stock.cantidad -= cantidad
        stockRepository.save(stock)

        return producto.precio * cantidad;

    }

    fun getCantidadVendida(idSupermercado: Long, idProducto: Long) : Int {

        /* Busco Supermercado por ID */
        val supermercado = supermercadoRepository.findById(idSupermercado).orElseThrow { SupermercadoNotFoundException("Supermercado no encontrado") }

        /* Busco Producto por ID */
        val producto = productoRepository.findById(idProducto).orElseThrow { ProductoNotFoundException("Producto no encontrado") }

        /* Busco si el supermercado tiene al producto */
        val stock = stockRepository.findBySupermercadoAndProducto(supermercado,producto) ?: throw ProductoNoDisponibleException("Producto no disponible en este Supermercado")

        return stock.cantidadVendida;
    }

    fun getIngresosProducto(idSupermercado: Long,idProducto: Long): Double {

        /* Busco Supermercado por ID */
        val supermercado = supermercadoRepository.findById(idSupermercado).orElseThrow { SupermercadoNotFoundException("Supermercado no encontrado") }

        /* Busco Producto por ID */
        val producto = productoRepository.findById(idProducto).orElseThrow { ProductoNotFoundException("Producto no encontrado") }

        /* Busco si el supermercado tiene al producto */
        val stock = stockRepository.findBySupermercadoAndProducto(supermercado,producto) ?: throw ProductoNoDisponibleException("Producto no disponible en este Supermercado")

        return stock.cantidadVendida * producto.precio;
    }
}