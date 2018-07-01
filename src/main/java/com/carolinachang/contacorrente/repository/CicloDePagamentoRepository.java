package com.carolinachang.contacorrente.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.carolinachang.contacorrente.domain.CicloDePagamento;
import com.carolinachang.contacorrente.domain.Conta;

@Repository
public interface CicloDePagamentoRepository extends MongoRepository<CicloDePagamento, String>{

}
