package com.auditcom.documentparser.service.impl;

import com.auditcom.documentparser.domain.NotaFiscalEntrada;
import com.auditcom.documentparser.repository.NotaFiscalEntradaRepository;
import com.auditcom.documentparser.service.NotaFiscalEntradaService;
import com.auditcom.documentparser.service.dto.NotaFiscalEntradaDTO;
import com.auditcom.documentparser.service.mapper.NotaFiscalEntradaMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NotaFiscalEntrada}.
 */
@Service
@Transactional
public class NotaFiscalEntradaServiceImpl implements NotaFiscalEntradaService {

    private final Logger log = LoggerFactory.getLogger(NotaFiscalEntradaServiceImpl.class);

    private final NotaFiscalEntradaRepository notaFiscalEntradaRepository;

    private final NotaFiscalEntradaMapper notaFiscalEntradaMapper;

    public NotaFiscalEntradaServiceImpl(
        NotaFiscalEntradaRepository notaFiscalEntradaRepository,
        NotaFiscalEntradaMapper notaFiscalEntradaMapper
    ) {
        this.notaFiscalEntradaRepository = notaFiscalEntradaRepository;
        this.notaFiscalEntradaMapper = notaFiscalEntradaMapper;
    }

    @Override
    public NotaFiscalEntradaDTO save(NotaFiscalEntradaDTO notaFiscalEntradaDTO) {
        log.debug("Request to save NotaFiscalEntrada : {}", notaFiscalEntradaDTO);
        NotaFiscalEntrada notaFiscalEntrada = notaFiscalEntradaMapper.toEntity(notaFiscalEntradaDTO);
        notaFiscalEntrada = notaFiscalEntradaRepository.save(notaFiscalEntrada);
        return notaFiscalEntradaMapper.toDto(notaFiscalEntrada);
    }

    @Override
    public Optional<NotaFiscalEntradaDTO> partialUpdate(NotaFiscalEntradaDTO notaFiscalEntradaDTO) {
        log.debug("Request to partially update NotaFiscalEntrada : {}", notaFiscalEntradaDTO);

        return notaFiscalEntradaRepository
            .findById(notaFiscalEntradaDTO.getId())
            .map(
                existingNotaFiscalEntrada -> {
                    notaFiscalEntradaMapper.partialUpdate(existingNotaFiscalEntrada, notaFiscalEntradaDTO);
                    return existingNotaFiscalEntrada;
                }
            )
            .map(notaFiscalEntradaRepository::save)
            .map(notaFiscalEntradaMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotaFiscalEntradaDTO> findAll() {
        log.debug("Request to get all NotaFiscalEntradas");
        return notaFiscalEntradaRepository
            .findAll()
            .stream()
            .map(notaFiscalEntradaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NotaFiscalEntradaDTO> findOne(Long id) {
        log.debug("Request to get NotaFiscalEntrada : {}", id);
        return notaFiscalEntradaRepository.findById(id).map(notaFiscalEntradaMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete NotaFiscalEntrada : {}", id);
        notaFiscalEntradaRepository.deleteById(id);
    }
}
