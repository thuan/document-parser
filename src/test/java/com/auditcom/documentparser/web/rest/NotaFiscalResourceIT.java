package com.auditcom.documentparser.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.auditcom.documentparser.IntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Test class for the NotaFiscalResource REST controller.
 *
 * @see NotaFiscalResource
 */
@IntegrationTest
class NotaFiscalResourceIT {

    private MockMvc restMockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        NotaFiscalResource notaFiscalResource = new NotaFiscalResource();
        restMockMvc = MockMvcBuilders.standaloneSetup(notaFiscalResource).build();
    }

    /**
     * Test enviarNotas
     */
    @Test
    void testEnviarNotas() throws Exception {
        restMockMvc.perform(post("/api/nota-fiscal/enviar-notas")).andExpect(status().isOk());
    }

    /**
     * Test obterNotas
     */
    @Test
    void testObterNotas() throws Exception {
        restMockMvc.perform(get("/api/nota-fiscal/obter-notas")).andExpect(status().isOk());
    }
}
