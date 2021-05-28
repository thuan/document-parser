package com.auditcom.documentparser.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ProdutoEntrada.
 */
@Entity
@Table(name = "produto_entrada")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProdutoEntrada implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "percentual_icms")
    private Double percentualICMS;

    @Column(name = "cfop")
    private Integer cfop;

    @Column(name = "cst")
    private String cst;

    @OneToMany(mappedBy = "produtoEntrada")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "produtoEntrada" }, allowSetters = true)
    private Set<NotaFiscalEntrada> notaFiscalEntradas = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProdutoEntrada id(Long id) {
        this.id = id;
        return this;
    }

    public Double getPercentualICMS() {
        return this.percentualICMS;
    }

    public ProdutoEntrada percentualICMS(Double percentualICMS) {
        this.percentualICMS = percentualICMS;
        return this;
    }

    public void setPercentualICMS(Double percentualICMS) {
        this.percentualICMS = percentualICMS;
    }

    public Integer getCfop() {
        return this.cfop;
    }

    public ProdutoEntrada cfop(Integer cfop) {
        this.cfop = cfop;
        return this;
    }

    public void setCfop(Integer cfop) {
        this.cfop = cfop;
    }

    public String getCst() {
        return this.cst;
    }

    public ProdutoEntrada cst(String cst) {
        this.cst = cst;
        return this;
    }

    public void setCst(String cst) {
        this.cst = cst;
    }

    public Set<NotaFiscalEntrada> getNotaFiscalEntradas() {
        return this.notaFiscalEntradas;
    }

    public ProdutoEntrada notaFiscalEntradas(Set<NotaFiscalEntrada> notaFiscalEntradas) {
        this.setNotaFiscalEntradas(notaFiscalEntradas);
        return this;
    }

    public ProdutoEntrada addNotaFiscalEntrada(NotaFiscalEntrada notaFiscalEntrada) {
        this.notaFiscalEntradas.add(notaFiscalEntrada);
        notaFiscalEntrada.setProdutoEntrada(this);
        return this;
    }

    public ProdutoEntrada removeNotaFiscalEntrada(NotaFiscalEntrada notaFiscalEntrada) {
        this.notaFiscalEntradas.remove(notaFiscalEntrada);
        notaFiscalEntrada.setProdutoEntrada(null);
        return this;
    }

    public void setNotaFiscalEntradas(Set<NotaFiscalEntrada> notaFiscalEntradas) {
        if (this.notaFiscalEntradas != null) {
            this.notaFiscalEntradas.forEach(i -> i.setProdutoEntrada(null));
        }
        if (notaFiscalEntradas != null) {
            notaFiscalEntradas.forEach(i -> i.setProdutoEntrada(this));
        }
        this.notaFiscalEntradas = notaFiscalEntradas;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProdutoEntrada)) {
            return false;
        }
        return id != null && id.equals(((ProdutoEntrada) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProdutoEntrada{" +
            "id=" + getId() +
            ", percentualICMS=" + getPercentualICMS() +
            ", cfop=" + getCfop() +
            ", cst='" + getCst() + "'" +
            "}";
    }
}
