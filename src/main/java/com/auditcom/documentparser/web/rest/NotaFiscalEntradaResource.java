package com.auditcom.documentparser.web.rest;

import com.auditcom.documentparser.repository.NotaFiscalEntradaRepository;
import com.auditcom.documentparser.service.NotaFiscalEntradaService;
import com.auditcom.documentparser.service.dto.NotaFiscalEntradaDTO;
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
 * REST controller for managing {@link com.auditcom.documentparser.domain.NotaFiscalEntrada}.
 */
@RestController
@RequestMapping("/api")
public class NotaFiscalEntradaResource {

    private final Logger log = LoggerFactory.getLogger(NotaFiscalEntradaResource.class);

    private static final String ENTITY_NAME = "documentparserNotaFiscalEntrada";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NotaFiscalEntradaService notaFiscalEntradaService;

    private final NotaFiscalEntradaRepository notaFiscalEntradaRepository;

    public NotaFiscalEntradaResource(
        NotaFiscalEntradaService notaFiscalEntradaService,
        NotaFiscalEntradaRepository notaFiscalEntradaRepository
    ) {
        this.notaFiscalEntradaService = notaFiscalEntradaService;
        this.notaFiscalEntradaRepository = notaFiscalEntradaRepository;
    }

    /**
     * {@code POST  /nota-fiscal-entradas} : Create a new notaFiscalEntrada.
     *
     * @param notaFiscalEntradaDTO the notaFiscalEntradaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new notaFiscalEntradaDTO, or with status {@code 400 (Bad Request)} if the notaFiscalEntrada has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nota-fiscal-entradas")
    public ResponseEntity<NotaFiscalEntradaDTO> createNotaFiscalEntrada(@Valid @RequestBody NotaFiscalEntradaDTO notaFiscalEntradaDTO)
        throws URISyntaxException {
        log.debug("REST request to save NotaFiscalEntrada : {}", notaFiscalEntradaDTO);
        if (notaFiscalEntradaDTO.getId() != null) {
            throw new BadRequestAlertException("A new notaFiscalEntrada cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NotaFiscalEntradaDTO result = notaFiscalEntradaService.save(notaFiscalEntradaDTO);
        return ResponseEntity
            .created(new URI("/api/nota-fiscal-entradas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nota-fiscal-entradas/:id} : Updates an existing notaFiscalEntrada.
     *
     * @param id the id of the notaFiscalEntradaDTO to save.
     * @param notaFiscalEntradaDTO the notaFiscalEntradaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated notaFiscalEntradaDTO,
     * or with status {@code 400 (Bad Request)} if the notaFiscalEntradaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the notaFiscalEntradaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nota-fiscal-entradas/{id}")
    public ResponseEntity<NotaFiscalEntradaDTO> updateNotaFiscalEntrada(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody NotaFiscalEntradaDTO notaFiscalEntradaDTO
    ) throws URISyntaxException {
        log.debug("REST request to update NotaFiscalEntrada : {}, {}", id, notaFiscalEntradaDTO);
        if (notaFiscalEntradaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, notaFiscalEntradaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!notaFiscalEntradaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        NotaFiscalEntradaDTO result = notaFiscalEntradaService.save(notaFiscalEntradaDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, notaFiscalEntradaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /nota-fiscal-entradas/:id} : Partial updates given fields of an existing notaFiscalEntrada, field will ignore if it is null
     *
     * @param id the id of the notaFiscalEntradaDTO to save.
     * @param notaFiscalEntradaDTO the notaFiscalEntradaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated notaFiscalEntradaDTO,
     * or with status {@code 400 (Bad Request)} if the notaFiscalEntradaDTO is not valid,
     * or with status {@code 404 (Not Found)} if the notaFiscalEntradaDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the notaFiscalEntradaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/nota-fiscal-entradas/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<NotaFiscalEntradaDTO> partialUpdateNotaFiscalEntrada(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody NotaFiscalEntradaDTO notaFiscalEntradaDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update NotaFiscalEntrada partially : {}, {}", id, notaFiscalEntradaDTO);
        if (notaFiscalEntradaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, notaFiscalEntradaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!notaFiscalEntradaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<NotaFiscalEntradaDTO> result = notaFiscalEntradaService.partialUpdate(notaFiscalEntradaDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, notaFiscalEntradaDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /nota-fiscal-entradas} : get all the notaFiscalEntradas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of notaFiscalEntradas in body.
     */
    @GetMapping("/nota-fiscal-entradas")
    public List<NotaFiscalEntradaDTO> getAllNotaFiscalEntradas() {
        log.debug("REST request to get all NotaFiscalEntradas");
        return notaFiscalEntradaService.findAll();
    }

    /**
     * {@code GET  /nota-fiscal-entradas/:id} : get the "id" notaFiscalEntrada.
     *
     * @param id the id of the notaFiscalEntradaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the notaFiscalEntradaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nota-fiscal-entradas/{id}")
    public ResponseEntity<NotaFiscalEntradaDTO> getNotaFiscalEntrada(@PathVariable Long id) {
        log.debug("REST request to get NotaFiscalEntrada : {}", id);
        Optional<NotaFiscalEntradaDTO> notaFiscalEntradaDTO = notaFiscalEntradaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(notaFiscalEntradaDTO);
    }

    /**
     * {@code DELETE  /nota-fiscal-entradas/:id} : delete the "id" notaFiscalEntrada.
     *
     * @param id the id of the notaFiscalEntradaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nota-fiscal-entradas/{id}")
    public ResponseEntity<Void> deleteNotaFiscalEntrada(@PathVariable Long id) {
        log.debug("REST request to delete NotaFiscalEntrada : {}", id);
        notaFiscalEntradaService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
