package com.carolinachang.contacorrente.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.carolinachang.contacorrente.domain.Conta;
import com.carolinachang.contacorrente.dto.ClienteDTO;

@Repository
public interface ContaRepository extends MongoRepository<Conta, String>{

	List<Conta> findByClienteDTO(ClienteDTO clienteId);
}
