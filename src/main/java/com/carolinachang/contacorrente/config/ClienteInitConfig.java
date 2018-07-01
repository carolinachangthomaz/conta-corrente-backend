package com.carolinachang.contacorrente.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.carolinachang.contacorrente.domain.Cliente;
import com.carolinachang.contacorrente.domain.Conta;
import com.carolinachang.contacorrente.repository.ClienteRepository;
import com.carolinachang.contacorrente.repository.ContaRepository;

@Configuration
public class ClienteInitConfig implements CommandLineRunner{
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ContaRepository contaRepository;

	@Override
	public void run(String... args) throws Exception {
		
		clienteRepository.deleteAll();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		
		Cliente carol = new Cliente(null,"Carol","carol.com.jp@gmail.com");
		Cliente xis = new Cliente(null,"Xis","xiscardoso@gmail.com");
		
		Conta conta = new Conta(null, "2324", carol);
		
		clienteRepository.saveAll(Arrays.asList(carol,xis));
		contaRepository.saveAll(Arrays.asList(conta));
		
		
	}

	
}
