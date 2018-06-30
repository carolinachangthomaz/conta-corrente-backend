package com.carolinachang.contacorrente.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.carolinachang.contacorrente.domain.Cliente;
import com.carolinachang.contacorrente.repository.ClienteRepository;

@Configuration
public class ClienteInitConfig implements CommandLineRunner{
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void run(String... args) throws Exception {
		
		clienteRepository.deleteAll();
		
		Cliente carol = new Cliente(null,"Carol","carol.com.jp@gmail.com");
		Cliente xis = new Cliente(null,"Xis","xiscardoso@gmail.com");
		
		clienteRepository.saveAll(Arrays.asList(carol,xis));
		
	}

	
}
