package com.auditcom.documentparser.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A NotaFiscalEntrada.
 */
@Entity
@Table(name = "nota_fiscal_entrada")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NotaFiscalEntrada implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_nfe", nullable = false)
    private String idNfe;

    @Column(name = "crt")
    private Integer crt;

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

    @Column(name = "cnpj_emitente")
    private String cnpjEmitente;

    @Column(name = "cnpj_destinatario")
    private String cnpjDestinatario;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "norma_execucao")
    private String normaExecucao;

    @ManyToOne
    @JsonIgnoreProperties(value = { "notaFiscalEntradas" }, allowSetters = true)
    private ProdutoEntrada produtoEntrada;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NotaFiscalEntrada id(Long id) {
        this.id = id;
        return this;
    }

    public String getIdNfe() {
        return this.idNfe;
    }

    public NotaFiscalEntrada idNfe(String idNfe) {
        this.idNfe = idNfe;
        return this;
    }

    public void setIdNfe(String idNfe) {
        this.idNfe = idNfe;
    }

    public Integer getCrt() {
        return this.crt;
    }

    public NotaFiscalEntrada crt(Integer crt) {
        this.crt = crt;
        return this;
    }

    public void setCrt(Integer crt) {
        this.crt = crt;
    }

    public String getUfEmitente() {
        return this.ufEmitente;
    }

    public NotaFiscalEntrada ufEmitente(String ufEmitente) {
        this.ufEmitente = ufEmitente;
        return this;
    }

    public void setUfEmitente(String ufEmitente) {
        this.ufEmitente = ufEmitente;
    }

    public String getUfDestinatario() {
        return this.ufDestinatario;
    }

    public NotaFiscalEntrada ufDestinatario(String ufDestinatario) {
        this.ufDestinatario = ufDestinatario;
        return this;
    }

    public void setUfDestinatario(String ufDestinatario) {
        this.ufDestinatario = ufDestinatario;
    }

    public Double getValorItem() {
        return this.valorItem;
    }

    public NotaFiscalEntrada valorItem(Double valorItem) {
        this.valorItem = valorItem;
        return this;
    }

    public void setValorItem(Double valorItem) {
        this.valorItem = valorItem;
    }

    public Double getValorIPI() {
        return this.valorIPI;
    }

    public NotaFiscalEntrada valorIPI(Double valorIPI) {
        this.valorIPI = valorIPI;
        return this;
    }

    public void setValorIPI(Double valorIPI) {
        this.valorIPI = valorIPI;
    }

    public Double getValorFrete() {
        return this.valorFrete;
    }

    public NotaFiscalEntrada valorFrete(Double valorFrete) {
        this.valorFrete = valorFrete;
        return this;
    }

    public void setValorFrete(Double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public Double getValorSeguro() {
        return this.valorSeguro;
    }

    public NotaFiscalEntrada valorSeguro(Double valorSeguro) {
        this.valorSeguro = valorSeguro;
        return this;
    }

    public void setValorSeguro(Double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    public Double getValorOutros() {
        return this.valorOutros;
    }

    public NotaFiscalEntrada valorOutros(Double valorOutros) {
        this.valorOutros = valorOutros;
        return this;
    }

    public void setValorOutros(Double valorOutros) {
        this.valorOutros = valorOutros;
    }

    public String getCnpjEmitente() {
        return this.cnpjEmitente;
    }

    public NotaFiscalEntrada cnpjEmitente(String cnpjEmitente) {
        this.cnpjEmitente = cnpjEmitente;
        return this;
    }

    public void setCnpjEmitente(String cnpjEmitente) {
        this.cnpjEmitente = cnpjEmitente;
    }

    public String getCnpjDestinatario() {
        return this.cnpjDestinatario;
    }

    public NotaFiscalEntrada cnpjDestinatario(String cnpjDestinatario) {
        this.cnpjDestinatario = cnpjDestinatario;
        return this;
    }

    public void setCnpjDestinatario(String cnpjDestinatario) {
        this.cnpjDestinatario = cnpjDestinatario;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public NotaFiscalEntrada categoria(String categoria) {
        this.categoria = categoria;
        return this;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNormaExecucao() {
        return this.normaExecucao;
    }

    public NotaFiscalEntrada normaExecucao(String normaExecucao) {
        this.normaExecucao = normaExecucao;
        return this;
    }

    public void setNormaExecucao(String normaExecucao) {
        this.normaExecucao = normaExecucao;
    }

    public ProdutoEntrada getProdutoEntrada() {
        return this.produtoEntrada;
    }

    public NotaFiscalEntrada produtoEntrada(ProdutoEntrada produtoEntrada) {
        this.setProdutoEntrada(produtoEntrada);
        return this;
    }

    public void setProdutoEntrada(ProdutoEntrada produtoEntrada) {
        this.produtoEntrada = produtoEntrada;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NotaFiscalEntrada)) {
            return false;
        }
        return id != null && id.equals(((NotaFiscalEntrada) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NotaFiscalEntrada{" +
            "id=" + getId() +
            ", idNfe='" + getIdNfe() + "'" +
            ", crt=" + getCrt() +
            ", ufEmitente='" + getUfEmitente() + "'" +
            ", ufDestinatario='" + getUfDestinatario() + "'" +
            ", valorItem=" + getValorItem() +
            ", valorIPI=" + getValorIPI() +
            ", valorFrete=" + getValorFrete() +
            ", valorSeguro=" + getValorSeguro() +
            ", valorOutros=" + getValorOutros() +
            ", cnpjEmitente='" + getCnpjEmitente() + "'" +
            ", cnpjDestinatario='" + getCnpjDestinatario() + "'" +
            ", categoria='" + getCategoria() + "'" +
            ", normaExecucao='" + getNormaExecucao() + "'" +
            "}";
    }
}
