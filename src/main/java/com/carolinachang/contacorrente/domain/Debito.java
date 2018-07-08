package com.carolinachang.contacorrente.domain;

import java.io.Serializable;
import java.util.Date;

import com.carolinachang.contacorrente.enums.Status;

public class Debito implements Serializable{
     private static final long serialVersionUID = 1L;
     
     private Date data;
     private String nome;
     private Double valor;
     private Status status;
     private DebitoDescricao descricao;

     public Debito() {
    	 
     }

	public Debito(Date data, String nome, Double valor) {
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public DebitoDescricao getDescricao() {
		return descricao;
	}

	public void setDescricao(DebitoDescricao descricao) {
		this.descricao = descricao;
	}
	
     
}
