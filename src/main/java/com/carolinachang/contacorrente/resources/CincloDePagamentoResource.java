package com.carolinachang.contacorrente.resources;

import java.net.URI;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.carolinachang.contacorrente.domain.CicloDePagamento;
import com.carolinachang.contacorrente.domain.Conta;
import com.carolinachang.contacorrente.services.CicloDePagamentoService;
import com.carolinachang.contacorrente.services.ContaService;

@RestController
@RequestMapping(value="ciclos")
public class CincloDePagamentoResource {
	
	@Autowired
	private CicloDePagamentoService cicloDePagamentoService;
	
	@Autowired
	private ContaService contaService;
	
	@RequestMapping(value="/{id}" ,method=RequestMethod.GET)
	public ResponseEntity<CicloDePagamento> findById(@PathVariable String id){
		CicloDePagamento ciclo = cicloDePagamentoService.findById(id);
		return ResponseEntity.ok().body(ciclo);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody CicloDePagamento ciclo){
		ciclo = cicloDePagamentoService.insert(ciclo);
		Conta conta = contaService.findById(ciclo.getConta().getId());
		conta.getCiclos().addAll(Arrays.asList(ciclo));
		contaService.update(conta);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ciclo.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/clone", method=RequestMethod.POST)
	public ResponseEntity<Void> clone(@RequestBody CicloDePagamento newciclo){
		CicloDePagamento cicloClone = cicloDePagamentoService.findById(newciclo.getId());
		CicloDePagamento novoCiclo =  cloneData(cicloClone,newciclo);
		newciclo = cicloDePagamentoService.insert(novoCiclo);
		Conta conta = contaService.findById(newciclo.getConta().getId());
		conta.getCiclos().addAll(Arrays.asList(newciclo));
		contaService.update(conta);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newciclo.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
    private CicloDePagamento cloneData(CicloDePagamento cicloClone, CicloDePagamento newciclo) {
    	CicloDePagamento novoCiclo = new CicloDePagamento();
    	novoCiclo.setId(null);
    	novoCiclo.setNome(newciclo.getNome());
    	novoCiclo.setMes(newciclo.getMes());
        novoCiclo.setAno(newciclo.getAno());
        novoCiclo.setConta(cicloClone.getConta());
        novoCiclo.setCreditos(cicloClone.getCreditos());
        novoCiclo.setDebitos(cicloClone.getDebitos());
        
        
    	return novoCiclo;
	}
	
	@RequestMapping(value="/{id}" ,method=RequestMethod.DELETE)
	public ResponseEntity<Conta> delete(@PathVariable String id){
		cicloDePagamentoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}" ,method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody CicloDePagamento ciclo, @PathVariable String id){
		ciclo = cicloDePagamentoService.update(ciclo);
		return ResponseEntity.noContent().build();
	}
	
	

}
