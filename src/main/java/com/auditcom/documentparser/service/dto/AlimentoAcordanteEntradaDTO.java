package com.auditcom.documentparser.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.auditcom.documentparser.domain.AlimentoAcordanteEntrada} entity.
 */
public class AlimentoAcordanteEntradaDTO implements Serializable {

    private Long id;

    @NotNull
    private String idNFe;

    private Integer crt;

    private Double percentualICMS;

    private String ufEmitente;

    private String ufDestinatario;

    private Double valorItem;

    private Double valorIPI;

    private Double valorFrete;

    private Double valorSeguro;

    private Double valorOutros;

    private Integer cfop;

    private String cnpjEmitente;

    private String cnpjDestinatario;

    private String categoria;

    private String cst;

    private String normaExecucao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdNFe() {
        return idNFe;
    }

    public void setIdNFe(String idNFe) {
        this.idNFe = idNFe;
    }

    public Integer getCrt() {
        return crt;
    }

    public void setCrt(Integer crt) {
        this.crt = crt;
    }

    public Double getPercentualICMS() {
        return percentualICMS;
    }

    public void setPercentualICMS(Double percentualICMS) {
        this.percentualICMS = percentualICMS;
    }

    public String getUfEmitente() {
        return ufEmitente;
    }

    public void setUfEmitente(String ufEmitente) {
        this.ufEmitente = ufEmitente;
    }

    public String getUfDestinatario() {
        return ufDestinatario;
    }

    public void setUfDestinatario(String ufDestinatario) {
        this.ufDestinatario = ufDestinatario;
    }

    public Double getValorItem() {
        return valorItem;
    }

    public void setValorItem(Double valorItem) {
        this.valorItem = valorItem;
    }

    public Double getValorIPI() {
        return valorIPI;
    }

    public void setValorIPI(Double valorIPI) {
        this.valorIPI = valorIPI;
    }

    public Double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(Double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public Double getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(Double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    public Double getValorOutros() {
        return valorOutros;
    }

    public void setValorOutros(Double valorOutros) {
        this.valorOutros = valorOutros;
    }

    public Integer getCfop() {
        return cfop;
    }

    public void setCfop(Integer cfop) {
        this.cfop = cfop;
    }

    public String getCnpjEmitente() {
        return cnpjEmitente;
    }

    public void setCnpjEmitente(String cnpjEmitente) {
        this.cnpjEmitente = cnpjEmitente;
    }

    public String getCnpjDestinatario() {
        return cnpjDestinatario;
    }

    public void setCnpjDestinatario(String cnpjDestinatario) {
        this.cnpjDestinatario = cnpjDestinatario;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCst() {
        return cst;
    }

    public void setCst(String cst) {
        this.cst = cst;
    }

    public String getNormaExecucao() {
        return normaExecucao;
    }

    public void setNormaExecucao(String normaExecucao) {
        this.normaExecucao = normaExecucao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AlimentoAcordanteEntradaDTO)) {
            return false;
        }

        AlimentoAcordanteEntradaDTO alimentoAcordanteEntradaDTO = (AlimentoAcordanteEntradaDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, alimentoAcordanteEntradaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AlimentoAcordanteEntradaDTO{" +
            "id=" + getId() +
            ", idNFe='" + getIdNFe() + "'" +
            ", crt=" + getCrt() +
            ", percentualICMS=" + getPercentualICMS() +
            ", ufEmitente='" + getUfEmitente() + "'" +
            ", ufDestinatario='" + getUfDestinatario() + "'" +
            ", valorItem=" + getValorItem() +
            ", valorIPI=" + getValorIPI() +
            ", valorFrete=" + getValorFrete() +
            ", valorSeguro=" + getValorSeguro() +
            ", valorOutros=" + getValorOutros() +
            ", cfop=" + getCfop() +
            ", cnpjEmitente='" + getCnpjEmitente() + "'" +
            ", cnpjDestinatario='" + getCnpjDestinatario() + "'" +
            ", categoria='" + getCategoria() + "'" +
            ", cst='" + getCst() + "'" +
            ", normaExecucao='" + getNormaExecucao() + "'" +
            "}";
    }
}
