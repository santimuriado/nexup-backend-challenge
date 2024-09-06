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

    /**
     * Registra la venta de un producto en un supermercado específico.
     *
     * Este método se encarga de registrar una venta de un producto en un supermercado, actualizando el stock
     * disponible y la cantidad vendida del producto en dicho supermercado.
     *
     * @param idSupermercado El ID del supermercado donde se realiza la venta.
     * @param idProducto El ID del producto que se está vendiendo.
     * @param cantidad La cantidad de unidades del producto que se están vendiendo.
     * @return El total de la venta, calculado como el precio del producto multiplicado por la cantidad vendida.
     * @throws SupermercadoNotFoundException Si el supermercado con el ID dado no existe.
     * @throws ProductoNotFoundException Si el producto con el ID dado no existe.
     * @throws ProductoNoDisponibleException Si el producto no está disponible en el supermercado.
     * @throws StockInsuficienteException Si la cantidad solicitada es mayor que el stock disponible.
     */
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

    /**
     * Obtiene la cantidad vendida de un producto en un supermercado específico.
     *
     * Este método devuelve la cantidad de unidades vendidas de un producto en un supermercado,
     * verificando que ambos existan y que el producto esté disponible en el supermercado.
     *
     * @param idSupermercado El ID del supermercado donde se consulta la cantidad vendida.
     * @param idProducto El ID del producto del cual se quiere conocer la cantidad vendida.
     * @return La cantidad vendida del producto en el supermercado.
     * @throws SupermercadoNotFoundException Si el supermercado con el ID dado no existe.
     * @throws ProductoNotFoundException Si el producto con el ID dado no existe.
     * @throws ProductoNoDisponibleException Si el producto no está disponible en el supermercado.
     */
    fun getCantidadVendida(idSupermercado: Long, idProducto: Long) : Int {

        /* Busco Supermercado por ID */
        val supermercado = supermercadoRepository.findById(idSupermercado).orElseThrow { SupermercadoNotFoundException("Supermercado no encontrado") }

        /* Busco Producto por ID */
        val producto = productoRepository.findById(idProducto).orElseThrow { ProductoNotFoundException("Producto no encontrado") }

        /* Busco si el supermercado tiene al producto */
        val stock = stockRepository.findBySupermercadoAndProducto(supermercado,producto) ?: throw ProductoNoDisponibleException("Producto no disponible en este Supermercado")

        return stock.cantidadVendida;
    }

    /**
     * Calcula los ingresos generados por la venta de un producto en un supermercado específico.
     *
     * Este método calcula los ingresos obtenidos de un producto en un supermercado, multiplicando la cantidad vendida por el precio del producto.
     *
     * @param idSupermercado El ID del supermercado donde se consulta los ingresos.
     * @param idProducto El ID del producto del cual se desea conocer los ingresos.
     * @return El total de ingresos generados por la venta del producto en el supermercado.
     * @throws SupermercadoNotFoundException Si el supermercado con el ID dado no existe.
     * @throws ProductoNotFoundException Si el producto con el ID dado no existe.
     * @throws ProductoNoDisponibleException Si el producto no está disponible en el supermercado.
     */
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