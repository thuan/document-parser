package com.auditcom.documentparser.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A AlimentoAcordanteEntrada.
 */
@Entity
@Table(name = "alimento_acordante_entrada")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlimentoAcordanteEntrada implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_n_fe", nullable = false)
    private String idNFe;

    @Column(name = "crt")
    private Integer crt;

    @Column(name = "percentual_icms")
    private Double percentualICMS;

    @Column(name = "uf_emitente")
    private String ufEmitente;

    @Column(name = "uf_destinatario")
    private String ufDestinatario;

    @Column(name = "valor_item")
    private Double valorItem;

    @Column(name = "valor_ipi")
    private Double valorIPI;

    @Column(name = "valor_frete")
    private Double valorFrete;

    @Column(name = "valor_seguro")
    private Double valorSeguro;

    @Column(name = "valor_outros")
    private Double valorOutros;

    @Column(name = "cfop")
    private Integer cfop;

    @Column(name = "cnpj_emitente")
    private String cnpjEmitente;

    @Column(name = "cnpj_destinatario")
    private String cnpjDestinatario;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "cst")
    private String cst;

    @Column(name = "norma_execucao")
    private String normaExecucao;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AlimentoAcordanteEntrada id(Long id) {
        this.id = id;
        return this;
    }

    public String getIdNFe() {
        return this.idNFe;
    }

    public AlimentoAcordanteEntrada idNFe(String idNFe) {
        this.idNFe = idNFe;
        return this;
    }

    public void setIdNFe(String idNFe) {
        this.idNFe = idNFe;
    }

    public Integer getCrt() {
        return this.crt;
    }

    public AlimentoAcordanteEntrada crt(Integer crt) {
        this.crt = crt;
        return this;
    }

    public void setCrt(Integer crt) {
        this.crt = crt;
    }

    public Double getPercentualICMS() {
        return this.percentualICMS;
    }

    public AlimentoAcordanteEntrada percentualICMS(Double percentualICMS) {
        this.percentualICMS = percentualICMS;
        return this;
    }

    public void setPercentualICMS(Double percentualICMS) {
        this.percentualICMS = percentualICMS;
    }

    public String getUfEmitente() {
        return this.ufEmitente;
    }

    public AlimentoAcordanteEntrada ufEmitente(String ufEmitente) {
        this.ufEmitente = ufEmitente;
        return this;
    }

    public void setUfEmitente(String ufEmitente) {
        this.ufEmitente = ufEmitente;
    }

    public String getUfDestinatario() {
        return this.ufDestinatario;
    }

    public AlimentoAcordanteEntrada ufDestinatario(String ufDestinatario) {
        this.ufDestinatario = ufDestinatario;
        return this;
    }

    public void setUfDestinatario(String ufDestinatario) {
        this.ufDestinatario = ufDestinatario;
    }

    public Double getValorItem() {
        return this.valorItem;
    }

    public AlimentoAcordanteEntrada valorItem(Double valorItem) {
        this.valorItem = valorItem;
        return this;
    }

    public void setValorItem(Double valorItem) {
        this.valorItem = valorItem;
    }

    public Double getValorIPI() {
        return this.valorIPI;
    }

    public AlimentoAcordanteEntrada valorIPI(Double valorIPI) {
        this.valorIPI = valorIPI;
        return this;
    }

    public void setValorIPI(Double valorIPI) {
        this.valorIPI = valorIPI;
    }

    public Double getValorFrete() {
        return this.valorFrete;
    }

    public AlimentoAcordanteEntrada valorFrete(Double valorFrete) {
        this.valorFrete = valorFrete;
        return this;
    }

    public void setValorFrete(Double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public Double getValorSeguro() {
        return this.valorSeguro;
    }

    public AlimentoAcordanteEntrada valorSeguro(Double valorSeguro) {
        this.valorSeguro = valorSeguro;
        return this;
    }

    public void setValorSeguro(Double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    public Double getValorOutros() {
        return this.valorOutros;
    }

    public AlimentoAcordanteEntrada valorOutros(Double valorOutros) {
        this.valorOutros = valorOutros;
        return this;
    }

    public void setValorOutros(Double valorOutros) {
        this.valorOutros = valorOutros;
    }

    public Integer getCfop() {
        return this.cfop;
    }

    public AlimentoAcordanteEntrada cfop(Integer cfop) {
        this.cfop = cfop;
        return this;
    }

    public void setCfop(Integer cfop) {
        this.cfop = cfop;
    }

    public String getCnpjEmitente() {
        return this.cnpjEmitente;
    }

    public AlimentoAcordanteEntrada cnpjEmitente(String cnpjEmitente) {
        this.cnpjEmitente = cnpjEmitente;
        return this;
    }

    public void setCnpjEmitente(String cnpjEmitente) {
        this.cnpjEmitente = cnpjEmitente;
    }

    public String getCnpjDestinatario() {
        return this.cnpjDestinatario;
    }

    public AlimentoAcordanteEntrada cnpjDestinatario(String cnpjDestinatario) {
        this.cnpjDestinatario = cnpjDestinatario;
        return this;
    }

    public void setCnpjDestinatario(String cnpjDestinatario) {
        this.cnpjDestinatario = cnpjDestinatario;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public AlimentoAcordanteEntrada categoria(String categoria) {
        this.categoria = categoria;
        return this;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCst() {
        return this.cst;
    }

    public AlimentoAcordanteEntrada cst(String cst) {
        this.cst = cst;
        return this;
    }

    public void setCst(String cst) {
        this.cst = cst;
    }

    public String getNormaExecucao() {
        return this.normaExecucao;
    }

    public AlimentoAcordanteEntrada normaExecucao(String normaExecucao) {
        this.normaExecucao = normaExecucao;
        return this;
    }

    public void setNormaExecucao(String normaExecucao) {
        this.normaExecucao = normaExecucao;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AlimentoAcordanteEntrada)) {
            return false;
        }
        return id != null && id.equals(((AlimentoAcordanteEntrada) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AlimentoAcordanteEntrada{" +
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
