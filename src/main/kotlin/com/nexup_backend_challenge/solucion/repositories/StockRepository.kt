package com.nexup_backend_challenge.solucion.repositories

import com.nexup_backend_challenge.solucion.entities.Producto
import com.nexup_backend_challenge.solucion.entities.Stock
import com.nexup_backend_challenge.solucion.entities.Supermercado
import org.springframework.data.jpa.repository.JpaRepository

interface StockRepository: JpaRepository<Stock,Long> {
    fun findBySupermercadoAndProducto(supermercado: Supermercado, producto: Producto): Stock?
}