package com.auditcom.documentparser.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.auditcom.documentparser.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NotaFiscalEntradaDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotaFiscalEntradaDTO.class);
        NotaFiscalEntradaDTO notaFiscalEntradaDTO1 = new NotaFiscalEntradaDTO();
        notaFiscalEntradaDTO1.setId(1L);
        NotaFiscalEntradaDTO notaFiscalEntradaDTO2 = new NotaFiscalEntradaDTO();
        assertThat(notaFiscalEntradaDTO1).isNotEqualTo(notaFiscalEntradaDTO2);
        notaFiscalEntradaDTO2.setId(notaFiscalEntradaDTO1.getId());
        assertThat(notaFiscalEntradaDTO1).isEqualTo(notaFiscalEntradaDTO2);
        notaFiscalEntradaDTO2.setId(2L);
        assertThat(notaFiscalEntradaDTO1).isNotEqualTo(notaFiscalEntradaDTO2);
        notaFiscalEntradaDTO1.setId(null);
        assertThat(notaFiscalEntradaDTO1).isNotEqualTo(notaFiscalEntradaDTO2);
    }
}
