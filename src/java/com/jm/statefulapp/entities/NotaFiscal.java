package com.jm.statefulapp.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Oscar Costa <oscarcosta@gmail.com>
 */
@Entity
public class NotaFiscal implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
    protected Prestador prestador;
    
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
    protected Tomador tomador;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected Date dataHoraEmissao; 
    
    protected BigDecimal valorTotal;
    
    @OneToMany(mappedBy = "notaFiscal", 
               cascade=CascadeType.ALL, 
               orphanRemoval=true)
    protected List<Item> itens;
    
    protected NotaFiscal() {
        itens = new ArrayList<Item>();
        valorTotal = BigDecimal.ZERO;
        dataHoraEmissao = new Date();
    }
    
    protected NotaFiscal(Prestador prestador) {
        this();
        this.prestador = prestador;
    }
    
    public static class Builder {
    
        private NotaFiscal notaFiscal;

        public Builder(Prestador prestador) {
            this.notaFiscal = new NotaFiscal(prestador);
        }
        
        public Builder withTomador(Tomador tomador) {
            this.notaFiscal.tomador = tomador;
            return this;
        }
        
        public Builder withItem(Item item) {
            this.notaFiscal.addItem(item);
            return this;
        }
        
        public Builder withItens(Collection<Item> itens) {
            this.notaFiscal.addItens(itens);
            return this;
        }
        
        public NotaFiscal build() {
            if (notaFiscal.prestador == null 
             || notaFiscal.tomador == null 
             || notaFiscal.itens.isEmpty()) {
                throw new IllegalStateException("Erro ao criar a Nota Fiscal.");
            }
            return this.notaFiscal;
        }
    }
    
    public static Item createItem() {
        return new Item();
    }
    
    public static Item createItem(String descricao, 
                                  double valorUnitario, 
                                  long quantidade) {
        return new Item(descricao, 
                        BigDecimal.valueOf(valorUnitario), 
                        quantidade);
    }
    
    protected void addItens(Collection<Item> itens) {
        for (Item item : itens) {
            addItem(item);
        }
    }
    
    public void addItem(Item item) {
        if (this.itens.add(item)) {
            item.setNotaFiscal(this);
            this.valorTotal = this.valorTotal.add(item.getValorTotal());
        }
    }

    public void removeItem(Item item) {
        if (this.itens.remove(item)) {
            this.valorTotal = this.valorTotal.subtract(item.getValorTotal());
        }
    }
    
    public int getQuantidadeItens() {
        return itens.size();
    }
    
    public Date getDataHoraEmissao() {
        return dataHoraEmissao;
    }

    public Long getId() {
        return id;
    }

    public List<Item> getItens() {
        return itens;
    }

    public Prestador getPrestador() {
        return prestador;
    }

    public Tomador getTomador() {
        return tomador;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }
    
    public static BigDecimal getValorTotal(List<Item> itens) {
        BigDecimal valor = BigDecimal.ZERO;
        for (Item item : itens) {
            valor = valor.add(item.getValorTotal());
        }
        return valor;
    }

    public void setDataHoraEmissao(Date dataHoraEmissao) {
        this.dataHoraEmissao = dataHoraEmissao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public void setPrestador(Prestador prestador) {
        this.prestador = prestador;
    }

    public void setTomador(Tomador tomador) {
        this.tomador = tomador;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
    
}
