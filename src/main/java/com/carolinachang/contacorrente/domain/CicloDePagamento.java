package com.carolinachang.contacorrente.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CicloDePagamento implements Serializable {
    private static final long serialVersionUID = 1L;
	
    @Id
	private String id;
    private String nome;
    private Integer mes;
    private Integer ano;
    private Conta conta;
    private Double saldo;
    private Double totalCreditos;
    private Double totalDebitos;
    
    private List<Credito> creditos = new ArrayList<>();
    private List<Debito> debitos = new ArrayList<>();
    
    public CicloDePagamento() {
    	
    }
        
	public CicloDePagamento(String id, String nome, Integer mes, Integer ano,Conta conta) {
		super();
		this.id = id;
		this.nome = nome;
		this.mes = mes;
		this.ano = ano;
		this.conta = conta;
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
	public Integer getMês() {
		return getMes();
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	
	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	public Double getSaldo() {
		this.saldo = this.getTotalCreditos() - this.getTotalDebitos();
		return this.saldo;
	}

	public Double getTotalCreditos() {
		this.totalCreditos = getCreditos().stream().filter(valor -> valor != null && 
				valor.getValor() !=null).mapToDouble(Credito::getValor).sum();
		return totalCreditos;
	}

	public Double getTotalDebitos() {
		this.totalDebitos = getDebitos().stream().filter(valor -> valor != null && 
				valor.getValor() !=null).mapToDouble(Debito::getValor).sum();
		return totalDebitos;
	}

	public List<Credito> getCreditos() {
		return creditos;
	}

	public void setCreditos(List<Credito> creditos) {
		this.creditos = creditos;
	}

	public List<Debito> getDebitos() {
		return debitos;
	}

	public void setDebitos(List<Debito> debitos) {
		this.debitos = debitos;
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
		CicloDePagamento other = (CicloDePagamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
