package com.auditcom.documentparser.service.mapper;

import com.auditcom.documentparser.domain.*;
import com.auditcom.documentparser.service.dto.ProdutoEntradaDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProdutoEntrada} and its DTO {@link ProdutoEntradaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProdutoEntradaMapper extends EntityMapper<ProdutoEntradaDTO, ProdutoEntrada> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProdutoEntradaDTO toDtoId(ProdutoEntrada produtoEntrada);
}
