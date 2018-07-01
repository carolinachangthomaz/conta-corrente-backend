package com.carolinachang.contacorrente.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Conta implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
     private String id;
     private String numero;
     private Cliente cliente;
     
     public Conta() {
    	 
     }

	public Conta(String id, String numero,Cliente cliente) {
		super();
		this.id = id;
		this.numero = numero;
		this.cliente = cliente;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
     
     
     
}
