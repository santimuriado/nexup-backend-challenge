package com.nexup_backend_challenge.solucion.services

import com.nexup_backend_challenge.solucion.repositories.StockRepository
import com.nexup_backend_challenge.solucion.repositories.SupermercadoRepository
import org.springframework.stereotype.Service

@Service
class CadenaService(
    private val stockRepository: StockRepository,
    private val supermercadoRepository: SupermercadoRepository,
    private val supermercadoService: SupermercadoService
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

    /**
     * Calcula los ingresos totales generados por todos los supermercados.
     *
     * Este método suma los ingresos de todos los supermercados, obteniendo los ingresos
     * de cada uno a través de la función `getIngresosPorSupermercado`.
     *
     * @return El total de ingresos generados por todos los supermercados.
     */
    fun getIngresosTotales() : Double {
        return supermercadoRepository.findAll().sumOf { supermercadoService.getIngresosPorSupermercado(it.id) }
    }
}