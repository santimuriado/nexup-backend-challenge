package com.nexup_backend_challenge.solucion.services

import com.nexup_backend_challenge.solucion.repositories.StockRepository
import com.nexup_backend_challenge.solucion.repositories.SupermercadoRepository
import org.springframework.stereotype.Service
import java.time.LocalTime

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

    /**
     * Obtiene el supermercado con mayores ingresos.
     *
     * Este método busca el supermercado con los ingresos más altos y devuelve una cadena
     * de texto con el nombre del supermercado, su ID y sus ingresos totales.
     *
     * @return Una cadena con el nombre del supermercado, su ID y los ingresos totales.
     * @throws Exception Si no se encuentran supermercados.
     */
    fun getSupermercadoTopIngresos() : String {
        val supermercado = supermercadoRepository.findAll()
            .maxByOrNull { supermercadoService.getIngresosPorSupermercado(it.id) } ?: throw Exception("No se encontraron supermercados")

        return "${supermercado.nombre} (${supermercado.id}). Ingresos totales: ${supermercadoService.getIngresosPorSupermercado(supermercado.id)}"

    }

    /**
     * Obtiene los supermercados abiertos en un día y hora específicos.
     *
     * Este método filtra los supermercados que están abiertos en un día de la semana y
     * hora específicos, y devuelve una lista de nombres e IDs de los supermercados abiertos.
     *
     * @param diaDeLaSemana El día de la semana en que se desea consultar los supermercados abiertos (en formato de texto).
     * @param hora La hora a la que se desea consultar los supermercados abiertos.
     * @return Una cadena de texto con los nombres e IDs de los supermercados abiertos, separados por comas.
     */
    fun getSupermercadosAbiertos(diaDeLaSemana: String, hora: LocalTime) : String {
        val supermercadosAbiertos = supermercadoRepository.findAll().filter { supermercado ->
            supermercado.diaAbierto.contains(diaDeLaSemana.uppercase()) &&
                    hora.isAfter(supermercado.horarioApertura) &&
                    hora.isBefore(supermercado.horarioCierre)
        }
        return supermercadosAbiertos.joinToString (", ") { supermercado -> "${supermercado.nombre} (${supermercado.id})" }
    }
}