package com.carolinachang.contacorrente.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.carolinachang.contacorrente.domain.CicloDePagamento;

@Repository
public interface CicloDePagamentoRepository extends MongoRepository<CicloDePagamento, String>{

	@Query("{ $and: [ { mes: { $eq: ?0 } }, {  ano: { $eq: ?1 }  } ] }")
	CicloDePagamento getSaldoMesAnterior(Integer mes, Integer ano);

}
