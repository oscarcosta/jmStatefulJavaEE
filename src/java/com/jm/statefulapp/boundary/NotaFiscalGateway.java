package com.jm.statefulapp.boundary;

import com.jm.statefulapp.entities.NotaFiscal;
import com.jm.statefulapp.entities.Pessoa;
import com.jm.statefulapp.entities.Prestador;
import com.jm.statefulapp.entities.Tomador;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Oscar Costa <oscarcosta@gmail.com>
 */
@Stateful
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class NotaFiscalGateway {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;
    
    private NotaFiscal notaFiscal;
    
    public NotaFiscal getNotaFiscal(long id) {
        notaFiscal = em.find(NotaFiscal.class, id);
        return notaFiscal;
    }
    
    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }
    
    public void criaNotaFiscal(NotaFiscal notaFiscal) {
        em.persist(notaFiscal);
        this.notaFiscal = notaFiscal;
    }
    
    public void atualizaNotaFiscal() {
        if (notaFiscal != null && notaFiscal.getId() != null) {
            notaFiscal = em.merge(notaFiscal);
        }
    }

    public void excluiNotaFiscal() {
        if (notaFiscal != null && notaFiscal.getId() != null) {
            em.remove(notaFiscal);
        }
        notaFiscal = null;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void save() {
    }

    @Remove
    public void closeGateway() {
    }
    
    private <P extends Pessoa> P pesquisaPessoa(Class tipo, 
                                                String atributo, 
                                                String documento) {
        CriteriaBuilder cBuilder = em.getCriteriaBuilder();
        CriteriaQuery<P> cQuery = cBuilder.createQuery(tipo);
        
        Root<P> from = cQuery.from(tipo);
        cQuery.where(cBuilder.equal(from.get(atributo), documento));
        
        TypedQuery<P> tQuery = em.createQuery(cQuery);
        
        try {
            return tQuery.getSingleResult();
        } catch (Exception e) {
        }
        return null;
    }
    
    public Prestador pesquisaPrestadorPorDocumento(String documento) {
        return pesquisaPessoa(Prestador.class, "documento", documento);
    }
    
    public Tomador pesquisaTomadorPorDocumento(String documento) {
        return pesquisaPessoa(Tomador.class, "documento", documento);
    }

}
