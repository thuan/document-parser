package com.auditcom.documentparser.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.auditcom.documentparser.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AlimentoAcordanteEntradaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AlimentoAcordanteEntrada.class);
        AlimentoAcordanteEntrada alimentoAcordanteEntrada1 = new AlimentoAcordanteEntrada();
        alimentoAcordanteEntrada1.setId(1L);
        AlimentoAcordanteEntrada alimentoAcordanteEntrada2 = new AlimentoAcordanteEntrada();
        alimentoAcordanteEntrada2.setId(alimentoAcordanteEntrada1.getId());
        assertThat(alimentoAcordanteEntrada1).isEqualTo(alimentoAcordanteEntrada2);
        alimentoAcordanteEntrada2.setId(2L);
        assertThat(alimentoAcordanteEntrada1).isNotEqualTo(alimentoAcordanteEntrada2);
        alimentoAcordanteEntrada1.setId(null);
        assertThat(alimentoAcordanteEntrada1).isNotEqualTo(alimentoAcordanteEntrada2);
    }
}
