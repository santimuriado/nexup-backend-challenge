package com.nexup_backend_challenge.solucion.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id


@Entity
class Producto (

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,
    val nombre: String,
    val precio: Double,
)