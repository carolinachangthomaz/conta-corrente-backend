package com.carolinachang.contacorrente.domain;

import java.io.Serializable;
import java.util.Date;

public class Credito implements Serializable{
     private static final long serialVersionUID = 1L;
     
     private Date data;
     private String nome;
     private Double valor;

     public Credito() {
    	 
     }

	public Credito(Date data, String nome, Double valor) {
		super();
		this.data = data;
		this.nome = nome;
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
     
}
