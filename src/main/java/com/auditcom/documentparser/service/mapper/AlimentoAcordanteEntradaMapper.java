package com.auditcom.documentparser.service.mapper;

import com.auditcom.documentparser.domain.*;
import com.auditcom.documentparser.service.dto.AlimentoAcordanteEntradaDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AlimentoAcordanteEntrada} and its DTO {@link AlimentoAcordanteEntradaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AlimentoAcordanteEntradaMapper extends EntityMapper<AlimentoAcordanteEntradaDTO, AlimentoAcordanteEntrada> {}
