package com.carolinachang.contacorrente.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.carolinachang.contacorrente.domain.DebitoDescricao;
import com.carolinachang.contacorrente.dto.ClienteDTO;
import com.carolinachang.contacorrente.services.DebitoDescricaoService;

@RestController
@RequestMapping(value="debitosdescricoes")
public class DebitoDescricaoResource {
	
	@Autowired
	private DebitoDescricaoService descricaoService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<DebitoDescricao>> findAll(){
		List<DebitoDescricao> descricoes = descricaoService.findAll();
		return ResponseEntity.ok().body(descricoes);
	}
	
	@RequestMapping(value="/{id}" ,method=RequestMethod.GET)
	public ResponseEntity<DebitoDescricao> findById(@PathVariable String id){
		DebitoDescricao descricao = descricaoService.findById(id);
		return ResponseEntity.ok().body(descricao);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<DebitoDescricao> insert(@RequestBody DebitoDescricao descricao){
		descricao = descricaoService.insert(descricao);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(descricao.getId()).toUri();
		return ResponseEntity.created(uri).body(descricao);
	}
	
	@RequestMapping(value="/{id}" ,method=RequestMethod.DELETE)
	public ResponseEntity<ClienteDTO> delete(@PathVariable String id){
		descricaoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}" ,method=RequestMethod.PUT)
	public ResponseEntity<DebitoDescricao> update(@RequestBody DebitoDescricao descricao, @PathVariable String id){
		descricao.setId(id);
		descricao = descricaoService.update(descricao);
		return ResponseEntity.ok().body(descricao);
	}


}
