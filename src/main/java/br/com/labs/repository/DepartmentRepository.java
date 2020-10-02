package br.com.labs.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import br.com.labs.model.Departament;
import reactor.core.publisher.Mono;

public interface DepartmentRepository extends ReactiveCrudRepository<Departament, Integer>{

	Mono<Departament> findByUserId(Integer userId);
}
