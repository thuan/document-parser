package com.auditcom.documentparser.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.auditcom.documentparser.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProdutoEntradaDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProdutoEntradaDTO.class);
        ProdutoEntradaDTO produtoEntradaDTO1 = new ProdutoEntradaDTO();
        produtoEntradaDTO1.setId(1L);
        ProdutoEntradaDTO produtoEntradaDTO2 = new ProdutoEntradaDTO();
        assertThat(produtoEntradaDTO1).isNotEqualTo(produtoEntradaDTO2);
        produtoEntradaDTO2.setId(produtoEntradaDTO1.getId());
        assertThat(produtoEntradaDTO1).isEqualTo(produtoEntradaDTO2);
        produtoEntradaDTO2.setId(2L);
        assertThat(produtoEntradaDTO1).isNotEqualTo(produtoEntradaDTO2);
        produtoEntradaDTO1.setId(null);
        assertThat(produtoEntradaDTO1).isNotEqualTo(produtoEntradaDTO2);
    }
}
