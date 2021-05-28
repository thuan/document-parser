package com.auditcom.documentparser.web.rest;

import com.auditcom.documentparser.repository.ProdutoEntradaRepository;
import com.auditcom.documentparser.service.ProdutoEntradaService;
import com.auditcom.documentparser.service.dto.ProdutoEntradaDTO;
import com.auditcom.documentparser.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.auditcom.documentparser.domain.ProdutoEntrada}.
 */
@RestController
@RequestMapping("/api")
public class ProdutoEntradaResource {

    private final Logger log = LoggerFactory.getLogger(ProdutoEntradaResource.class);

    private static final String ENTITY_NAME = "documentparserProdutoEntrada";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProdutoEntradaService produtoEntradaService;

    private final ProdutoEntradaRepository produtoEntradaRepository;

    public ProdutoEntradaResource(ProdutoEntradaService produtoEntradaService, ProdutoEntradaRepository produtoEntradaRepository) {
        this.produtoEntradaService = produtoEntradaService;
        this.produtoEntradaRepository = produtoEntradaRepository;
    }

    /**
     * {@code POST  /produto-entradas} : Create a new produtoEntrada.
     *
     * @param produtoEntradaDTO the produtoEntradaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new produtoEntradaDTO, or with status {@code 400 (Bad Request)} if the produtoEntrada has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/produto-entradas")
    public ResponseEntity<ProdutoEntradaDTO> createProdutoEntrada(@RequestBody ProdutoEntradaDTO produtoEntradaDTO)
        throws URISyntaxException {
        log.debug("REST request to save ProdutoEntrada : {}", produtoEntradaDTO);
        if (produtoEntradaDTO.getId() != null) {
            throw new BadRequestAlertException("A new produtoEntrada cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProdutoEntradaDTO result = produtoEntradaService.save(produtoEntradaDTO);
        return ResponseEntity
            .created(new URI("/api/produto-entradas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /produto-entradas/:id} : Updates an existing produtoEntrada.
     *
     * @param id the id of the produtoEntradaDTO to save.
     * @param produtoEntradaDTO the produtoEntradaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated produtoEntradaDTO,
     * or with status {@code 400 (Bad Request)} if the produtoEntradaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the produtoEntradaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/produto-entradas/{id}")
    public ResponseEntity<ProdutoEntradaDTO> updateProdutoEntrada(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ProdutoEntradaDTO produtoEntradaDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ProdutoEntrada : {}, {}", id, produtoEntradaDTO);
        if (produtoEntradaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, produtoEntradaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!produtoEntradaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ProdutoEntradaDTO result = produtoEntradaService.save(produtoEntradaDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, produtoEntradaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /produto-entradas/:id} : Partial updates given fields of an existing produtoEntrada, field will ignore if it is null
     *
     * @param id the id of the produtoEntradaDTO to save.
     * @param produtoEntradaDTO the produtoEntradaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated produtoEntradaDTO,
     * or with status {@code 400 (Bad Request)} if the produtoEntradaDTO is not valid,
     * or with status {@code 404 (Not Found)} if the produtoEntradaDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the produtoEntradaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/produto-entradas/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<ProdutoEntradaDTO> partialUpdateProdutoEntrada(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ProdutoEntradaDTO produtoEntradaDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProdutoEntrada partially : {}, {}", id, produtoEntradaDTO);
        if (produtoEntradaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, produtoEntradaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!produtoEntradaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProdutoEntradaDTO> result = produtoEntradaService.partialUpdate(produtoEntradaDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, produtoEntradaDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /produto-entradas} : get all the produtoEntradas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of produtoEntradas in body.
     */
    @GetMapping("/produto-entradas")
    public List<ProdutoEntradaDTO> getAllProdutoEntradas() {
        log.debug("REST request to get all ProdutoEntradas");
        return produtoEntradaService.findAll();
    }

    /**
     * {@code GET  /produto-entradas/:id} : get the "id" produtoEntrada.
     *
     * @param id the id of the produtoEntradaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the produtoEntradaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/produto-entradas/{id}")
    public ResponseEntity<ProdutoEntradaDTO> getProdutoEntrada(@PathVariable Long id) {
        log.debug("REST request to get ProdutoEntrada : {}", id);
        Optional<ProdutoEntradaDTO> produtoEntradaDTO = produtoEntradaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(produtoEntradaDTO);
    }

    /**
     * {@code DELETE  /produto-entradas/:id} : delete the "id" produtoEntrada.
     *
     * @param id the id of the produtoEntradaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/produto-entradas/{id}")
    public ResponseEntity<Void> deleteProdutoEntrada(@PathVariable Long id) {
        log.debug("REST request to delete ProdutoEntrada : {}", id);
        produtoEntradaService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
