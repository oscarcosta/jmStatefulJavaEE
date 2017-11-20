package com.jm.statefulapp.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Oscar Costa <oscarcosta@gmail.com>
 */
@Entity
public class Tomador extends Pessoa implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @OneToMany(mappedBy = "tomador")
    protected List<NotaFiscal> notasFiscais;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<NotaFiscal> getNotasFiscais() {
        return notasFiscais;
    }

    public void setNotasFiscais(List<NotaFiscal> notasFiscais) {
        this.notasFiscais = notasFiscais;
    }
    
}
