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
class SupermercadoControllerTests (@Autowired val mockMvc: MockMvc) {

    @Test
    fun `registrar venta y devolver precio total`() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/supermercado/1/productos/2/vender")
                .param("cantidad", "7"))
            .andExpect(status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("8.4"))
    }

    @Test
    fun `registrar venta con supermercado no encontrado`() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/supermercado/120/productos/2/vender")
                .param("cantidad", "7"))
            .andExpect(status().isNotFound)
            .andExpect(MockMvcResultMatchers.content().string("Supermercado no encontrado"))
    }

    @Test
    fun `registrar venta con producto no encontrado`() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/supermercado/1/productos/221/vender")
                .param("cantidad", "7"))
            .andExpect(status().isNotFound)
            .andExpect(MockMvcResultMatchers.content().string("Producto no encontrado"))
    }

    @Test
    fun `registrar venta con stock insuficiente`() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/supermercado/1/productos/2/vender")
                .param("cantidad", "3000"))
            .andExpect(status().isBadRequest)
            .andExpect(MockMvcResultMatchers.content().string("Stock insuficiente"))
    }

    @Test
    fun `devuelve cantidad vendida de un producto en un supermercado`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/supermercado/1/productos/4/cantidadVendida"))
            .andExpect(status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("5"))
    }

    @Test
    fun `devuelve cantidad vendida de un producto en un supermercado inexistente`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/supermercado/123/productos/4/cantidadVendida"))
            .andExpect(status().isNotFound)
            .andExpect(MockMvcResultMatchers.content().string("Supermercado no encontrado"))
    }

    @Test
    fun `devuelve cantidad vendida de un producto inexistente en un supermercado`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/supermercado/1/productos/312/cantidadVendida"))
            .andExpect(status().isNotFound)
            .andExpect(MockMvcResultMatchers.content().string("Producto no encontrado"))
    }

    @Test
    fun `devuelve cantidad vendida de un producto que no existe en este supermercado`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/supermercado/1/productos/7/cantidadVendida"))
            .andExpect(status().isNotFound)
            .andExpect(MockMvcResultMatchers.content().string("Producto no disponible en este Supermercado"))
    }

}

