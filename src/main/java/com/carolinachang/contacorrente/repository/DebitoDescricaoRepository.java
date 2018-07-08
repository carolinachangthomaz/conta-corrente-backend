package com.carolinachang.contacorrente.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.carolinachang.contacorrente.domain.DebitoDescricao;

@Repository
public interface DebitoDescricaoRepository extends MongoRepository<DebitoDescricao, String>{

}
