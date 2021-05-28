package com.auditcom.documentparser.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.auditcom.documentparser.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProdutoEntradaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProdutoEntrada.class);
        ProdutoEntrada produtoEntrada1 = new ProdutoEntrada();
        produtoEntrada1.setId(1L);
        ProdutoEntrada produtoEntrada2 = new ProdutoEntrada();
        produtoEntrada2.setId(produtoEntrada1.getId());
        assertThat(produtoEntrada1).isEqualTo(produtoEntrada2);
        produtoEntrada2.setId(2L);
        assertThat(produtoEntrada1).isNotEqualTo(produtoEntrada2);
        produtoEntrada1.setId(null);
        assertThat(produtoEntrada1).isNotEqualTo(produtoEntrada2);
    }
}
