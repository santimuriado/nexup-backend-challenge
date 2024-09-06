package com.nexup_backend_challenge.solucion.entities

import jakarta.persistence.CascadeType
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import java.time.LocalTime

@Entity
class Supermercado (

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,
    val nombre : String,

    @OneToMany (cascade = [CascadeType.ALL], mappedBy = "supermercado")
    val stock: MutableList<Stock> = mutableListOf(),

    val horarioApertura: LocalTime,
    val horarioCierre: LocalTime,

    @ElementCollection
    val diaAbierto: List<String>
)