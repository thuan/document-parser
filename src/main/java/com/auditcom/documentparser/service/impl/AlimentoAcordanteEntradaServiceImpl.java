package com.auditcom.documentparser.service.impl;

import com.auditcom.documentparser.domain.AlimentoAcordanteEntrada;
import com.auditcom.documentparser.repository.AlimentoAcordanteEntradaRepository;
import com.auditcom.documentparser.service.AlimentoAcordanteEntradaService;
import com.auditcom.documentparser.service.dto.AlimentoAcordanteEntradaDTO;
import com.auditcom.documentparser.service.mapper.AlimentoAcordanteEntradaMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AlimentoAcordanteEntrada}.
 */
@Service
@Transactional
public class AlimentoAcordanteEntradaServiceImpl implements AlimentoAcordanteEntradaService {

    private final Logger log = LoggerFactory.getLogger(AlimentoAcordanteEntradaServiceImpl.class);

    private final AlimentoAcordanteEntradaRepository alimentoAcordanteEntradaRepository;

    private final AlimentoAcordanteEntradaMapper alimentoAcordanteEntradaMapper;

    public AlimentoAcordanteEntradaServiceImpl(
        AlimentoAcordanteEntradaRepository alimentoAcordanteEntradaRepository,
        AlimentoAcordanteEntradaMapper alimentoAcordanteEntradaMapper
    ) {
        this.alimentoAcordanteEntradaRepository = alimentoAcordanteEntradaRepository;
        this.alimentoAcordanteEntradaMapper = alimentoAcordanteEntradaMapper;
    }

    @Override
    public AlimentoAcordanteEntradaDTO save(AlimentoAcordanteEntradaDTO alimentoAcordanteEntradaDTO) {
        log.debug("Request to save AlimentoAcordanteEntrada : {}", alimentoAcordanteEntradaDTO);
        AlimentoAcordanteEntrada alimentoAcordanteEntrada = alimentoAcordanteEntradaMapper.toEntity(alimentoAcordanteEntradaDTO);
        alimentoAcordanteEntrada = alimentoAcordanteEntradaRepository.save(alimentoAcordanteEntrada);
        return alimentoAcordanteEntradaMapper.toDto(alimentoAcordanteEntrada);
    }

    @Override
    public Optional<AlimentoAcordanteEntradaDTO> partialUpdate(AlimentoAcordanteEntradaDTO alimentoAcordanteEntradaDTO) {
        log.debug("Request to partially update AlimentoAcordanteEntrada : {}", alimentoAcordanteEntradaDTO);

        return alimentoAcordanteEntradaRepository
            .findById(alimentoAcordanteEntradaDTO.getId())
            .map(
                existingAlimentoAcordanteEntrada -> {
                    alimentoAcordanteEntradaMapper.partialUpdate(existingAlimentoAcordanteEntrada, alimentoAcordanteEntradaDTO);
                    return existingAlimentoAcordanteEntrada;
                }
            )
            .map(alimentoAcordanteEntradaRepository::save)
            .map(alimentoAcordanteEntradaMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlimentoAcordanteEntradaDTO> findAll() {
        log.debug("Request to get all AlimentoAcordanteEntradas");
        return alimentoAcordanteEntradaRepository
            .findAll()
            .stream()
            .map(alimentoAcordanteEntradaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AlimentoAcordanteEntradaDTO> findOne(Long id) {
        log.debug("Request to get AlimentoAcordanteEntrada : {}", id);
        return alimentoAcordanteEntradaRepository.findById(id).map(alimentoAcordanteEntradaMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AlimentoAcordanteEntrada : {}", id);
        alimentoAcordanteEntradaRepository.deleteById(id);
    }
}
