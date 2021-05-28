package com.auditcom.documentparser.repository;

import com.auditcom.documentparser.domain.ProdutoEntrada;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ProdutoEntrada entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProdutoEntradaRepository extends JpaRepository<ProdutoEntrada, Long> {}
