package com.auditcom.documentparser.service.mapper;

import com.auditcom.documentparser.domain.*;
import com.auditcom.documentparser.service.dto.NotaFiscalEntradaDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NotaFiscalEntrada} and its DTO {@link NotaFiscalEntradaDTO}.
 */
@Mapper(componentModel = "spring", uses = { ProdutoEntradaMapper.class })
public interface NotaFiscalEntradaMapper extends EntityMapper<NotaFiscalEntradaDTO, NotaFiscalEntrada> {
    @Mapping(target = "produtoEntrada", source = "produtoEntrada", qualifiedByName = "id")
    NotaFiscalEntradaDTO toDto(NotaFiscalEntrada s);
}
