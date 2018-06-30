package com.carolinachang.contacorrente.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.carolinachang.contacorrente.domain.Cliente;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String>{

}
