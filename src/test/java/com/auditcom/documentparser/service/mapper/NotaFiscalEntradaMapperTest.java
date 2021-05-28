package com.auditcom.documentparser.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NotaFiscalEntradaMapperTest {

    private NotaFiscalEntradaMapper notaFiscalEntradaMapper;

    @BeforeEach
    public void setUp() {
        notaFiscalEntradaMapper = new NotaFiscalEntradaMapperImpl();
    }
}
