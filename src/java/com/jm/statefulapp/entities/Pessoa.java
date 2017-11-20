package com.jm.statefulapp.entities;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author Oscar Costa <oscarcosta@gmail.com>
 */
@MappedSuperclass
public abstract class Pessoa implements Serializable {

    protected String nome;
    
    protected String documento;
    
    protected Endereco endereco;
    
    protected Pessoa() {
        this.endereco = new Endereco();
    }
    
    public abstract Long getId();
    
    protected void addEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public static class Builder<T extends Pessoa> {
    
        private T pessoa;

        public Builder(Class<T> clazz, String nome, String documento) 
                throws Exception {
            pessoa = clazz.newInstance();
            this.pessoa.setNome(nome);
            this.pessoa.setDocumento(documento);
        }
        
        public Builder withEndereco(Endereco endereco) {
            this.pessoa.addEndereco(endereco);
            return this;
        }
                
        public T build() {
            if (pessoa.documento == null || pessoa.nome == null) {
                throw new IllegalStateException("Erro ao criar pessoa.");
            }
            return this.pessoa;
        }
    }
    
    public String getDocumento() {
        return documento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
