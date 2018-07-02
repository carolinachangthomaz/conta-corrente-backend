package com.carolinachang.contacorrente.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.carolinachang.contacorrente.domain.CicloDePagamento;
import com.carolinachang.contacorrente.services.CicloDePagamentoService;

@RestController
@RequestMapping(value="ciclos")
public class CincloDePagamentoResource {
	
	@Autowired
	private CicloDePagamentoService cicloDePagamentoService;
	
	@RequestMapping(value="/{id}" ,method=RequestMethod.GET)
	public ResponseEntity<CicloDePagamento> findById(@PathVariable String id){
		CicloDePagamento contas = cicloDePagamentoService.findById(id);
		return ResponseEntity.ok().body(contas);
	}

}
