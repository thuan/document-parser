package com.auditcom.documentparser.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProdutoEntradaMapperTest {

    private ProdutoEntradaMapper produtoEntradaMapper;

    @BeforeEach
    public void setUp() {
        produtoEntradaMapper = new ProdutoEntradaMapperImpl();
    }
}
