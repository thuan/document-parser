package com.auditcom.documentparser.repository;

import com.auditcom.documentparser.domain.AlimentoAcordanteEntrada;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AlimentoAcordanteEntrada entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AlimentoAcordanteEntradaRepository extends JpaRepository<AlimentoAcordanteEntrada, Long> {}
