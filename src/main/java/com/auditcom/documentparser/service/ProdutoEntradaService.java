package com.auditcom.documentparser.service;

import com.auditcom.documentparser.service.dto.ProdutoEntradaDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.auditcom.documentparser.domain.ProdutoEntrada}.
 */
public interface ProdutoEntradaService {
    /**
     * Save a produtoEntrada.
     *
     * @param produtoEntradaDTO the entity to save.
     * @return the persisted entity.
     */
    ProdutoEntradaDTO save(ProdutoEntradaDTO produtoEntradaDTO);

    /**
     * Partially updates a produtoEntrada.
     *
     * @param produtoEntradaDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ProdutoEntradaDTO> partialUpdate(ProdutoEntradaDTO produtoEntradaDTO);

    /**
     * Get all the produtoEntradas.
     *
     * @return the list of entities.
     */
    List<ProdutoEntradaDTO> findAll();

    /**
     * Get the "id" produtoEntrada.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProdutoEntradaDTO> findOne(Long id);

    /**
     * Delete the "id" produtoEntrada.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
