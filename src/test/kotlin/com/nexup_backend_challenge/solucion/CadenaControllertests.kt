package com.nexup_backend_challenge.solucion

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class CadenaControllertests(@Autowired val mockMvc: MockMvc) {

    @Test
    fun `devuelve top 5 de productos`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/cadena/top5Productos"))
            .andExpect(status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("Pan: 55 - Leche: 40 - Cereal: 30 - Huevos: 27 - Jugo de Naranja: 25"))
    }

    @Test
    fun `devuelve ingresos totales de la cadena`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/cadena/ingresosTotales"))
            .andExpect(status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("406.25"))
    }

    @Test
    fun `devuelve el supermercado con mas ingresos`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/cadena/supermercadoTopIngresos"))
            .andExpect(status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("Supermercado Norte (2). Ingresos totales: 188.75"))
    }

    @Test
    fun `devuelve supermercados abiertos`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/cadena/abierto")
                .param("diaDeLaSemana", "lunes")
                .param("hora", "10:00:01"))
            .andExpect(status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("Supermercado Central (1), Supermercado Norte (2)"))
    }

}