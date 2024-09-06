package com.nexup_backend_challenge.solucion.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne


@Entity
class Stock (

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,

    @ManyToOne
    @JoinColumn(name = "producto_id")
    val producto: Producto,

    var cantidad: Int,

    @ManyToOne
    @JoinColumn(name = "supermercado_id")
    val supermercado: Supermercado,

    var cantidadVendida: Int = 0
)