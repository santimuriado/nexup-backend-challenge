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
}