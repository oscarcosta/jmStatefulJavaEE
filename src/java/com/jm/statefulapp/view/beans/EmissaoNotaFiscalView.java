package com.jm.statefulapp.view.beans;

import com.jm.statefulapp.boundary.NotaFiscalGateway;
import com.jm.statefulapp.entities.Item;
import com.jm.statefulapp.entities.NotaFiscal;
import com.jm.statefulapp.entities.Prestador;
import com.jm.statefulapp.entities.Tomador;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Oscar Costa <oscarcosta@gmail.com>
 */
@ViewScoped
@ManagedBean(name="emissaoNotaFiscal")
public class EmissaoNotaFiscalView implements Serializable {

    @EJB
    NotaFiscalGateway notaFiscalGateway;
    
    private Prestador prestador = new Prestador();
    private Tomador tomador = new Tomador();
    private List<Item> itens = new ArrayList<Item>();
    
    @PostConstruct
    public void init() {
    }
    
    @PreDestroy
    public void destroy() {
        notaFiscalGateway.closeGateway();
    }
    
    public void criaNotaFiscal() {
        NotaFiscal notaFiscal = new NotaFiscal.Builder(prestador)
                                              .withTomador(tomador)
                                              .withItens(itens)
                                              .build();
        notaFiscalGateway.criaNotaFiscal(notaFiscal);
    }
    
    public void atualizaNotaFiscal() {
        notaFiscalGateway.atualizaNotaFiscal();
    }
    
    public void salvaNotaFiscal() {
        notaFiscalGateway.save();
    }
    
    public void adicionaItem() {
        itens.add(NotaFiscal.createItem());
    }
    
    public void removeItem(Item item) {
        itens.remove(item);
    }
    
    public void findPrestadorByDocumento() {
        prestador = notaFiscalGateway.pesquisaPrestadorPorDocumento(prestador.getDocumento());
    }
    
    public void findTomadorByDocumento() {
        tomador = notaFiscalGateway.pesquisaTomadorPorDocumento(tomador.getDocumento());
    }
    
    public List<Item> getItens() {
        return itens;
    }

    public BigDecimal getValorTotal() {
        return NotaFiscal.getValorTotal(itens);
    }

    public Prestador getPrestador() {
        return prestador;
    }

    public Tomador getTomador() {
        return tomador;
    }
    
}
