package com.example.back21;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // Sobe o servidor inteiro na RAM para o teste
@AutoConfigureMockMvc // Configura o "cliente fantasma"
class AccountIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldRegisterAccountAndPrintResponse() throws Exception {
        // O JSON que o Host C enviaria
        String jsonRequest = """
                {
                    "username": "gabriel_dev",
                    "email": "gabriel@test.com",
                    "password": "senha_longa_123"
                }
                """;

        mockMvc.perform(post("/api/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andDo(print()) // É aqui que o retorno aparece no seu terminal
                .andExpect(status().isCreated()); // Verifica se voltou 201
    }
}