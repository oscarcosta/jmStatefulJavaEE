package com.jm.statefulapp.boundary;

import com.jm.statefulapp.entities.*;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Oscar Costa <oscarcosta@gmail.com>
 */
public class NotaFiscalGatewayTest {

    private static EJBContainer container;
    private static NotaFiscalGateway gateway;
    private static NotaFiscalGateway gateway2;

    @BeforeClass
    public static void setUpClass() throws Exception {
        container = EJBContainer.createEJBContainer();
        gateway = (NotaFiscalGateway) container.getContext().lookup("java:global/classes/NotaFiscalGateway");
        gateway2 = (NotaFiscalGateway) container.getContext().lookup("java:global/classes/NotaFiscalGateway");
    }
    
    @AfterClass
    public static void tearDownClass() throws Exception {
        gateway.closeGateway();
        container.close();
    }
    
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");

        List<Item> itens = new ArrayList<Item>();
        itens.add(NotaFiscal.createItem("Curso Java I", 1800.00, 1));
        itens.add(NotaFiscal.createItem("Curso Java II", 2400.00, 1));
                
        NotaFiscal nf = new NotaFiscal.Builder(new Pessoa.Builder<Prestador>(Prestador.class, "Clinica Java EE", "99999999999").build())
                                      .withTomador(new Pessoa.Builder<Tomador>(Tomador.class, "José da Silva", "12345678900").build())
                                      .withItens(itens)
                                      .build();
        gateway.criaNotaFiscal(nf);
        Long nfId = nf.getId();
        
        NotaFiscal nfCurrent = gateway.getNotaFiscal();
        assertSame(nf, nfCurrent); // É o mesmo objeto da sessão?
        
        NotaFiscal found = gateway2.getNotaFiscal(nfId);
        assertNull(found); // Ainda não foi persistido?
        
        gateway.save();
        
        found = gateway2.getNotaFiscal(nfId);
        assertNotNull(found); // Foi persistido?
    }
    
}
