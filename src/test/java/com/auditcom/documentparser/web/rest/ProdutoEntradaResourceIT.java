package com.auditcom.documentparser.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.auditcom.documentparser.IntegrationTest;
import com.auditcom.documentparser.domain.ProdutoEntrada;
import com.auditcom.documentparser.repository.ProdutoEntradaRepository;
import com.auditcom.documentparser.service.dto.ProdutoEntradaDTO;
import com.auditcom.documentparser.service.mapper.ProdutoEntradaMapper;
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
 * Integration tests for the {@link ProdutoEntradaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProdutoEntradaResourceIT {

    private static final Double DEFAULT_PERCENTUAL_ICMS = 1D;
    private static final Double UPDATED_PERCENTUAL_ICMS = 2D;

    private static final Integer DEFAULT_CFOP = 1;
    private static final Integer UPDATED_CFOP = 2;

    private static final String DEFAULT_CST = "AAAAAAAAAA";
    private static final String UPDATED_CST = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/produto-entradas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ProdutoEntradaRepository produtoEntradaRepository;

    @Autowired
    private ProdutoEntradaMapper produtoEntradaMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProdutoEntradaMockMvc;

    private ProdutoEntrada produtoEntrada;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProdutoEntrada createEntity(EntityManager em) {
        ProdutoEntrada produtoEntrada = new ProdutoEntrada().percentualICMS(DEFAULT_PERCENTUAL_ICMS).cfop(DEFAULT_CFOP).cst(DEFAULT_CST);
        return produtoEntrada;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProdutoEntrada createUpdatedEntity(EntityManager em) {
        ProdutoEntrada produtoEntrada = new ProdutoEntrada().percentualICMS(UPDATED_PERCENTUAL_ICMS).cfop(UPDATED_CFOP).cst(UPDATED_CST);
        return produtoEntrada;
    }

    @BeforeEach
    public void initTest() {
        produtoEntrada = createEntity(em);
    }

    @Test
    @Transactional
    void createProdutoEntrada() throws Exception {
        int databaseSizeBeforeCreate = produtoEntradaRepository.findAll().size();
        // Create the ProdutoEntrada
        ProdutoEntradaDTO produtoEntradaDTO = produtoEntradaMapper.toDto(produtoEntrada);
        restProdutoEntradaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(produtoEntradaDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ProdutoEntrada in the database
        List<ProdutoEntrada> produtoEntradaList = produtoEntradaRepository.findAll();
        assertThat(produtoEntradaList).hasSize(databaseSizeBeforeCreate + 1);
        ProdutoEntrada testProdutoEntrada = produtoEntradaList.get(produtoEntradaList.size() - 1);
        assertThat(testProdutoEntrada.getPercentualICMS()).isEqualTo(DEFAULT_PERCENTUAL_ICMS);
        assertThat(testProdutoEntrada.getCfop()).isEqualTo(DEFAULT_CFOP);
        assertThat(testProdutoEntrada.getCst()).isEqualTo(DEFAULT_CST);
    }

    @Test
    @Transactional
    void createProdutoEntradaWithExistingId() throws Exception {
        // Create the ProdutoEntrada with an existing ID
        produtoEntrada.setId(1L);
        ProdutoEntradaDTO produtoEntradaDTO = produtoEntradaMapper.toDto(produtoEntrada);

        int databaseSizeBeforeCreate = produtoEntradaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProdutoEntradaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(produtoEntradaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProdutoEntrada in the database
        List<ProdutoEntrada> produtoEntradaList = produtoEntradaRepository.findAll();
        assertThat(produtoEntradaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProdutoEntradas() throws Exception {
        // Initialize the database
        produtoEntradaRepository.saveAndFlush(produtoEntrada);

        // Get all the produtoEntradaList
        restProdutoEntradaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(produtoEntrada.getId().intValue())))
            .andExpect(jsonPath("$.[*].percentualICMS").value(hasItem(DEFAULT_PERCENTUAL_ICMS.doubleValue())))
            .andExpect(jsonPath("$.[*].cfop").value(hasItem(DEFAULT_CFOP)))
            .andExpect(jsonPath("$.[*].cst").value(hasItem(DEFAULT_CST)));
    }

    @Test
    @Transactional
    void getProdutoEntrada() throws Exception {
        // Initialize the database
        produtoEntradaRepository.saveAndFlush(produtoEntrada);

        // Get the produtoEntrada
        restProdutoEntradaMockMvc
            .perform(get(ENTITY_API_URL_ID, produtoEntrada.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(produtoEntrada.getId().intValue()))
            .andExpect(jsonPath("$.percentualICMS").value(DEFAULT_PERCENTUAL_ICMS.doubleValue()))
            .andExpect(jsonPath("$.cfop").value(DEFAULT_CFOP))
            .andExpect(jsonPath("$.cst").value(DEFAULT_CST));
    }

    @Test
    @Transactional
    void getNonExistingProdutoEntrada() throws Exception {
        // Get the produtoEntrada
        restProdutoEntradaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewProdutoEntrada() throws Exception {
        // Initialize the database
        produtoEntradaRepository.saveAndFlush(produtoEntrada);

        int databaseSizeBeforeUpdate = produtoEntradaRepository.findAll().size();

        // Update the produtoEntrada
        ProdutoEntrada updatedProdutoEntrada = produtoEntradaRepository.findById(produtoEntrada.getId()).get();
        // Disconnect from session so that the updates on updatedProdutoEntrada are not directly saved in db
        em.detach(updatedProdutoEntrada);
        updatedProdutoEntrada.percentualICMS(UPDATED_PERCENTUAL_ICMS).cfop(UPDATED_CFOP).cst(UPDATED_CST);
        ProdutoEntradaDTO produtoEntradaDTO = produtoEntradaMapper.toDto(updatedProdutoEntrada);

        restProdutoEntradaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, produtoEntradaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(produtoEntradaDTO))
            )
            .andExpect(status().isOk());

        // Validate the ProdutoEntrada in the database
        List<ProdutoEntrada> produtoEntradaList = produtoEntradaRepository.findAll();
        assertThat(produtoEntradaList).hasSize(databaseSizeBeforeUpdate);
        ProdutoEntrada testProdutoEntrada = produtoEntradaList.get(produtoEntradaList.size() - 1);
        assertThat(testProdutoEntrada.getPercentualICMS()).isEqualTo(UPDATED_PERCENTUAL_ICMS);
        assertThat(testProdutoEntrada.getCfop()).isEqualTo(UPDATED_CFOP);
        assertThat(testProdutoEntrada.getCst()).isEqualTo(UPDATED_CST);
    }

    @Test
    @Transactional
    void putNonExistingProdutoEntrada() throws Exception {
        int databaseSizeBeforeUpdate = produtoEntradaRepository.findAll().size();
        produtoEntrada.setId(count.incrementAndGet());

        // Create the ProdutoEntrada
        ProdutoEntradaDTO produtoEntradaDTO = produtoEntradaMapper.toDto(produtoEntrada);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProdutoEntradaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, produtoEntradaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(produtoEntradaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProdutoEntrada in the database
        List<ProdutoEntrada> produtoEntradaList = produtoEntradaRepository.findAll();
        assertThat(produtoEntradaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProdutoEntrada() throws Exception {
        int databaseSizeBeforeUpdate = produtoEntradaRepository.findAll().size();
        produtoEntrada.setId(count.incrementAndGet());

        // Create the ProdutoEntrada
        ProdutoEntradaDTO produtoEntradaDTO = produtoEntradaMapper.toDto(produtoEntrada);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProdutoEntradaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(produtoEntradaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProdutoEntrada in the database
        List<ProdutoEntrada> produtoEntradaList = produtoEntradaRepository.findAll();
        assertThat(produtoEntradaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProdutoEntrada() throws Exception {
        int databaseSizeBeforeUpdate = produtoEntradaRepository.findAll().size();
        produtoEntrada.setId(count.incrementAndGet());

        // Create the ProdutoEntrada
        ProdutoEntradaDTO produtoEntradaDTO = produtoEntradaMapper.toDto(produtoEntrada);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProdutoEntradaMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(produtoEntradaDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProdutoEntrada in the database
        List<ProdutoEntrada> produtoEntradaList = produtoEntradaRepository.findAll();
        assertThat(produtoEntradaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProdutoEntradaWithPatch() throws Exception {
        // Initialize the database
        produtoEntradaRepository.saveAndFlush(produtoEntrada);

        int databaseSizeBeforeUpdate = produtoEntradaRepository.findAll().size();

        // Update the produtoEntrada using partial update
        ProdutoEntrada partialUpdatedProdutoEntrada = new ProdutoEntrada();
        partialUpdatedProdutoEntrada.setId(produtoEntrada.getId());

        partialUpdatedProdutoEntrada.percentualICMS(UPDATED_PERCENTUAL_ICMS).cfop(UPDATED_CFOP);

        restProdutoEntradaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProdutoEntrada.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProdutoEntrada))
            )
            .andExpect(status().isOk());

        // Validate the ProdutoEntrada in the database
        List<ProdutoEntrada> produtoEntradaList = produtoEntradaRepository.findAll();
        assertThat(produtoEntradaList).hasSize(databaseSizeBeforeUpdate);
        ProdutoEntrada testProdutoEntrada = produtoEntradaList.get(produtoEntradaList.size() - 1);
        assertThat(testProdutoEntrada.getPercentualICMS()).isEqualTo(UPDATED_PERCENTUAL_ICMS);
        assertThat(testProdutoEntrada.getCfop()).isEqualTo(UPDATED_CFOP);
        assertThat(testProdutoEntrada.getCst()).isEqualTo(DEFAULT_CST);
    }

    @Test
    @Transactional
    void fullUpdateProdutoEntradaWithPatch() throws Exception {
        // Initialize the database
        produtoEntradaRepository.saveAndFlush(produtoEntrada);

        int databaseSizeBeforeUpdate = produtoEntradaRepository.findAll().size();

        // Update the produtoEntrada using partial update
        ProdutoEntrada partialUpdatedProdutoEntrada = new ProdutoEntrada();
        partialUpdatedProdutoEntrada.setId(produtoEntrada.getId());

        partialUpdatedProdutoEntrada.percentualICMS(UPDATED_PERCENTUAL_ICMS).cfop(UPDATED_CFOP).cst(UPDATED_CST);

        restProdutoEntradaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProdutoEntrada.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProdutoEntrada))
            )
            .andExpect(status().isOk());

        // Validate the ProdutoEntrada in the database
        List<ProdutoEntrada> produtoEntradaList = produtoEntradaRepository.findAll();
        assertThat(produtoEntradaList).hasSize(databaseSizeBeforeUpdate);
        ProdutoEntrada testProdutoEntrada = produtoEntradaList.get(produtoEntradaList.size() - 1);
        assertThat(testProdutoEntrada.getPercentualICMS()).isEqualTo(UPDATED_PERCENTUAL_ICMS);
        assertThat(testProdutoEntrada.getCfop()).isEqualTo(UPDATED_CFOP);
        assertThat(testProdutoEntrada.getCst()).isEqualTo(UPDATED_CST);
    }

    @Test
    @Transactional
    void patchNonExistingProdutoEntrada() throws Exception {
        int databaseSizeBeforeUpdate = produtoEntradaRepository.findAll().size();
        produtoEntrada.setId(count.incrementAndGet());

        // Create the ProdutoEntrada
        ProdutoEntradaDTO produtoEntradaDTO = produtoEntradaMapper.toDto(produtoEntrada);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProdutoEntradaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, produtoEntradaDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(produtoEntradaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProdutoEntrada in the database
        List<ProdutoEntrada> produtoEntradaList = produtoEntradaRepository.findAll();
        assertThat(produtoEntradaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProdutoEntrada() throws Exception {
        int databaseSizeBeforeUpdate = produtoEntradaRepository.findAll().size();
        produtoEntrada.setId(count.incrementAndGet());

        // Create the ProdutoEntrada
        ProdutoEntradaDTO produtoEntradaDTO = produtoEntradaMapper.toDto(produtoEntrada);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProdutoEntradaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(produtoEntradaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProdutoEntrada in the database
        List<ProdutoEntrada> produtoEntradaList = produtoEntradaRepository.findAll();
        assertThat(produtoEntradaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProdutoEntrada() throws Exception {
        int databaseSizeBeforeUpdate = produtoEntradaRepository.findAll().size();
        produtoEntrada.setId(count.incrementAndGet());

        // Create the ProdutoEntrada
        ProdutoEntradaDTO produtoEntradaDTO = produtoEntradaMapper.toDto(produtoEntrada);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProdutoEntradaMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(produtoEntradaDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProdutoEntrada in the database
        List<ProdutoEntrada> produtoEntradaList = produtoEntradaRepository.findAll();
        assertThat(produtoEntradaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProdutoEntrada() throws Exception {
        // Initialize the database
        produtoEntradaRepository.saveAndFlush(produtoEntrada);

        int databaseSizeBeforeDelete = produtoEntradaRepository.findAll().size();

        // Delete the produtoEntrada
        restProdutoEntradaMockMvc
            .perform(delete(ENTITY_API_URL_ID, produtoEntrada.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProdutoEntrada> produtoEntradaList = produtoEntradaRepository.findAll();
        assertThat(produtoEntradaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
