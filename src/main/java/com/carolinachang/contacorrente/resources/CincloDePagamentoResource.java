package com.carolinachang.contacorrente.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.carolinachang.contacorrente.domain.CicloDePagamento;
import com.carolinachang.contacorrente.domain.Conta;
import com.carolinachang.contacorrente.domain.Debito;
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
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Double> getSaldoMesAnterior(@RequestParam(value="mes",defaultValue="0") Integer mes,
            @RequestParam("ano") Integer ano){
		CicloDePagamento ciclo = cicloDePagamentoService.getSaldoMesAnterior(mes,ano);
		if(ciclo != null) {
			return ResponseEntity.ok().body(ciclo.getSaldo());	
		}
		return ResponseEntity.ok().body(0.0);
	}
	
	public Double getSaldo(@RequestParam(value="mes",defaultValue="0") Integer mes,
            @RequestParam("ano") Integer ano){
		 mes = mes > 1 ? mes - 1 : 12 ;
	     ano = (mes == 1) ? ano - 1 : ano;
		CicloDePagamento ciclo = cicloDePagamentoService.getSaldoMesAnterior(mes,ano);
		Double saldo =  (ciclo != null && ciclo.getSaldo() != null) ?  ciclo.getSaldo()  : 0.0;
		
		return saldo;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<CicloDePagamento> insert(@RequestBody CicloDePagamento ciclo){
		ciclo.setSaldo(getSaldo(ciclo));
		ciclo = cicloDePagamentoService.insert(ciclo);
		Conta conta = contaService.findById(ciclo.getConta().getId());
		conta.getCiclos().addAll(Arrays.asList(ciclo));
		contaService.update(conta);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ciclo.getId()).toUri();
		return ResponseEntity.created(uri).body(ciclo);
	}
	
	private Double getSaldo(CicloDePagamento ciclo) {
		Double saldoMesAnterior = this.getSaldo(ciclo.getMes(), ciclo.getAno());
		Double totalCreditos =  ciclo.getTotalCreditos() != null ?  ciclo.getTotalCreditos()  : 0.0;
		Double totalDebitos =  ciclo.getTotalDebitos() != null ?  ciclo.getTotalDebitos()  : 0.0;
		return saldoMesAnterior += totalCreditos - totalDebitos;
		
	}

	@RequestMapping(value="/clone", method=RequestMethod.POST)
	public ResponseEntity<CicloDePagamento> clone(@RequestBody CicloDePagamento newciclo){
		CicloDePagamento cicloClone = cicloDePagamentoService.findById(newciclo.getId());
		CicloDePagamento novoCiclo =  cloneData(cicloClone,newciclo);
		newciclo = cicloDePagamentoService.insert(novoCiclo);
		Conta conta = contaService.findById(newciclo.getConta().getId());
		conta.getCiclos().addAll(Arrays.asList(newciclo));
		contaService.update(conta);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newciclo.getId()).toUri();
		return ResponseEntity.created(uri).body(newciclo);
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
        novoCiclo.setSaldo(getSaldo(cicloClone));
        
    	return novoCiclo;
	}
	
	@RequestMapping(value="/{id}" ,method=RequestMethod.DELETE)
	public ResponseEntity<Conta> delete(@PathVariable String id){
		cicloDePagamentoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}" ,method=RequestMethod.PUT)
	public ResponseEntity<CicloDePagamento> update(@RequestBody CicloDePagamento ciclo, @PathVariable String id){
		List<Debito> debitos = new ArrayList<>();
		for (Debito t : ciclo.getDebitos()) {
			System.out.println("Antes >> " +t.getData());
			Date dt = new Date();
			Calendar c = Calendar.getInstance(); 
			c.setTime(t.getData()); 
			c.add(Calendar.DATE, 1);
			dt = c.getTime();
			System.out.println("Depois >> " +dt);
			t.setData(dt);
			debitos.add(t);
			
		}
		
		ciclo.setDebitos(debitos); 
		Collections.sort(ciclo.getDebitos(), new Comparator<Debito>() {
	        @Override
	        public int compare(Debito o1, Debito o2) {
	            // TODO Auto-generated method stub
	            return o1.getData().compareTo(o2.getData());
	        }
	    });
		
		ciclo.setSaldo(getSaldo(ciclo));
		
		ciclo = cicloDePagamentoService.update(ciclo);
		return ResponseEntity.ok().body(ciclo);
	}
	
	

}
