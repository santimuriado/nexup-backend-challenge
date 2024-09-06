package com.nexup_backend_challenge.solucion.repositories

import com.nexup_backend_challenge.solucion.entities.Producto
import org.springframework.data.jpa.repository.JpaRepository

interface ProductoRepository : JpaRepository<Producto,Long> {}