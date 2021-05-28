package com.auditcom.documentparser.service;

import com.auditcom.documentparser.service.dto.NotaFiscalEntradaDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.auditcom.documentparser.domain.NotaFiscalEntrada}.
 */
public interface NotaFiscalEntradaService {
    /**
     * Save a notaFiscalEntrada.
     *
     * @param notaFiscalEntradaDTO the entity to save.
     * @return the persisted entity.
     */
    NotaFiscalEntradaDTO save(NotaFiscalEntradaDTO notaFiscalEntradaDTO);

    /**
     * Partially updates a notaFiscalEntrada.
     *
     * @param notaFiscalEntradaDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<NotaFiscalEntradaDTO> partialUpdate(NotaFiscalEntradaDTO notaFiscalEntradaDTO);

    /**
     * Get all the notaFiscalEntradas.
     *
     * @return the list of entities.
     */
    List<NotaFiscalEntradaDTO> findAll();

    /**
     * Get the "id" notaFiscalEntrada.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NotaFiscalEntradaDTO> findOne(Long id);

    /**
     * Delete the "id" notaFiscalEntrada.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
