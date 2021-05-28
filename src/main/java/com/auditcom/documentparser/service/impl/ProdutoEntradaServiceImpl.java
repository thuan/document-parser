package com.auditcom.documentparser.service.impl;

import com.auditcom.documentparser.domain.ProdutoEntrada;
import com.auditcom.documentparser.repository.ProdutoEntradaRepository;
import com.auditcom.documentparser.service.ProdutoEntradaService;
import com.auditcom.documentparser.service.dto.ProdutoEntradaDTO;
import com.auditcom.documentparser.service.mapper.ProdutoEntradaMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ProdutoEntrada}.
 */
@Service
@Transactional
public class ProdutoEntradaServiceImpl implements ProdutoEntradaService {

    private final Logger log = LoggerFactory.getLogger(ProdutoEntradaServiceImpl.class);

    private final ProdutoEntradaRepository produtoEntradaRepository;

    private final ProdutoEntradaMapper produtoEntradaMapper;

    public ProdutoEntradaServiceImpl(ProdutoEntradaRepository produtoEntradaRepository, ProdutoEntradaMapper produtoEntradaMapper) {
        this.produtoEntradaRepository = produtoEntradaRepository;
        this.produtoEntradaMapper = produtoEntradaMapper;
    }

    @Override
    public ProdutoEntradaDTO save(ProdutoEntradaDTO produtoEntradaDTO) {
        log.debug("Request to save ProdutoEntrada : {}", produtoEntradaDTO);
        ProdutoEntrada produtoEntrada = produtoEntradaMapper.toEntity(produtoEntradaDTO);
        produtoEntrada = produtoEntradaRepository.save(produtoEntrada);
        return produtoEntradaMapper.toDto(produtoEntrada);
    }

    @Override
    public Optional<ProdutoEntradaDTO> partialUpdate(ProdutoEntradaDTO produtoEntradaDTO) {
        log.debug("Request to partially update ProdutoEntrada : {}", produtoEntradaDTO);

        return produtoEntradaRepository
            .findById(produtoEntradaDTO.getId())
            .map(
                existingProdutoEntrada -> {
                    produtoEntradaMapper.partialUpdate(existingProdutoEntrada, produtoEntradaDTO);
                    return existingProdutoEntrada;
                }
            )
            .map(produtoEntradaRepository::save)
            .map(produtoEntradaMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProdutoEntradaDTO> findAll() {
        log.debug("Request to get all ProdutoEntradas");
        return produtoEntradaRepository
            .findAll()
            .stream()
            .map(produtoEntradaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProdutoEntradaDTO> findOne(Long id) {
        log.debug("Request to get ProdutoEntrada : {}", id);
        return produtoEntradaRepository.findById(id).map(produtoEntradaMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProdutoEntrada : {}", id);
        produtoEntradaRepository.deleteById(id);
    }
}
