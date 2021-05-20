package com.auditcom.documentparser.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * NotaFiscalResource controller
 */
@RestController
@RequestMapping("/api/nota-fiscal")
public class NotaFiscalResource {

    private final Logger log = LoggerFactory.getLogger(NotaFiscalResource.class);

    /**
     * POST enviarNotas
     */
    @PostMapping("/enviar-notas")
    public String enviarNotas() {
        return "enviarNotas";
    }

    /**
     * GET obterNotas
     */
    @GetMapping("/obter-notas")
    public String obterNotas() {
        return "obterNotas";
    }
}
