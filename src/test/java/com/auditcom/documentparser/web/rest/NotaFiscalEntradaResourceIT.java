package com.auditcom.documentparser.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.auditcom.documentparser.IntegrationTest;
import com.auditcom.documentparser.domain.NotaFiscalEntrada;
import com.auditcom.documentparser.repository.NotaFiscalEntradaRepository;
import com.auditcom.documentparser.service.dto.NotaFiscalEntradaDTO;
import com.auditcom.documentparser.service.mapper.NotaFiscalEntradaMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link NotaFiscalEntradaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NotaFiscalEntradaResourceIT {

    private static final String DEFAULT_ID_NFE = "AAAAAAAAAA";
    private static final String UPDATED_ID_NFE = "BBBBBBBBBB";

    private static final Integer DEFAULT_CRT = 1;
    private static final Integer UPDATED_CRT = 2;

    private static final String DEFAULT_UF_EMITENTE = "AAAAAAAAAA";
    private static final String UPDATED_UF_EMITENTE = "BBBBBBBBBB";

    private static final String DEFAULT_UF_DESTINATARIO = "AAAAAAAAAA";
    private static final String UPDATED_UF_DESTINATARIO = "BBBBBBBBBB";

    private static final Double DEFAULT_VALOR_ITEM = 1D;
    private static final Double UPDATED_VALOR_ITEM = 2D;

    private static final Double DEFAULT_VALOR_IPI = 1D;
    private static final Double UPDATED_VALOR_IPI = 2D;

    private static final Double DEFAULT_VALOR_FRETE = 1D;
    private static final Double UPDATED_VALOR_FRETE = 2D;

    private static final Double DEFAULT_VALOR_SEGURO = 1D;
    private static final Double UPDATED_VALOR_SEGURO = 2D;

    private static final Double DEFAULT_VALOR_OUTROS = 1D;
    private static final Double UPDATED_VALOR_OUTROS = 2D;

    private static final String DEFAULT_CNPJ_EMITENTE = "AAAAAAAAAA";
    private static final String UPDATED_CNPJ_EMITENTE = "BBBBBBBBBB";

    private static final String DEFAULT_CNPJ_DESTINATARIO = "AAAAAAAAAA";
    private static final String UPDATED_CNPJ_DESTINATARIO = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORIA = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORIA = "BBBBBBBBBB";

    private static final String DEFAULT_NORMA_EXECUCAO = "AAAAAAAAAA";
    private static final String UPDATED_NORMA_EXECUCAO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/nota-fiscal-entradas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private NotaFiscalEntradaRepository notaFiscalEntradaRepository;

    @Autowired
    private NotaFiscalEntradaMapper notaFiscalEntradaMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNotaFiscalEntradaMockMvc;

    private NotaFiscalEntrada notaFiscalEntrada;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotaFiscalEntrada createEntity(EntityManager em) {
        NotaFiscalEntrada notaFiscalEntrada = new NotaFiscalEntrada()
            .idNfe(DEFAULT_ID_NFE)
            .crt(DEFAULT_CRT)
            .ufEmitente(DEFAULT_UF_EMITENTE)
            .ufDestinatario(DEFAULT_UF_DESTINATARIO)
            .valorItem(DEFAULT_VALOR_ITEM)
            .valorIPI(DEFAULT_VALOR_IPI)
            .valorFrete(DEFAULT_VALOR_FRETE)
            .valorSeguro(DEFAULT_VALOR_SEGURO)
            .valorOutros(DEFAULT_VALOR_OUTROS)
            .cnpjEmitente(DEFAULT_CNPJ_EMITENTE)
            .cnpjDestinatario(DEFAULT_CNPJ_DESTINATARIO)
            .categoria(DEFAULT_CATEGORIA)
            .normaExecucao(DEFAULT_NORMA_EXECUCAO);
        return notaFiscalEntrada;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotaFiscalEntrada createUpdatedEntity(EntityManager em) {
        NotaFiscalEntrada notaFiscalEntrada = new NotaFiscalEntrada()
            .idNfe(UPDATED_ID_NFE)
            .crt(UPDATED_CRT)
            .ufEmitente(UPDATED_UF_EMITENTE)
            .ufDestinatario(UPDATED_UF_DESTINATARIO)
            .valorItem(UPDATED_VALOR_ITEM)
            .valorIPI(UPDATED_VALOR_IPI)
            .valorFrete(UPDATED_VALOR_FRETE)
            .valorSeguro(UPDATED_VALOR_SEGURO)
            .valorOutros(UPDATED_VALOR_OUTROS)
            .cnpjEmitente(UPDATED_CNPJ_EMITENTE)
            .cnpjDestinatario(UPDATED_CNPJ_DESTINATARIO)
            .categoria(UPDATED_CATEGORIA)
            .normaExecucao(UPDATED_NORMA_EXECUCAO);
        return notaFiscalEntrada;
    }

    @BeforeEach
    public void initTest() {
        notaFiscalEntrada = createEntity(em);
    }

    @Test
    @Transactional
    void createNotaFiscalEntrada() throws Exception {
        int databaseSizeBeforeCreate = notaFiscalEntradaRepository.findAll().size();
        // Create the NotaFiscalEntrada
        NotaFiscalEntradaDTO notaFiscalEntradaDTO = notaFiscalEntradaMapper.toDto(notaFiscalEntrada);
        restNotaFiscalEntradaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notaFiscalEntradaDTO))
            )
            .andExpect(status().isCreated());

        // Validate the NotaFiscalEntrada in the database
        List<NotaFiscalEntrada> notaFiscalEntradaList = notaFiscalEntradaRepository.findAll();
        assertThat(notaFiscalEntradaList).hasSize(databaseSizeBeforeCreate + 1);
        NotaFiscalEntrada testNotaFiscalEntrada = notaFiscalEntradaList.get(notaFiscalEntradaList.size() - 1);
        assertThat(testNotaFiscalEntrada.getIdNfe()).isEqualTo(DEFAULT_ID_NFE);
        assertThat(testNotaFiscalEntrada.getCrt()).isEqualTo(DEFAULT_CRT);
        assertThat(testNotaFiscalEntrada.getUfEmitente()).isEqualTo(DEFAULT_UF_EMITENTE);
        assertThat(testNotaFiscalEntrada.getUfDestinatario()).isEqualTo(DEFAULT_UF_DESTINATARIO);
        assertThat(testNotaFiscalEntrada.getValorItem()).isEqualTo(DEFAULT_VALOR_ITEM);
        assertThat(testNotaFiscalEntrada.getValorIPI()).isEqualTo(DEFAULT_VALOR_IPI);
        assertThat(testNotaFiscalEntrada.getValorFrete()).isEqualTo(DEFAULT_VALOR_FRETE);
        assertThat(testNotaFiscalEntrada.getValorSeguro()).isEqualTo(DEFAULT_VALOR_SEGURO);
        assertThat(testNotaFiscalEntrada.getValorOutros()).isEqualTo(DEFAULT_VALOR_OUTROS);
        assertThat(testNotaFiscalEntrada.getCnpjEmitente()).isEqualTo(DEFAULT_CNPJ_EMITENTE);
        assertThat(testNotaFiscalEntrada.getCnpjDestinatario()).isEqualTo(DEFAULT_CNPJ_DESTINATARIO);
        assertThat(testNotaFiscalEntrada.getCategoria()).isEqualTo(DEFAULT_CATEGORIA);
        assertThat(testNotaFiscalEntrada.getNormaExecucao()).isEqualTo(DEFAULT_NORMA_EXECUCAO);
    }

    @Test
    @Transactional
    void createNotaFiscalEntradaWithExistingId() throws Exception {
        // Create the NotaFiscalEntrada with an existing ID
        notaFiscalEntrada.setId(1L);
        NotaFiscalEntradaDTO notaFiscalEntradaDTO = notaFiscalEntradaMapper.toDto(notaFiscalEntrada);

        int databaseSizeBeforeCreate = notaFiscalEntradaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restNotaFiscalEntradaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notaFiscalEntradaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NotaFiscalEntrada in the database
        List<NotaFiscalEntrada> notaFiscalEntradaList = notaFiscalEntradaRepository.findAll();
        assertThat(notaFiscalEntradaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkIdNfeIsRequired() throws Exception {
        int databaseSizeBeforeTest = notaFiscalEntradaRepository.findAll().size();
        // set the field null
        notaFiscalEntrada.setIdNfe(null);

        // Create the NotaFiscalEntrada, which fails.
        NotaFiscalEntradaDTO notaFiscalEntradaDTO = notaFiscalEntradaMapper.toDto(notaFiscalEntrada);

        restNotaFiscalEntradaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notaFiscalEntradaDTO))
            )
            .andExpect(status().isBadRequest());

        List<NotaFiscalEntrada> notaFiscalEntradaList = notaFiscalEntradaRepository.findAll();
        assertThat(notaFiscalEntradaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllNotaFiscalEntradas() throws Exception {
        // Initialize the database
        notaFiscalEntradaRepository.saveAndFlush(notaFiscalEntrada);

        // Get all the notaFiscalEntradaList
        restNotaFiscalEntradaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notaFiscalEntrada.getId().intValue())))
            .andExpect(jsonPath("$.[*].idNfe").value(hasItem(DEFAULT_ID_NFE)))
            .andExpect(jsonPath("$.[*].crt").value(hasItem(DEFAULT_CRT)))
            .andExpect(jsonPath("$.[*].ufEmitente").value(hasItem(DEFAULT_UF_EMITENTE)))
            .andExpect(jsonPath("$.[*].ufDestinatario").value(hasItem(DEFAULT_UF_DESTINATARIO)))
            .andExpect(jsonPath("$.[*].valorItem").value(hasItem(DEFAULT_VALOR_ITEM.doubleValue())))
            .andExpect(jsonPath("$.[*].valorIPI").value(hasItem(DEFAULT_VALOR_IPI.doubleValue())))
            .andExpect(jsonPath("$.[*].valorFrete").value(hasItem(DEFAULT_VALOR_FRETE.doubleValue())))
            .andExpect(jsonPath("$.[*].valorSeguro").value(hasItem(DEFAULT_VALOR_SEGURO.doubleValue())))
            .andExpect(jsonPath("$.[*].valorOutros").value(hasItem(DEFAULT_VALOR_OUTROS.doubleValue())))
            .andExpect(jsonPath("$.[*].cnpjEmitente").value(hasItem(DEFAULT_CNPJ_EMITENTE)))
            .andExpect(jsonPath("$.[*].cnpjDestinatario").value(hasItem(DEFAULT_CNPJ_DESTINATARIO)))
            .andExpect(jsonPath("$.[*].categoria").value(hasItem(DEFAULT_CATEGORIA)))
            .andExpect(jsonPath("$.[*].normaExecucao").value(hasItem(DEFAULT_NORMA_EXECUCAO)));
    }

    @Test
    @Transactional
    void getNotaFiscalEntrada() throws Exception {
        // Initialize the database
        notaFiscalEntradaRepository.saveAndFlush(notaFiscalEntrada);

        // Get the notaFiscalEntrada
        restNotaFiscalEntradaMockMvc
            .perform(get(ENTITY_API_URL_ID, notaFiscalEntrada.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(notaFiscalEntrada.getId().intValue()))
            .andExpect(jsonPath("$.idNfe").value(DEFAULT_ID_NFE))
            .andExpect(jsonPath("$.crt").value(DEFAULT_CRT))
            .andExpect(jsonPath("$.ufEmitente").value(DEFAULT_UF_EMITENTE))
            .andExpect(jsonPath("$.ufDestinatario").value(DEFAULT_UF_DESTINATARIO))
            .andExpect(jsonPath("$.valorItem").value(DEFAULT_VALOR_ITEM.doubleValue()))
            .andExpect(jsonPath("$.valorIPI").value(DEFAULT_VALOR_IPI.doubleValue()))
            .andExpect(jsonPath("$.valorFrete").value(DEFAULT_VALOR_FRETE.doubleValue()))
            .andExpect(jsonPath("$.valorSeguro").value(DEFAULT_VALOR_SEGURO.doubleValue()))
            .andExpect(jsonPath("$.valorOutros").value(DEFAULT_VALOR_OUTROS.doubleValue()))
            .andExpect(jsonPath("$.cnpjEmitente").value(DEFAULT_CNPJ_EMITENTE))
            .andExpect(jsonPath("$.cnpjDestinatario").value(DEFAULT_CNPJ_DESTINATARIO))
            .andExpect(jsonPath("$.categoria").value(DEFAULT_CATEGORIA))
            .andExpect(jsonPath("$.normaExecucao").value(DEFAULT_NORMA_EXECUCAO));
    }

    @Test
    @Transactional
    void getNonExistingNotaFiscalEntrada() throws Exception {
        // Get the notaFiscalEntrada
        restNotaFiscalEntradaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewNotaFiscalEntrada() throws Exception {
        // Initialize the database
        notaFiscalEntradaRepository.saveAndFlush(notaFiscalEntrada);

        int databaseSizeBeforeUpdate = notaFiscalEntradaRepository.findAll().size();

        // Update the notaFiscalEntrada
        NotaFiscalEntrada updatedNotaFiscalEntrada = notaFiscalEntradaRepository.findById(notaFiscalEntrada.getId()).get();
        // Disconnect from session so that the updates on updatedNotaFiscalEntrada are not directly saved in db
        em.detach(updatedNotaFiscalEntrada);
        updatedNotaFiscalEntrada
            .idNfe(UPDATED_ID_NFE)
            .crt(UPDATED_CRT)
            .ufEmitente(UPDATED_UF_EMITENTE)
            .ufDestinatario(UPDATED_UF_DESTINATARIO)
            .valorItem(UPDATED_VALOR_ITEM)
            .valorIPI(UPDATED_VALOR_IPI)
            .valorFrete(UPDATED_VALOR_FRETE)
            .valorSeguro(UPDATED_VALOR_SEGURO)
            .valorOutros(UPDATED_VALOR_OUTROS)
            .cnpjEmitente(UPDATED_CNPJ_EMITENTE)
            .cnpjDestinatario(UPDATED_CNPJ_DESTINATARIO)
            .categoria(UPDATED_CATEGORIA)
            .normaExecucao(UPDATED_NORMA_EXECUCAO);
        NotaFiscalEntradaDTO notaFiscalEntradaDTO = notaFiscalEntradaMapper.toDto(updatedNotaFiscalEntrada);

        restNotaFiscalEntradaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, notaFiscalEntradaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notaFiscalEntradaDTO))
            )
            .andExpect(status().isOk());

        // Validate the NotaFiscalEntrada in the database
        List<NotaFiscalEntrada> notaFiscalEntradaList = notaFiscalEntradaRepository.findAll();
        assertThat(notaFiscalEntradaList).hasSize(databaseSizeBeforeUpdate);
        NotaFiscalEntrada testNotaFiscalEntrada = notaFiscalEntradaList.get(notaFiscalEntradaList.size() - 1);
        assertThat(testNotaFiscalEntrada.getIdNfe()).isEqualTo(UPDATED_ID_NFE);
        assertThat(testNotaFiscalEntrada.getCrt()).isEqualTo(UPDATED_CRT);
        assertThat(testNotaFiscalEntrada.getUfEmitente()).isEqualTo(UPDATED_UF_EMITENTE);
        assertThat(testNotaFiscalEntrada.getUfDestinatario()).isEqualTo(UPDATED_UF_DESTINATARIO);
        assertThat(testNotaFiscalEntrada.getValorItem()).isEqualTo(UPDATED_VALOR_ITEM);
        assertThat(testNotaFiscalEntrada.getValorIPI()).isEqualTo(UPDATED_VALOR_IPI);
        assertThat(testNotaFiscalEntrada.getValorFrete()).isEqualTo(UPDATED_VALOR_FRETE);
        assertThat(testNotaFiscalEntrada.getValorSeguro()).isEqualTo(UPDATED_VALOR_SEGURO);
        assertThat(testNotaFiscalEntrada.getValorOutros()).isEqualTo(UPDATED_VALOR_OUTROS);
        assertThat(testNotaFiscalEntrada.getCnpjEmitente()).isEqualTo(UPDATED_CNPJ_EMITENTE);
        assertThat(testNotaFiscalEntrada.getCnpjDestinatario()).isEqualTo(UPDATED_CNPJ_DESTINATARIO);
        assertThat(testNotaFiscalEntrada.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testNotaFiscalEntrada.getNormaExecucao()).isEqualTo(UPDATED_NORMA_EXECUCAO);
    }

    @Test
    @Transactional
    void putNonExistingNotaFiscalEntrada() throws Exception {
        int databaseSizeBeforeUpdate = notaFiscalEntradaRepository.findAll().size();
        notaFiscalEntrada.setId(count.incrementAndGet());

        // Create the NotaFiscalEntrada
        NotaFiscalEntradaDTO notaFiscalEntradaDTO = notaFiscalEntradaMapper.toDto(notaFiscalEntrada);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNotaFiscalEntradaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, notaFiscalEntradaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notaFiscalEntradaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NotaFiscalEntrada in the database
        List<NotaFiscalEntrada> notaFiscalEntradaList = notaFiscalEntradaRepository.findAll();
        assertThat(notaFiscalEntradaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchNotaFiscalEntrada() throws Exception {
        int databaseSizeBeforeUpdate = notaFiscalEntradaRepository.findAll().size();
        notaFiscalEntrada.setId(count.incrementAndGet());

        // Create the NotaFiscalEntrada
        NotaFiscalEntradaDTO notaFiscalEntradaDTO = notaFiscalEntradaMapper.toDto(notaFiscalEntrada);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNotaFiscalEntradaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(notaFiscalEntradaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NotaFiscalEntrada in the database
        List<NotaFiscalEntrada> notaFiscalEntradaList = notaFiscalEntradaRepository.findAll();
        assertThat(notaFiscalEntradaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamNotaFiscalEntrada() throws Exception {
        int databaseSizeBeforeUpdate = notaFiscalEntradaRepository.findAll().size();
        notaFiscalEntrada.setId(count.incrementAndGet());

        // Create the NotaFiscalEntrada
        NotaFiscalEntradaDTO notaFiscalEntradaDTO = notaFiscalEntradaMapper.toDto(notaFiscalEntrada);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNotaFiscalEntradaMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(notaFiscalEntradaDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NotaFiscalEntrada in the database
        List<NotaFiscalEntrada> notaFiscalEntradaList = notaFiscalEntradaRepository.findAll();
        assertThat(notaFiscalEntradaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateNotaFiscalEntradaWithPatch() throws Exception {
        // Initialize the database
        notaFiscalEntradaRepository.saveAndFlush(notaFiscalEntrada);

        int databaseSizeBeforeUpdate = notaFiscalEntradaRepository.findAll().size();

        // Update the notaFiscalEntrada using partial update
        NotaFiscalEntrada partialUpdatedNotaFiscalEntrada = new NotaFiscalEntrada();
        partialUpdatedNotaFiscalEntrada.setId(notaFiscalEntrada.getId());

        partialUpdatedNotaFiscalEntrada
            .valorIPI(UPDATED_VALOR_IPI)
            .valorFrete(UPDATED_VALOR_FRETE)
            .valorSeguro(UPDATED_VALOR_SEGURO)
            .valorOutros(UPDATED_VALOR_OUTROS)
            .cnpjDestinatario(UPDATED_CNPJ_DESTINATARIO)
            .normaExecucao(UPDATED_NORMA_EXECUCAO);

        restNotaFiscalEntradaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNotaFiscalEntrada.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNotaFiscalEntrada))
            )
            .andExpect(status().isOk());

        // Validate the NotaFiscalEntrada in the database
        List<NotaFiscalEntrada> notaFiscalEntradaList = notaFiscalEntradaRepository.findAll();
        assertThat(notaFiscalEntradaList).hasSize(databaseSizeBeforeUpdate);
        NotaFiscalEntrada testNotaFiscalEntrada = notaFiscalEntradaList.get(notaFiscalEntradaList.size() - 1);
        assertThat(testNotaFiscalEntrada.getIdNfe()).isEqualTo(DEFAULT_ID_NFE);
        assertThat(testNotaFiscalEntrada.getCrt()).isEqualTo(DEFAULT_CRT);
        assertThat(testNotaFiscalEntrada.getUfEmitente()).isEqualTo(DEFAULT_UF_EMITENTE);
        assertThat(testNotaFiscalEntrada.getUfDestinatario()).isEqualTo(DEFAULT_UF_DESTINATARIO);
        assertThat(testNotaFiscalEntrada.getValorItem()).isEqualTo(DEFAULT_VALOR_ITEM);
        assertThat(testNotaFiscalEntrada.getValorIPI()).isEqualTo(UPDATED_VALOR_IPI);
        assertThat(testNotaFiscalEntrada.getValorFrete()).isEqualTo(UPDATED_VALOR_FRETE);
        assertThat(testNotaFiscalEntrada.getValorSeguro()).isEqualTo(UPDATED_VALOR_SEGURO);
        assertThat(testNotaFiscalEntrada.getValorOutros()).isEqualTo(UPDATED_VALOR_OUTROS);
        assertThat(testNotaFiscalEntrada.getCnpjEmitente()).isEqualTo(DEFAULT_CNPJ_EMITENTE);
        assertThat(testNotaFiscalEntrada.getCnpjDestinatario()).isEqualTo(UPDATED_CNPJ_DESTINATARIO);
        assertThat(testNotaFiscalEntrada.getCategoria()).isEqualTo(DEFAULT_CATEGORIA);
        assertThat(testNotaFiscalEntrada.getNormaExecucao()).isEqualTo(UPDATED_NORMA_EXECUCAO);
    }

    @Test
    @Transactional
    void fullUpdateNotaFiscalEntradaWithPatch() throws Exception {
        // Initialize the database
        notaFiscalEntradaRepository.saveAndFlush(notaFiscalEntrada);

        int databaseSizeBeforeUpdate = notaFiscalEntradaRepository.findAll().size();

        // Update the notaFiscalEntrada using partial update
        NotaFiscalEntrada partialUpdatedNotaFiscalEntrada = new NotaFiscalEntrada();
        partialUpdatedNotaFiscalEntrada.setId(notaFiscalEntrada.getId());

        partialUpdatedNotaFiscalEntrada
            .idNfe(UPDATED_ID_NFE)
            .crt(UPDATED_CRT)
            .ufEmitente(UPDATED_UF_EMITENTE)
            .ufDestinatario(UPDATED_UF_DESTINATARIO)
            .valorItem(UPDATED_VALOR_ITEM)
            .valorIPI(UPDATED_VALOR_IPI)
            .valorFrete(UPDATED_VALOR_FRETE)
            .valorSeguro(UPDATED_VALOR_SEGURO)
            .valorOutros(UPDATED_VALOR_OUTROS)
            .cnpjEmitente(UPDATED_CNPJ_EMITENTE)
            .cnpjDestinatario(UPDATED_CNPJ_DESTINATARIO)
            .categoria(UPDATED_CATEGORIA)
            .normaExecucao(UPDATED_NORMA_EXECUCAO);

        restNotaFiscalEntradaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNotaFiscalEntrada.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNotaFiscalEntrada))
            )
            .andExpect(status().isOk());

        // Validate the NotaFiscalEntrada in the database
        List<NotaFiscalEntrada> notaFiscalEntradaList = notaFiscalEntradaRepository.findAll();
        assertThat(notaFiscalEntradaList).hasSize(databaseSizeBeforeUpdate);
        NotaFiscalEntrada testNotaFiscalEntrada = notaFiscalEntradaList.get(notaFiscalEntradaList.size() - 1);
        assertThat(testNotaFiscalEntrada.getIdNfe()).isEqualTo(UPDATED_ID_NFE);
        assertThat(testNotaFiscalEntrada.getCrt()).isEqualTo(UPDATED_CRT);
        assertThat(testNotaFiscalEntrada.getUfEmitente()).isEqualTo(UPDATED_UF_EMITENTE);
        assertThat(testNotaFiscalEntrada.getUfDestinatario()).isEqualTo(UPDATED_UF_DESTINATARIO);
        assertThat(testNotaFiscalEntrada.getValorItem()).isEqualTo(UPDATED_VALOR_ITEM);
        assertThat(testNotaFiscalEntrada.getValorIPI()).isEqualTo(UPDATED_VALOR_IPI);
        assertThat(testNotaFiscalEntrada.getValorFrete()).isEqualTo(UPDATED_VALOR_FRETE);
        assertThat(testNotaFiscalEntrada.getValorSeguro()).isEqualTo(UPDATED_VALOR_SEGURO);
        assertThat(testNotaFiscalEntrada.getValorOutros()).isEqualTo(UPDATED_VALOR_OUTROS);
        assertThat(testNotaFiscalEntrada.getCnpjEmitente()).isEqualTo(UPDATED_CNPJ_EMITENTE);
        assertThat(testNotaFiscalEntrada.getCnpjDestinatario()).isEqualTo(UPDATED_CNPJ_DESTINATARIO);
        assertThat(testNotaFiscalEntrada.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testNotaFiscalEntrada.getNormaExecucao()).isEqualTo(UPDATED_NORMA_EXECUCAO);
    }

    @Test
    @Transactional
    void patchNonExistingNotaFiscalEntrada() throws Exception {
        int databaseSizeBeforeUpdate = notaFiscalEntradaRepository.findAll().size();
        notaFiscalEntrada.setId(count.incrementAndGet());

        // Create the NotaFiscalEntrada
        NotaFiscalEntradaDTO notaFiscalEntradaDTO = notaFiscalEntradaMapper.toDto(notaFiscalEntrada);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNotaFiscalEntradaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, notaFiscalEntradaDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(notaFiscalEntradaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NotaFiscalEntrada in the database
        List<NotaFiscalEntrada> notaFiscalEntradaList = notaFiscalEntradaRepository.findAll();
        assertThat(notaFiscalEntradaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchNotaFiscalEntrada() throws Exception {
        int databaseSizeBeforeUpdate = notaFiscalEntradaRepository.findAll().size();
        notaFiscalEntrada.setId(count.incrementAndGet());

        // Create the NotaFiscalEntrada
        NotaFiscalEntradaDTO notaFiscalEntradaDTO = notaFiscalEntradaMapper.toDto(notaFiscalEntrada);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNotaFiscalEntradaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(notaFiscalEntradaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NotaFiscalEntrada in the database
        List<NotaFiscalEntrada> notaFiscalEntradaList = notaFiscalEntradaRepository.findAll();
        assertThat(notaFiscalEntradaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamNotaFiscalEntrada() throws Exception {
        int databaseSizeBeforeUpdate = notaFiscalEntradaRepository.findAll().size();
        notaFiscalEntrada.setId(count.incrementAndGet());

        // Create the NotaFiscalEntrada
        NotaFiscalEntradaDTO notaFiscalEntradaDTO = notaFiscalEntradaMapper.toDto(notaFiscalEntrada);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNotaFiscalEntradaMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(notaFiscalEntradaDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NotaFiscalEntrada in the database
        List<NotaFiscalEntrada> notaFiscalEntradaList = notaFiscalEntradaRepository.findAll();
        assertThat(notaFiscalEntradaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteNotaFiscalEntrada() throws Exception {
        // Initialize the database
        notaFiscalEntradaRepository.saveAndFlush(notaFiscalEntrada);

        int databaseSizeBeforeDelete = notaFiscalEntradaRepository.findAll().size();

        // Delete the notaFiscalEntrada
        restNotaFiscalEntradaMockMvc
            .perform(delete(ENTITY_API_URL_ID, notaFiscalEntrada.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NotaFiscalEntrada> notaFiscalEntradaList = notaFiscalEntradaRepository.findAll();
        assertThat(notaFiscalEntradaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
