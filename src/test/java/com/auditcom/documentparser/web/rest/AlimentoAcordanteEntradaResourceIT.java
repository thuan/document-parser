package com.auditcom.documentparser.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.auditcom.documentparser.IntegrationTest;
import com.auditcom.documentparser.domain.AlimentoAcordanteEntrada;
import com.auditcom.documentparser.repository.AlimentoAcordanteEntradaRepository;
import com.auditcom.documentparser.service.dto.AlimentoAcordanteEntradaDTO;
import com.auditcom.documentparser.service.mapper.AlimentoAcordanteEntradaMapper;
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
 * Integration tests for the {@link AlimentoAcordanteEntradaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AlimentoAcordanteEntradaResourceIT {

    private static final String DEFAULT_ID_N_FE = "AAAAAAAAAA";
    private static final String UPDATED_ID_N_FE = "BBBBBBBBBB";

    private static final Integer DEFAULT_CRT = 1;
    private static final Integer UPDATED_CRT = 2;

    private static final Double DEFAULT_PERCENTUAL_ICMS = 1D;
    private static final Double UPDATED_PERCENTUAL_ICMS = 2D;

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

    private static final Integer DEFAULT_CFOP = 1;
    private static final Integer UPDATED_CFOP = 2;

    private static final String DEFAULT_CNPJ_EMITENTE = "AAAAAAAAAA";
    private static final String UPDATED_CNPJ_EMITENTE = "BBBBBBBBBB";

    private static final String DEFAULT_CNPJ_DESTINATARIO = "AAAAAAAAAA";
    private static final String UPDATED_CNPJ_DESTINATARIO = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORIA = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORIA = "BBBBBBBBBB";

    private static final String DEFAULT_CST = "AAAAAAAAAA";
    private static final String UPDATED_CST = "BBBBBBBBBB";

    private static final String DEFAULT_NORMA_EXECUCAO = "AAAAAAAAAA";
    private static final String UPDATED_NORMA_EXECUCAO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/alimento-acordante-entradas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AlimentoAcordanteEntradaRepository alimentoAcordanteEntradaRepository;

    @Autowired
    private AlimentoAcordanteEntradaMapper alimentoAcordanteEntradaMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAlimentoAcordanteEntradaMockMvc;

    private AlimentoAcordanteEntrada alimentoAcordanteEntrada;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AlimentoAcordanteEntrada createEntity(EntityManager em) {
        AlimentoAcordanteEntrada alimentoAcordanteEntrada = new AlimentoAcordanteEntrada()
            .idNFe(DEFAULT_ID_N_FE)
            .crt(DEFAULT_CRT)
            .percentualICMS(DEFAULT_PERCENTUAL_ICMS)
            .ufEmitente(DEFAULT_UF_EMITENTE)
            .ufDestinatario(DEFAULT_UF_DESTINATARIO)
            .valorItem(DEFAULT_VALOR_ITEM)
            .valorIPI(DEFAULT_VALOR_IPI)
            .valorFrete(DEFAULT_VALOR_FRETE)
            .valorSeguro(DEFAULT_VALOR_SEGURO)
            .valorOutros(DEFAULT_VALOR_OUTROS)
            .cfop(DEFAULT_CFOP)
            .cnpjEmitente(DEFAULT_CNPJ_EMITENTE)
            .cnpjDestinatario(DEFAULT_CNPJ_DESTINATARIO)
            .categoria(DEFAULT_CATEGORIA)
            .cst(DEFAULT_CST)
            .normaExecucao(DEFAULT_NORMA_EXECUCAO);
        return alimentoAcordanteEntrada;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AlimentoAcordanteEntrada createUpdatedEntity(EntityManager em) {
        AlimentoAcordanteEntrada alimentoAcordanteEntrada = new AlimentoAcordanteEntrada()
            .idNFe(UPDATED_ID_N_FE)
            .crt(UPDATED_CRT)
            .percentualICMS(UPDATED_PERCENTUAL_ICMS)
            .ufEmitente(UPDATED_UF_EMITENTE)
            .ufDestinatario(UPDATED_UF_DESTINATARIO)
            .valorItem(UPDATED_VALOR_ITEM)
            .valorIPI(UPDATED_VALOR_IPI)
            .valorFrete(UPDATED_VALOR_FRETE)
            .valorSeguro(UPDATED_VALOR_SEGURO)
            .valorOutros(UPDATED_VALOR_OUTROS)
            .cfop(UPDATED_CFOP)
            .cnpjEmitente(UPDATED_CNPJ_EMITENTE)
            .cnpjDestinatario(UPDATED_CNPJ_DESTINATARIO)
            .categoria(UPDATED_CATEGORIA)
            .cst(UPDATED_CST)
            .normaExecucao(UPDATED_NORMA_EXECUCAO);
        return alimentoAcordanteEntrada;
    }

    @BeforeEach
    public void initTest() {
        alimentoAcordanteEntrada = createEntity(em);
    }

    @Test
    @Transactional
    void createAlimentoAcordanteEntrada() throws Exception {
        int databaseSizeBeforeCreate = alimentoAcordanteEntradaRepository.findAll().size();
        // Create the AlimentoAcordanteEntrada
        AlimentoAcordanteEntradaDTO alimentoAcordanteEntradaDTO = alimentoAcordanteEntradaMapper.toDto(alimentoAcordanteEntrada);
        restAlimentoAcordanteEntradaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(alimentoAcordanteEntradaDTO))
            )
            .andExpect(status().isCreated());

        // Validate the AlimentoAcordanteEntrada in the database
        List<AlimentoAcordanteEntrada> alimentoAcordanteEntradaList = alimentoAcordanteEntradaRepository.findAll();
        assertThat(alimentoAcordanteEntradaList).hasSize(databaseSizeBeforeCreate + 1);
        AlimentoAcordanteEntrada testAlimentoAcordanteEntrada = alimentoAcordanteEntradaList.get(alimentoAcordanteEntradaList.size() - 1);
        assertThat(testAlimentoAcordanteEntrada.getIdNFe()).isEqualTo(DEFAULT_ID_N_FE);
        assertThat(testAlimentoAcordanteEntrada.getCrt()).isEqualTo(DEFAULT_CRT);
        assertThat(testAlimentoAcordanteEntrada.getPercentualICMS()).isEqualTo(DEFAULT_PERCENTUAL_ICMS);
        assertThat(testAlimentoAcordanteEntrada.getUfEmitente()).isEqualTo(DEFAULT_UF_EMITENTE);
        assertThat(testAlimentoAcordanteEntrada.getUfDestinatario()).isEqualTo(DEFAULT_UF_DESTINATARIO);
        assertThat(testAlimentoAcordanteEntrada.getValorItem()).isEqualTo(DEFAULT_VALOR_ITEM);
        assertThat(testAlimentoAcordanteEntrada.getValorIPI()).isEqualTo(DEFAULT_VALOR_IPI);
        assertThat(testAlimentoAcordanteEntrada.getValorFrete()).isEqualTo(DEFAULT_VALOR_FRETE);
        assertThat(testAlimentoAcordanteEntrada.getValorSeguro()).isEqualTo(DEFAULT_VALOR_SEGURO);
        assertThat(testAlimentoAcordanteEntrada.getValorOutros()).isEqualTo(DEFAULT_VALOR_OUTROS);
        assertThat(testAlimentoAcordanteEntrada.getCfop()).isEqualTo(DEFAULT_CFOP);
        assertThat(testAlimentoAcordanteEntrada.getCnpjEmitente()).isEqualTo(DEFAULT_CNPJ_EMITENTE);
        assertThat(testAlimentoAcordanteEntrada.getCnpjDestinatario()).isEqualTo(DEFAULT_CNPJ_DESTINATARIO);
        assertThat(testAlimentoAcordanteEntrada.getCategoria()).isEqualTo(DEFAULT_CATEGORIA);
        assertThat(testAlimentoAcordanteEntrada.getCst()).isEqualTo(DEFAULT_CST);
        assertThat(testAlimentoAcordanteEntrada.getNormaExecucao()).isEqualTo(DEFAULT_NORMA_EXECUCAO);
    }

    @Test
    @Transactional
    void createAlimentoAcordanteEntradaWithExistingId() throws Exception {
        // Create the AlimentoAcordanteEntrada with an existing ID
        alimentoAcordanteEntrada.setId(1L);
        AlimentoAcordanteEntradaDTO alimentoAcordanteEntradaDTO = alimentoAcordanteEntradaMapper.toDto(alimentoAcordanteEntrada);

        int databaseSizeBeforeCreate = alimentoAcordanteEntradaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAlimentoAcordanteEntradaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(alimentoAcordanteEntradaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AlimentoAcordanteEntrada in the database
        List<AlimentoAcordanteEntrada> alimentoAcordanteEntradaList = alimentoAcordanteEntradaRepository.findAll();
        assertThat(alimentoAcordanteEntradaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkIdNFeIsRequired() throws Exception {
        int databaseSizeBeforeTest = alimentoAcordanteEntradaRepository.findAll().size();
        // set the field null
        alimentoAcordanteEntrada.setIdNFe(null);

        // Create the AlimentoAcordanteEntrada, which fails.
        AlimentoAcordanteEntradaDTO alimentoAcordanteEntradaDTO = alimentoAcordanteEntradaMapper.toDto(alimentoAcordanteEntrada);

        restAlimentoAcordanteEntradaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(alimentoAcordanteEntradaDTO))
            )
            .andExpect(status().isBadRequest());

        List<AlimentoAcordanteEntrada> alimentoAcordanteEntradaList = alimentoAcordanteEntradaRepository.findAll();
        assertThat(alimentoAcordanteEntradaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAlimentoAcordanteEntradas() throws Exception {
        // Initialize the database
        alimentoAcordanteEntradaRepository.saveAndFlush(alimentoAcordanteEntrada);

        // Get all the alimentoAcordanteEntradaList
        restAlimentoAcordanteEntradaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(alimentoAcordanteEntrada.getId().intValue())))
            .andExpect(jsonPath("$.[*].idNFe").value(hasItem(DEFAULT_ID_N_FE)))
            .andExpect(jsonPath("$.[*].crt").value(hasItem(DEFAULT_CRT)))
            .andExpect(jsonPath("$.[*].percentualICMS").value(hasItem(DEFAULT_PERCENTUAL_ICMS.doubleValue())))
            .andExpect(jsonPath("$.[*].ufEmitente").value(hasItem(DEFAULT_UF_EMITENTE)))
            .andExpect(jsonPath("$.[*].ufDestinatario").value(hasItem(DEFAULT_UF_DESTINATARIO)))
            .andExpect(jsonPath("$.[*].valorItem").value(hasItem(DEFAULT_VALOR_ITEM.doubleValue())))
            .andExpect(jsonPath("$.[*].valorIPI").value(hasItem(DEFAULT_VALOR_IPI.doubleValue())))
            .andExpect(jsonPath("$.[*].valorFrete").value(hasItem(DEFAULT_VALOR_FRETE.doubleValue())))
            .andExpect(jsonPath("$.[*].valorSeguro").value(hasItem(DEFAULT_VALOR_SEGURO.doubleValue())))
            .andExpect(jsonPath("$.[*].valorOutros").value(hasItem(DEFAULT_VALOR_OUTROS.doubleValue())))
            .andExpect(jsonPath("$.[*].cfop").value(hasItem(DEFAULT_CFOP)))
            .andExpect(jsonPath("$.[*].cnpjEmitente").value(hasItem(DEFAULT_CNPJ_EMITENTE)))
            .andExpect(jsonPath("$.[*].cnpjDestinatario").value(hasItem(DEFAULT_CNPJ_DESTINATARIO)))
            .andExpect(jsonPath("$.[*].categoria").value(hasItem(DEFAULT_CATEGORIA)))
            .andExpect(jsonPath("$.[*].cst").value(hasItem(DEFAULT_CST)))
            .andExpect(jsonPath("$.[*].normaExecucao").value(hasItem(DEFAULT_NORMA_EXECUCAO)));
    }

    @Test
    @Transactional
    void getAlimentoAcordanteEntrada() throws Exception {
        // Initialize the database
        alimentoAcordanteEntradaRepository.saveAndFlush(alimentoAcordanteEntrada);

        // Get the alimentoAcordanteEntrada
        restAlimentoAcordanteEntradaMockMvc
            .perform(get(ENTITY_API_URL_ID, alimentoAcordanteEntrada.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(alimentoAcordanteEntrada.getId().intValue()))
            .andExpect(jsonPath("$.idNFe").value(DEFAULT_ID_N_FE))
            .andExpect(jsonPath("$.crt").value(DEFAULT_CRT))
            .andExpect(jsonPath("$.percentualICMS").value(DEFAULT_PERCENTUAL_ICMS.doubleValue()))
            .andExpect(jsonPath("$.ufEmitente").value(DEFAULT_UF_EMITENTE))
            .andExpect(jsonPath("$.ufDestinatario").value(DEFAULT_UF_DESTINATARIO))
            .andExpect(jsonPath("$.valorItem").value(DEFAULT_VALOR_ITEM.doubleValue()))
            .andExpect(jsonPath("$.valorIPI").value(DEFAULT_VALOR_IPI.doubleValue()))
            .andExpect(jsonPath("$.valorFrete").value(DEFAULT_VALOR_FRETE.doubleValue()))
            .andExpect(jsonPath("$.valorSeguro").value(DEFAULT_VALOR_SEGURO.doubleValue()))
            .andExpect(jsonPath("$.valorOutros").value(DEFAULT_VALOR_OUTROS.doubleValue()))
            .andExpect(jsonPath("$.cfop").value(DEFAULT_CFOP))
            .andExpect(jsonPath("$.cnpjEmitente").value(DEFAULT_CNPJ_EMITENTE))
            .andExpect(jsonPath("$.cnpjDestinatario").value(DEFAULT_CNPJ_DESTINATARIO))
            .andExpect(jsonPath("$.categoria").value(DEFAULT_CATEGORIA))
            .andExpect(jsonPath("$.cst").value(DEFAULT_CST))
            .andExpect(jsonPath("$.normaExecucao").value(DEFAULT_NORMA_EXECUCAO));
    }

    @Test
    @Transactional
    void getNonExistingAlimentoAcordanteEntrada() throws Exception {
        // Get the alimentoAcordanteEntrada
        restAlimentoAcordanteEntradaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAlimentoAcordanteEntrada() throws Exception {
        // Initialize the database
        alimentoAcordanteEntradaRepository.saveAndFlush(alimentoAcordanteEntrada);

        int databaseSizeBeforeUpdate = alimentoAcordanteEntradaRepository.findAll().size();

        // Update the alimentoAcordanteEntrada
        AlimentoAcordanteEntrada updatedAlimentoAcordanteEntrada = alimentoAcordanteEntradaRepository
            .findById(alimentoAcordanteEntrada.getId())
            .get();
        // Disconnect from session so that the updates on updatedAlimentoAcordanteEntrada are not directly saved in db
        em.detach(updatedAlimentoAcordanteEntrada);
        updatedAlimentoAcordanteEntrada
            .idNFe(UPDATED_ID_N_FE)
            .crt(UPDATED_CRT)
            .percentualICMS(UPDATED_PERCENTUAL_ICMS)
            .ufEmitente(UPDATED_UF_EMITENTE)
            .ufDestinatario(UPDATED_UF_DESTINATARIO)
            .valorItem(UPDATED_VALOR_ITEM)
            .valorIPI(UPDATED_VALOR_IPI)
            .valorFrete(UPDATED_VALOR_FRETE)
            .valorSeguro(UPDATED_VALOR_SEGURO)
            .valorOutros(UPDATED_VALOR_OUTROS)
            .cfop(UPDATED_CFOP)
            .cnpjEmitente(UPDATED_CNPJ_EMITENTE)
            .cnpjDestinatario(UPDATED_CNPJ_DESTINATARIO)
            .categoria(UPDATED_CATEGORIA)
            .cst(UPDATED_CST)
            .normaExecucao(UPDATED_NORMA_EXECUCAO);
        AlimentoAcordanteEntradaDTO alimentoAcordanteEntradaDTO = alimentoAcordanteEntradaMapper.toDto(updatedAlimentoAcordanteEntrada);

        restAlimentoAcordanteEntradaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, alimentoAcordanteEntradaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(alimentoAcordanteEntradaDTO))
            )
            .andExpect(status().isOk());

        // Validate the AlimentoAcordanteEntrada in the database
        List<AlimentoAcordanteEntrada> alimentoAcordanteEntradaList = alimentoAcordanteEntradaRepository.findAll();
        assertThat(alimentoAcordanteEntradaList).hasSize(databaseSizeBeforeUpdate);
        AlimentoAcordanteEntrada testAlimentoAcordanteEntrada = alimentoAcordanteEntradaList.get(alimentoAcordanteEntradaList.size() - 1);
        assertThat(testAlimentoAcordanteEntrada.getIdNFe()).isEqualTo(UPDATED_ID_N_FE);
        assertThat(testAlimentoAcordanteEntrada.getCrt()).isEqualTo(UPDATED_CRT);
        assertThat(testAlimentoAcordanteEntrada.getPercentualICMS()).isEqualTo(UPDATED_PERCENTUAL_ICMS);
        assertThat(testAlimentoAcordanteEntrada.getUfEmitente()).isEqualTo(UPDATED_UF_EMITENTE);
        assertThat(testAlimentoAcordanteEntrada.getUfDestinatario()).isEqualTo(UPDATED_UF_DESTINATARIO);
        assertThat(testAlimentoAcordanteEntrada.getValorItem()).isEqualTo(UPDATED_VALOR_ITEM);
        assertThat(testAlimentoAcordanteEntrada.getValorIPI()).isEqualTo(UPDATED_VALOR_IPI);
        assertThat(testAlimentoAcordanteEntrada.getValorFrete()).isEqualTo(UPDATED_VALOR_FRETE);
        assertThat(testAlimentoAcordanteEntrada.getValorSeguro()).isEqualTo(UPDATED_VALOR_SEGURO);
        assertThat(testAlimentoAcordanteEntrada.getValorOutros()).isEqualTo(UPDATED_VALOR_OUTROS);
        assertThat(testAlimentoAcordanteEntrada.getCfop()).isEqualTo(UPDATED_CFOP);
        assertThat(testAlimentoAcordanteEntrada.getCnpjEmitente()).isEqualTo(UPDATED_CNPJ_EMITENTE);
        assertThat(testAlimentoAcordanteEntrada.getCnpjDestinatario()).isEqualTo(UPDATED_CNPJ_DESTINATARIO);
        assertThat(testAlimentoAcordanteEntrada.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testAlimentoAcordanteEntrada.getCst()).isEqualTo(UPDATED_CST);
        assertThat(testAlimentoAcordanteEntrada.getNormaExecucao()).isEqualTo(UPDATED_NORMA_EXECUCAO);
    }

    @Test
    @Transactional
    void putNonExistingAlimentoAcordanteEntrada() throws Exception {
        int databaseSizeBeforeUpdate = alimentoAcordanteEntradaRepository.findAll().size();
        alimentoAcordanteEntrada.setId(count.incrementAndGet());

        // Create the AlimentoAcordanteEntrada
        AlimentoAcordanteEntradaDTO alimentoAcordanteEntradaDTO = alimentoAcordanteEntradaMapper.toDto(alimentoAcordanteEntrada);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAlimentoAcordanteEntradaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, alimentoAcordanteEntradaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(alimentoAcordanteEntradaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AlimentoAcordanteEntrada in the database
        List<AlimentoAcordanteEntrada> alimentoAcordanteEntradaList = alimentoAcordanteEntradaRepository.findAll();
        assertThat(alimentoAcordanteEntradaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAlimentoAcordanteEntrada() throws Exception {
        int databaseSizeBeforeUpdate = alimentoAcordanteEntradaRepository.findAll().size();
        alimentoAcordanteEntrada.setId(count.incrementAndGet());

        // Create the AlimentoAcordanteEntrada
        AlimentoAcordanteEntradaDTO alimentoAcordanteEntradaDTO = alimentoAcordanteEntradaMapper.toDto(alimentoAcordanteEntrada);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAlimentoAcordanteEntradaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(alimentoAcordanteEntradaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AlimentoAcordanteEntrada in the database
        List<AlimentoAcordanteEntrada> alimentoAcordanteEntradaList = alimentoAcordanteEntradaRepository.findAll();
        assertThat(alimentoAcordanteEntradaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAlimentoAcordanteEntrada() throws Exception {
        int databaseSizeBeforeUpdate = alimentoAcordanteEntradaRepository.findAll().size();
        alimentoAcordanteEntrada.setId(count.incrementAndGet());

        // Create the AlimentoAcordanteEntrada
        AlimentoAcordanteEntradaDTO alimentoAcordanteEntradaDTO = alimentoAcordanteEntradaMapper.toDto(alimentoAcordanteEntrada);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAlimentoAcordanteEntradaMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(alimentoAcordanteEntradaDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AlimentoAcordanteEntrada in the database
        List<AlimentoAcordanteEntrada> alimentoAcordanteEntradaList = alimentoAcordanteEntradaRepository.findAll();
        assertThat(alimentoAcordanteEntradaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAlimentoAcordanteEntradaWithPatch() throws Exception {
        // Initialize the database
        alimentoAcordanteEntradaRepository.saveAndFlush(alimentoAcordanteEntrada);

        int databaseSizeBeforeUpdate = alimentoAcordanteEntradaRepository.findAll().size();

        // Update the alimentoAcordanteEntrada using partial update
        AlimentoAcordanteEntrada partialUpdatedAlimentoAcordanteEntrada = new AlimentoAcordanteEntrada();
        partialUpdatedAlimentoAcordanteEntrada.setId(alimentoAcordanteEntrada.getId());

        partialUpdatedAlimentoAcordanteEntrada
            .crt(UPDATED_CRT)
            .ufEmitente(UPDATED_UF_EMITENTE)
            .ufDestinatario(UPDATED_UF_DESTINATARIO)
            .valorItem(UPDATED_VALOR_ITEM)
            .valorIPI(UPDATED_VALOR_IPI)
            .cnpjEmitente(UPDATED_CNPJ_EMITENTE)
            .cnpjDestinatario(UPDATED_CNPJ_DESTINATARIO)
            .categoria(UPDATED_CATEGORIA)
            .normaExecucao(UPDATED_NORMA_EXECUCAO);

        restAlimentoAcordanteEntradaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAlimentoAcordanteEntrada.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAlimentoAcordanteEntrada))
            )
            .andExpect(status().isOk());

        // Validate the AlimentoAcordanteEntrada in the database
        List<AlimentoAcordanteEntrada> alimentoAcordanteEntradaList = alimentoAcordanteEntradaRepository.findAll();
        assertThat(alimentoAcordanteEntradaList).hasSize(databaseSizeBeforeUpdate);
        AlimentoAcordanteEntrada testAlimentoAcordanteEntrada = alimentoAcordanteEntradaList.get(alimentoAcordanteEntradaList.size() - 1);
        assertThat(testAlimentoAcordanteEntrada.getIdNFe()).isEqualTo(DEFAULT_ID_N_FE);
        assertThat(testAlimentoAcordanteEntrada.getCrt()).isEqualTo(UPDATED_CRT);
        assertThat(testAlimentoAcordanteEntrada.getPercentualICMS()).isEqualTo(DEFAULT_PERCENTUAL_ICMS);
        assertThat(testAlimentoAcordanteEntrada.getUfEmitente()).isEqualTo(UPDATED_UF_EMITENTE);
        assertThat(testAlimentoAcordanteEntrada.getUfDestinatario()).isEqualTo(UPDATED_UF_DESTINATARIO);
        assertThat(testAlimentoAcordanteEntrada.getValorItem()).isEqualTo(UPDATED_VALOR_ITEM);
        assertThat(testAlimentoAcordanteEntrada.getValorIPI()).isEqualTo(UPDATED_VALOR_IPI);
        assertThat(testAlimentoAcordanteEntrada.getValorFrete()).isEqualTo(DEFAULT_VALOR_FRETE);
        assertThat(testAlimentoAcordanteEntrada.getValorSeguro()).isEqualTo(DEFAULT_VALOR_SEGURO);
        assertThat(testAlimentoAcordanteEntrada.getValorOutros()).isEqualTo(DEFAULT_VALOR_OUTROS);
        assertThat(testAlimentoAcordanteEntrada.getCfop()).isEqualTo(DEFAULT_CFOP);
        assertThat(testAlimentoAcordanteEntrada.getCnpjEmitente()).isEqualTo(UPDATED_CNPJ_EMITENTE);
        assertThat(testAlimentoAcordanteEntrada.getCnpjDestinatario()).isEqualTo(UPDATED_CNPJ_DESTINATARIO);
        assertThat(testAlimentoAcordanteEntrada.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testAlimentoAcordanteEntrada.getCst()).isEqualTo(DEFAULT_CST);
        assertThat(testAlimentoAcordanteEntrada.getNormaExecucao()).isEqualTo(UPDATED_NORMA_EXECUCAO);
    }

    @Test
    @Transactional
    void fullUpdateAlimentoAcordanteEntradaWithPatch() throws Exception {
        // Initialize the database
        alimentoAcordanteEntradaRepository.saveAndFlush(alimentoAcordanteEntrada);

        int databaseSizeBeforeUpdate = alimentoAcordanteEntradaRepository.findAll().size();

        // Update the alimentoAcordanteEntrada using partial update
        AlimentoAcordanteEntrada partialUpdatedAlimentoAcordanteEntrada = new AlimentoAcordanteEntrada();
        partialUpdatedAlimentoAcordanteEntrada.setId(alimentoAcordanteEntrada.getId());

        partialUpdatedAlimentoAcordanteEntrada
            .idNFe(UPDATED_ID_N_FE)
            .crt(UPDATED_CRT)
            .percentualICMS(UPDATED_PERCENTUAL_ICMS)
            .ufEmitente(UPDATED_UF_EMITENTE)
            .ufDestinatario(UPDATED_UF_DESTINATARIO)
            .valorItem(UPDATED_VALOR_ITEM)
            .valorIPI(UPDATED_VALOR_IPI)
            .valorFrete(UPDATED_VALOR_FRETE)
            .valorSeguro(UPDATED_VALOR_SEGURO)
            .valorOutros(UPDATED_VALOR_OUTROS)
            .cfop(UPDATED_CFOP)
            .cnpjEmitente(UPDATED_CNPJ_EMITENTE)
            .cnpjDestinatario(UPDATED_CNPJ_DESTINATARIO)
            .categoria(UPDATED_CATEGORIA)
            .cst(UPDATED_CST)
            .normaExecucao(UPDATED_NORMA_EXECUCAO);

        restAlimentoAcordanteEntradaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAlimentoAcordanteEntrada.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAlimentoAcordanteEntrada))
            )
            .andExpect(status().isOk());

        // Validate the AlimentoAcordanteEntrada in the database
        List<AlimentoAcordanteEntrada> alimentoAcordanteEntradaList = alimentoAcordanteEntradaRepository.findAll();
        assertThat(alimentoAcordanteEntradaList).hasSize(databaseSizeBeforeUpdate);
        AlimentoAcordanteEntrada testAlimentoAcordanteEntrada = alimentoAcordanteEntradaList.get(alimentoAcordanteEntradaList.size() - 1);
        assertThat(testAlimentoAcordanteEntrada.getIdNFe()).isEqualTo(UPDATED_ID_N_FE);
        assertThat(testAlimentoAcordanteEntrada.getCrt()).isEqualTo(UPDATED_CRT);
        assertThat(testAlimentoAcordanteEntrada.getPercentualICMS()).isEqualTo(UPDATED_PERCENTUAL_ICMS);
        assertThat(testAlimentoAcordanteEntrada.getUfEmitente()).isEqualTo(UPDATED_UF_EMITENTE);
        assertThat(testAlimentoAcordanteEntrada.getUfDestinatario()).isEqualTo(UPDATED_UF_DESTINATARIO);
        assertThat(testAlimentoAcordanteEntrada.getValorItem()).isEqualTo(UPDATED_VALOR_ITEM);
        assertThat(testAlimentoAcordanteEntrada.getValorIPI()).isEqualTo(UPDATED_VALOR_IPI);
        assertThat(testAlimentoAcordanteEntrada.getValorFrete()).isEqualTo(UPDATED_VALOR_FRETE);
        assertThat(testAlimentoAcordanteEntrada.getValorSeguro()).isEqualTo(UPDATED_VALOR_SEGURO);
        assertThat(testAlimentoAcordanteEntrada.getValorOutros()).isEqualTo(UPDATED_VALOR_OUTROS);
        assertThat(testAlimentoAcordanteEntrada.getCfop()).isEqualTo(UPDATED_CFOP);
        assertThat(testAlimentoAcordanteEntrada.getCnpjEmitente()).isEqualTo(UPDATED_CNPJ_EMITENTE);
        assertThat(testAlimentoAcordanteEntrada.getCnpjDestinatario()).isEqualTo(UPDATED_CNPJ_DESTINATARIO);
        assertThat(testAlimentoAcordanteEntrada.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testAlimentoAcordanteEntrada.getCst()).isEqualTo(UPDATED_CST);
        assertThat(testAlimentoAcordanteEntrada.getNormaExecucao()).isEqualTo(UPDATED_NORMA_EXECUCAO);
    }

    @Test
    @Transactional
    void patchNonExistingAlimentoAcordanteEntrada() throws Exception {
        int databaseSizeBeforeUpdate = alimentoAcordanteEntradaRepository.findAll().size();
        alimentoAcordanteEntrada.setId(count.incrementAndGet());

        // Create the AlimentoAcordanteEntrada
        AlimentoAcordanteEntradaDTO alimentoAcordanteEntradaDTO = alimentoAcordanteEntradaMapper.toDto(alimentoAcordanteEntrada);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAlimentoAcordanteEntradaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, alimentoAcordanteEntradaDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(alimentoAcordanteEntradaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AlimentoAcordanteEntrada in the database
        List<AlimentoAcordanteEntrada> alimentoAcordanteEntradaList = alimentoAcordanteEntradaRepository.findAll();
        assertThat(alimentoAcordanteEntradaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAlimentoAcordanteEntrada() throws Exception {
        int databaseSizeBeforeUpdate = alimentoAcordanteEntradaRepository.findAll().size();
        alimentoAcordanteEntrada.setId(count.incrementAndGet());

        // Create the AlimentoAcordanteEntrada
        AlimentoAcordanteEntradaDTO alimentoAcordanteEntradaDTO = alimentoAcordanteEntradaMapper.toDto(alimentoAcordanteEntrada);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAlimentoAcordanteEntradaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(alimentoAcordanteEntradaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AlimentoAcordanteEntrada in the database
        List<AlimentoAcordanteEntrada> alimentoAcordanteEntradaList = alimentoAcordanteEntradaRepository.findAll();
        assertThat(alimentoAcordanteEntradaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAlimentoAcordanteEntrada() throws Exception {
        int databaseSizeBeforeUpdate = alimentoAcordanteEntradaRepository.findAll().size();
        alimentoAcordanteEntrada.setId(count.incrementAndGet());

        // Create the AlimentoAcordanteEntrada
        AlimentoAcordanteEntradaDTO alimentoAcordanteEntradaDTO = alimentoAcordanteEntradaMapper.toDto(alimentoAcordanteEntrada);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAlimentoAcordanteEntradaMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(alimentoAcordanteEntradaDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AlimentoAcordanteEntrada in the database
        List<AlimentoAcordanteEntrada> alimentoAcordanteEntradaList = alimentoAcordanteEntradaRepository.findAll();
        assertThat(alimentoAcordanteEntradaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAlimentoAcordanteEntrada() throws Exception {
        // Initialize the database
        alimentoAcordanteEntradaRepository.saveAndFlush(alimentoAcordanteEntrada);

        int databaseSizeBeforeDelete = alimentoAcordanteEntradaRepository.findAll().size();

        // Delete the alimentoAcordanteEntrada
        restAlimentoAcordanteEntradaMockMvc
            .perform(delete(ENTITY_API_URL_ID, alimentoAcordanteEntrada.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AlimentoAcordanteEntrada> alimentoAcordanteEntradaList = alimentoAcordanteEntradaRepository.findAll();
        assertThat(alimentoAcordanteEntradaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
