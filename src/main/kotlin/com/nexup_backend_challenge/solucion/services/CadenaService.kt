package com.nexup_backend_challenge.solucion.services

import com.nexup_backend_challenge.solucion.repositories.StockRepository
import org.springframework.stereotype.Service

@Service
class CadenaService(
    private val stockRepository: StockRepository
) {

    /**
     * Obtiene los 5 productos más vendidos.
     *
     * Este método consulta todos los productos en el stock, agrupa por producto, suma la cantidad vendida
     * de cada uno, y luego retorna los 5 productos con más ventas en formato de cadena de texto.
     *
     * @return Una cadena de texto con los nombres de los 5 productos más vendidos y sus respectivas cantidades vendidas, separados por guiones.
     */
    fun getTop5Productos() : String {
        val productos = stockRepository.findAll()
            .groupBy { it.producto }
            .mapValues { it.value.sumOf { stock -> stock.cantidadVendida } }
            .toList()
            .sortedByDescending { it.second }
            .take(5)

        return productos.joinToString(separator = " - ") { "${it.first.nombre}: ${it.second}" }
    }
}