package com.carolinachang.contacorrente.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.carolinachang.contacorrente.domain.CicloDePagamento;
import com.carolinachang.contacorrente.domain.Cliente;
import com.carolinachang.contacorrente.repository.CicloDePagamentoRepository;
import com.carolinachang.contacorrente.services.exception.ObjectNotFoundException;

@Service
public class CicloDePagamentoService {
	
	@Autowired
	private CicloDePagamentoRepository cicloDePagamentoRepository;
		
	public CicloDePagamento findById(String id) {
		Optional<CicloDePagamento> CicloDePagamento = cicloDePagamentoRepository.findById(id);
		return	CicloDePagamento.orElseThrow(() -> new ObjectNotFoundException(
				"Ciclo Não encontrado: " +id+ ", Tipo :" +Cliente.class));
	}
	
	public CicloDePagamento insert(CicloDePagamento ciclo) {
		return cicloDePagamentoRepository.insert(ciclo);
	}
	
	public void delete(String id) {
		findById(id);
		cicloDePagamentoRepository.deleteById(id);
	}
	
	public CicloDePagamento update(CicloDePagamento ciclo) {
		CicloDePagamento newCiclo = findById(ciclo.getId());
		updateData(newCiclo, ciclo);
		return cicloDePagamentoRepository.save(newCiclo);
		
	}

	private void updateData(CicloDePagamento newCiclo, CicloDePagamento ciclo) {
		newCiclo.setNome(ciclo.getNome());
		newCiclo.setMes(ciclo.getMes());
		newCiclo.setAno(ciclo.getAno());
		newCiclo.setCreditos(ciclo.getCreditos());
		newCiclo.setDebitos(ciclo.getDebitos());
		newCiclo.setSaldo(ciclo.getSaldo());
	}

	public Double getSaldo(@RequestParam(value="mes",defaultValue="0") Integer mes,
            @RequestParam("ano") Integer ano){
		 mes = mes > 1 ? mes - 1 : 12 ;
	     ano = (mes == 1) ? ano - 1 : ano;
		CicloDePagamento ciclo = this.getSaldoMesAnterior(mes,ano);
		Double saldo =  (ciclo != null && ciclo.getSaldo() != null) ?  ciclo.getSaldo()  : 0.0;
		
		return saldo;
	}
	
	public CicloDePagamento getSaldoMesAnterior(Integer mes, Integer ano) {
		return cicloDePagamentoRepository.getSaldoMesAnterior(mes,ano);
	}
	
	public Double getSaldo(CicloDePagamento ciclo) {
		Double saldoMesAnterior = this.getSaldo(ciclo.getMes(), ciclo.getAno());
		Double totalCreditos =  ciclo.getTotalCreditos() != null ?  ciclo.getTotalCreditos()  : 0.0;
		Double totalDebitos =  ciclo.getTotalDebitos() != null ?  ciclo.getTotalDebitos()  : 0.0;
		return saldoMesAnterior += totalCreditos - totalDebitos;
		
	}

}
