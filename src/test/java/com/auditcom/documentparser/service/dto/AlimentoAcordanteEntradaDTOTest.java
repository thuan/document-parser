package com.auditcom.documentparser.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.auditcom.documentparser.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AlimentoAcordanteEntradaDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AlimentoAcordanteEntradaDTO.class);
        AlimentoAcordanteEntradaDTO alimentoAcordanteEntradaDTO1 = new AlimentoAcordanteEntradaDTO();
        alimentoAcordanteEntradaDTO1.setId(1L);
        AlimentoAcordanteEntradaDTO alimentoAcordanteEntradaDTO2 = new AlimentoAcordanteEntradaDTO();
        assertThat(alimentoAcordanteEntradaDTO1).isNotEqualTo(alimentoAcordanteEntradaDTO2);
        alimentoAcordanteEntradaDTO2.setId(alimentoAcordanteEntradaDTO1.getId());
        assertThat(alimentoAcordanteEntradaDTO1).isEqualTo(alimentoAcordanteEntradaDTO2);
        alimentoAcordanteEntradaDTO2.setId(2L);
        assertThat(alimentoAcordanteEntradaDTO1).isNotEqualTo(alimentoAcordanteEntradaDTO2);
        alimentoAcordanteEntradaDTO1.setId(null);
        assertThat(alimentoAcordanteEntradaDTO1).isNotEqualTo(alimentoAcordanteEntradaDTO2);
    }
}
