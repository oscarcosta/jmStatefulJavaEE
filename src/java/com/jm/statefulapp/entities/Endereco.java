package com.jm.statefulapp.entities;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Oscar Costa <oscarcosta@gmail.com>
 */
@Embeddable
public class Endereco implements Serializable {

    protected String logradouro;
    
    protected String numero;
    
    protected String complemento;
    
    protected String bairro;
    
    protected String municipio;
    
    protected String cep;
    
    protected String uf;

    public String getBairro() {
        return bairro;
    }

    public String getCep() {
        return cep;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getUf() {
        return uf;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }
    
    public String getEnderecoFormatado() {
        return logradouro + ", " + numero + ", " + complemento + ", " + bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

}