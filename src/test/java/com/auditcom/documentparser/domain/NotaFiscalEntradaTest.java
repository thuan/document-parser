package com.auditcom.documentparser.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.auditcom.documentparser.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NotaFiscalEntradaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotaFiscalEntrada.class);
        NotaFiscalEntrada notaFiscalEntrada1 = new NotaFiscalEntrada();
        notaFiscalEntrada1.setId(1L);
        NotaFiscalEntrada notaFiscalEntrada2 = new NotaFiscalEntrada();
        notaFiscalEntrada2.setId(notaFiscalEntrada1.getId());
        assertThat(notaFiscalEntrada1).isEqualTo(notaFiscalEntrada2);
        notaFiscalEntrada2.setId(2L);
        assertThat(notaFiscalEntrada1).isNotEqualTo(notaFiscalEntrada2);
        notaFiscalEntrada1.setId(null);
        assertThat(notaFiscalEntrada1).isNotEqualTo(notaFiscalEntrada2);
    }
}
