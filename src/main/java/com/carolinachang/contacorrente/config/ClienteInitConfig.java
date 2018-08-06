package com.carolinachang.contacorrente.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.carolinachang.contacorrente.domain.Cliente;
import com.carolinachang.contacorrente.domain.Conta;
import com.carolinachang.contacorrente.dto.ClienteDTO;
import com.carolinachang.contacorrente.repository.CicloDePagamentoRepository;
import com.carolinachang.contacorrente.repository.ClienteRepository;
import com.carolinachang.contacorrente.repository.ContaRepository;
import com.carolinachang.contacorrente.repository.DebitoDescricaoRepository;

@Configuration
public class ClienteInitConfig implements CommandLineRunner{
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private CicloDePagamentoRepository cicloDePagamentoRepository;
	
	@Autowired
	private DebitoDescricaoRepository descricaoRepository;

	@Override
	public void run(String... args) throws Exception {
		
		clienteRepository.deleteAll();
		contaRepository.deleteAll();
		cicloDePagamentoRepository.deleteAll();
		//descricaoRepository.deleteAll();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		
		Cliente carol = new Cliente(null,"Carol","carol.com.jp@gmail.com");
		Cliente xis = new Cliente(null,"Xis","xiscardoso@gmail.com");
		
		//clienteRepository.saveAll(Arrays.asList(carol,xis));
		
		Conta conta = new Conta(null, "Conta Corrente", new ClienteDTO(carol));
		conta = contaRepository.save(conta);
		
		carol.getContas().addAll(Arrays.asList(conta));
		
	    clienteRepository.saveAll(Arrays.asList(carol,xis));
		

		
		
		
		
	}

	
}
