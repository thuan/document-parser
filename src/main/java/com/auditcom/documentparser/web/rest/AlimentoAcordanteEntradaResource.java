package com.auditcom.documentparser.web.rest;

import com.auditcom.documentparser.repository.AlimentoAcordanteEntradaRepository;
import com.auditcom.documentparser.service.AlimentoAcordanteEntradaService;
import com.auditcom.documentparser.service.dto.AlimentoAcordanteEntradaDTO;
import com.auditcom.documentparser.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.auditcom.documentparser.domain.AlimentoAcordanteEntrada}.
 */
@RestController
@RequestMapping("/api")
public class AlimentoAcordanteEntradaResource {

    private final Logger log = LoggerFactory.getLogger(AlimentoAcordanteEntradaResource.class);

    private static final String ENTITY_NAME = "documentparserAlimentoAcordanteEntrada";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AlimentoAcordanteEntradaService alimentoAcordanteEntradaService;

    private final AlimentoAcordanteEntradaRepository alimentoAcordanteEntradaRepository;

    public AlimentoAcordanteEntradaResource(
        AlimentoAcordanteEntradaService alimentoAcordanteEntradaService,
        AlimentoAcordanteEntradaRepository alimentoAcordanteEntradaRepository
    ) {
        this.alimentoAcordanteEntradaService = alimentoAcordanteEntradaService;
        this.alimentoAcordanteEntradaRepository = alimentoAcordanteEntradaRepository;
    }

    /**
     * {@code POST  /alimento-acordante-entradas} : Create a new alimentoAcordanteEntrada.
     *
     * @param alimentoAcordanteEntradaDTO the alimentoAcordanteEntradaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new alimentoAcordanteEntradaDTO, or with status {@code 400 (Bad Request)} if the alimentoAcordanteEntrada has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/alimento-acordante-entradas")
    public ResponseEntity<AlimentoAcordanteEntradaDTO> createAlimentoAcordanteEntrada(
        @Valid @RequestBody AlimentoAcordanteEntradaDTO alimentoAcordanteEntradaDTO
    ) throws URISyntaxException {
        log.debug("REST request to save AlimentoAcordanteEntrada : {}", alimentoAcordanteEntradaDTO);
        if (alimentoAcordanteEntradaDTO.getId() != null) {
            throw new BadRequestAlertException("A new alimentoAcordanteEntrada cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AlimentoAcordanteEntradaDTO result = alimentoAcordanteEntradaService.save(alimentoAcordanteEntradaDTO);
        return ResponseEntity
            .created(new URI("/api/alimento-acordante-entradas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /alimento-acordante-entradas/:id} : Updates an existing alimentoAcordanteEntrada.
     *
     * @param id the id of the alimentoAcordanteEntradaDTO to save.
     * @param alimentoAcordanteEntradaDTO the alimentoAcordanteEntradaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated alimentoAcordanteEntradaDTO,
     * or with status {@code 400 (Bad Request)} if the alimentoAcordanteEntradaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the alimentoAcordanteEntradaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/alimento-acordante-entradas/{id}")
    public ResponseEntity<AlimentoAcordanteEntradaDTO> updateAlimentoAcordanteEntrada(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AlimentoAcordanteEntradaDTO alimentoAcordanteEntradaDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AlimentoAcordanteEntrada : {}, {}", id, alimentoAcordanteEntradaDTO);
        if (alimentoAcordanteEntradaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, alimentoAcordanteEntradaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!alimentoAcordanteEntradaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AlimentoAcordanteEntradaDTO result = alimentoAcordanteEntradaService.save(alimentoAcordanteEntradaDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, alimentoAcordanteEntradaDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /alimento-acordante-entradas/:id} : Partial updates given fields of an existing alimentoAcordanteEntrada, field will ignore if it is null
     *
     * @param id the id of the alimentoAcordanteEntradaDTO to save.
     * @param alimentoAcordanteEntradaDTO the alimentoAcordanteEntradaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated alimentoAcordanteEntradaDTO,
     * or with status {@code 400 (Bad Request)} if the alimentoAcordanteEntradaDTO is not valid,
     * or with status {@code 404 (Not Found)} if the alimentoAcordanteEntradaDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the alimentoAcordanteEntradaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/alimento-acordante-entradas/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<AlimentoAcordanteEntradaDTO> partialUpdateAlimentoAcordanteEntrada(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AlimentoAcordanteEntradaDTO alimentoAcordanteEntradaDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AlimentoAcordanteEntrada partially : {}, {}", id, alimentoAcordanteEntradaDTO);
        if (alimentoAcordanteEntradaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, alimentoAcordanteEntradaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!alimentoAcordanteEntradaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AlimentoAcordanteEntradaDTO> result = alimentoAcordanteEntradaService.partialUpdate(alimentoAcordanteEntradaDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, alimentoAcordanteEntradaDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /alimento-acordante-entradas} : get all the alimentoAcordanteEntradas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of alimentoAcordanteEntradas in body.
     */
    @GetMapping("/alimento-acordante-entradas")
    public List<AlimentoAcordanteEntradaDTO> getAllAlimentoAcordanteEntradas() {
        log.debug("REST request to get all AlimentoAcordanteEntradas");
        return alimentoAcordanteEntradaService.findAll();
    }

    /**
     * {@code GET  /alimento-acordante-entradas/:id} : get the "id" alimentoAcordanteEntrada.
     *
     * @param id the id of the alimentoAcordanteEntradaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the alimentoAcordanteEntradaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/alimento-acordante-entradas/{id}")
    public ResponseEntity<AlimentoAcordanteEntradaDTO> getAlimentoAcordanteEntrada(@PathVariable Long id) {
        log.debug("REST request to get AlimentoAcordanteEntrada : {}", id);
        Optional<AlimentoAcordanteEntradaDTO> alimentoAcordanteEntradaDTO = alimentoAcordanteEntradaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(alimentoAcordanteEntradaDTO);
    }

    /**
     * {@code DELETE  /alimento-acordante-entradas/:id} : delete the "id" alimentoAcordanteEntrada.
     *
     * @param id the id of the alimentoAcordanteEntradaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/alimento-acordante-entradas/{id}")
    public ResponseEntity<Void> deleteAlimentoAcordanteEntrada(@PathVariable Long id) {
        log.debug("REST request to delete AlimentoAcordanteEntrada : {}", id);
        alimentoAcordanteEntradaService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
