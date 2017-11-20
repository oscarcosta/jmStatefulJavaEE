package com.jm.statefulapp.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 *
 * @author Oscar Costa <oscarcosta@gmail.com>
 */
@Entity
public class Item implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    protected String descricao;
    
    protected Long quantidade;
    
    protected BigDecimal valorUnitario;
    
    @ManyToOne
    protected NotaFiscal notaFiscal;
    
    protected Item() {
        quantidade = 0L;
        valorUnitario = BigDecimal.ZERO;
    }
    
    protected Item(String descricao, BigDecimal valorUnitario, Long quantidade){
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
    }
    
    public BigDecimal getValorTotal() {
        return quantidade > 0 
               ? valorUnitario.multiply(BigDecimal.valueOf(quantidade)) 
               : BigDecimal.ZERO;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getId() {
        return id;
    }

    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }
    
    public Long getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
    
    
    
}
