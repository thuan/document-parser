package com.auditcom.documentparser.service;

import com.auditcom.documentparser.service.dto.AlimentoAcordanteEntradaDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.auditcom.documentparser.domain.AlimentoAcordanteEntrada}.
 */
public interface AlimentoAcordanteEntradaService {
    /**
     * Save a alimentoAcordanteEntrada.
     *
     * @param alimentoAcordanteEntradaDTO the entity to save.
     * @return the persisted entity.
     */
    AlimentoAcordanteEntradaDTO save(AlimentoAcordanteEntradaDTO alimentoAcordanteEntradaDTO);

    /**
     * Partially updates a alimentoAcordanteEntrada.
     *
     * @param alimentoAcordanteEntradaDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AlimentoAcordanteEntradaDTO> partialUpdate(AlimentoAcordanteEntradaDTO alimentoAcordanteEntradaDTO);

    /**
     * Get all the alimentoAcordanteEntradas.
     *
     * @return the list of entities.
     */
    List<AlimentoAcordanteEntradaDTO> findAll();

    /**
     * Get the "id" alimentoAcordanteEntrada.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AlimentoAcordanteEntradaDTO> findOne(Long id);

    /**
     * Delete the "id" alimentoAcordanteEntrada.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
