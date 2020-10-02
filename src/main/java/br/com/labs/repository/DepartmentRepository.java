package br.com.labs.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import br.com.labs.model.Department;
import reactor.core.publisher.Mono;

public interface DepartmentRepository extends ReactiveCrudRepository<Department, Integer>{

	Mono<Department> findByUserId(Integer userId);
}
