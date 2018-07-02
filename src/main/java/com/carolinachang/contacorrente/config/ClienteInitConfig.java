package com.carolinachang.contacorrente.config;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.carolinachang.contacorrente.domain.CicloDePagamento;
import com.carolinachang.contacorrente.domain.Cliente;
import com.carolinachang.contacorrente.domain.Conta;
import com.carolinachang.contacorrente.domain.Credito;
import com.carolinachang.contacorrente.domain.Debito;
import com.carolinachang.contacorrente.repository.CicloDePagamentoRepository;
import com.carolinachang.contacorrente.repository.ClienteRepository;
import com.carolinachang.contacorrente.repository.ContaRepository;

@Configuration
public class ClienteInitConfig implements CommandLineRunner{
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private CicloDePagamentoRepository cicloDePagamentoRepository;

	@Override
	public void run(String... args) throws Exception {
		
		clienteRepository.deleteAll();
		contaRepository.deleteAll();
		cicloDePagamentoRepository.deleteAll();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		
		Cliente carol = new Cliente(null,"Carol","carol.com.jp@gmail.com");
		Cliente xis = new Cliente(null,"Xis","xiscardoso@gmail.com");
		
		
		
		Conta conta = new Conta(null, "2324", carol);
			
		conta = contaRepository.save(conta);
		
		carol.getContas().addAll(Arrays.asList(conta));
		clienteRepository.saveAll(Arrays.asList(carol,xis));
		
		CicloDePagamento ciclo1 = new CicloDePagamento(null, "Junho", 6, 2018,conta);
		Credito credito1 = new Credito(sdf.parse("01/06/2018"), "Salario", 5000.00);
		Debito debito1 = new Debito(sdf.parse("01/06/2018"), "telefone", 1000.00);
		
		ciclo1.getCreditos().addAll(Arrays.asList(credito1));
		ciclo1.getDebitos().addAll(Arrays.asList(debito1));
		
		CicloDePagamento ciclo2 = new CicloDePagamento(null, "Julho", 7, 2018,conta);
		Credito credito2 = new Credito(sdf.parse("01/07/2018"), "Salario", 5000.00);
		Debito debito2 = new Debito(sdf.parse("01/07/2018"), "telefone", 500.00);
		
		ciclo2.getCreditos().addAll(Arrays.asList(credito2));
		ciclo2.getDebitos().addAll(Arrays.asList(debito2));
		
		
		cicloDePagamentoRepository.saveAll(Arrays.asList(ciclo1,ciclo2));
		
		System.out.println("C1 Saldo" +ciclo1.getSaldo());
		System.out.println("C1 Total Cred" +ciclo1.getTotalCreditos());
		System.out.println("C1 Total deb" +ciclo1.getTotalDebitos());
		
		
		System.out.println("C2 Saldo" +ciclo2.getSaldo());
		System.out.println("C2 Total Cred" +ciclo2.getTotalCreditos());
		System.out.println("C2 Total deb" +ciclo2.getTotalDebitos());
		
		conta.getCiclos().addAll(Arrays.asList(ciclo1,ciclo2));
		
		contaRepository.save(conta);
		
		
	}

	
}
