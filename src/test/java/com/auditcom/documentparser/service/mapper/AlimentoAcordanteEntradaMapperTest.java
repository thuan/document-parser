package com.auditcom.documentparser.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AlimentoAcordanteEntradaMapperTest {

    private AlimentoAcordanteEntradaMapper alimentoAcordanteEntradaMapper;

    @BeforeEach
    public void setUp() {
        alimentoAcordanteEntradaMapper = new AlimentoAcordanteEntradaMapperImpl();
    }
}
