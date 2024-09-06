package com.nexup_backend_challenge.solucion.repositories

import com.nexup_backend_challenge.solucion.entities.Supermercado
import org.springframework.data.jpa.repository.JpaRepository

interface SupermercadoRepository: JpaRepository<Supermercado,Long> {}