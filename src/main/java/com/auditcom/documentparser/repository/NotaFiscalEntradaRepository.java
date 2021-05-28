package com.auditcom.documentparser.repository;

import com.auditcom.documentparser.domain.NotaFiscalEntrada;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the NotaFiscalEntrada entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NotaFiscalEntradaRepository extends JpaRepository<NotaFiscalEntrada, Long> {}
