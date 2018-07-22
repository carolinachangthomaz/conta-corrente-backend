package com.carolinachang.contacorrente.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.carolinachang.contacorrente.dto.ContaClienteDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Document
public class Conta implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
     private String id;
     private String nome;
     private Double saldo;
     private ContaClienteDTO clienteDTO;
     
     @DBRef(lazy = false)
 	 @JsonIgnore
     private List<CicloDePagamento> ciclos = new ArrayList<>();	
     
     public Conta() {
    	 
     }

	public Conta(String id, String numero,ContaClienteDTO cliente) {
		super();
		this.id = id;
		this.nome = numero;
		this.clienteDTO = cliente;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public ContaClienteDTO getClienteDTO() {
		return clienteDTO;
	}

	public void setClienteDTO(ContaClienteDTO clienteDTO) {
		this.clienteDTO = clienteDTO;
	}

	public List<CicloDePagamento> getCiclos() {
		return ciclos;
	}

	public void setCiclos(List<CicloDePagamento> ciclos) {
		this.ciclos = ciclos;
	}
	
	public Double getSaldo() {
		Double creditos = getCiclos().stream().filter(valor -> valor != null && 
				valor.getTotalCreditos() !=null).mapToDouble(CicloDePagamento :: getTotalCreditos).sum();
		Double debitos = getCiclos().stream().filter(valor -> valor != null && 
				valor.getTotalDebitos() !=null).mapToDouble(CicloDePagamento :: getTotalDebitos).sum();
		if(creditos >= debitos) {
			saldo =  creditos - debitos;
		}else if(creditos < debitos){
			saldo = debitos - creditos;
		}
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
