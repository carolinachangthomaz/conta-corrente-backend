package com.carolinachang.contacorrente.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Conta implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
     private String id;
     private String numero;
     private Double saldo;
     private Cliente cliente;
     
     @DBRef
     private List<CicloDePagamento> ciclos = new ArrayList<>();	
     
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
	
	public List<CicloDePagamento> getCiclos() {
		return ciclos;
	}

	public void setCiclos(List<CicloDePagamento> ciclos) {
		this.ciclos = ciclos;
	}
	
	public Double getSaldo() {
		this.saldo = getCiclos().stream().filter(ciclo -> ciclo != null && 
				ciclo.getSaldo() !=null).mapToDouble(CicloDePagamento::getSaldo).sum();
		System.out.println("Conta" +this.saldo);
		return saldo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

    
}
