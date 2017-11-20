package com.jm.statefulapp.view.beans;

import com.jm.statefulapp.boundary.NotaFiscalGateway;
import com.jm.statefulapp.entities.NotaFiscal;
import com.jm.statefulapp.view.utils.JsfUtil;
import java.io.Serializable;
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
@ManagedBean(name = "verificacaoNotaFiscal")
public class VerificacaoNotaFiscalView implements Serializable {

    @EJB
    private NotaFiscalGateway notaFiscalGateway;
    
    @PostConstruct
    public void init() {
        String id = JsfUtil.getRequestParameter("notaFiscalId");
        if (id == null || notaFiscalGateway.getNotaFiscal(Long.valueOf(id)) == null) {
            JsfUtil.addErrorMessage("erro.notafiscal.nao.encontrada");
        }
    }
    
    @PreDestroy
    public void destroy() {
        notaFiscalGateway.closeGateway();
    }

    public NotaFiscal getNotaFiscal() {
        return notaFiscalGateway.getNotaFiscal();
    }
    
}
