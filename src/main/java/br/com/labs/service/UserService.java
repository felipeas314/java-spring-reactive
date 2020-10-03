package br.com.labs.service;

import java.util.List;
import java.util.function.BiFunction;

import org.springframework.stereotype.Service;

import br.com.labs.dto.UserDepartmentDTO;
import br.com.labs.model.Department;
import br.com.labs.model.User;
import br.com.labs.repository.DepartmentRepository;
import br.com.labs.repository.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class UserService {

	private UserRepository userRepository;

	private DepartmentRepository departmentRepository;

	public UserService(UserRepository userRepository, DepartmentRepository departmentRepository) {
		this.userRepository = userRepository;
		this.departmentRepository = departmentRepository;
	}

	public Mono<User> createUser(User user) {
		return userRepository.save(user);
	}

	public Flux<User> getAllUsers() {
		return userRepository.findAll();
	}

	public Mono<User> findById(Integer userId) {
		return userRepository.findById(userId);
	}
	
	public Mono<User> updateUser(Integer userId,  User user){
        return userRepository.findById(userId)
                .flatMap(dbUser -> {
                    dbUser.setAge(user.getAge());
                    dbUser.setSalary(user.getSalary());
                    return userRepository.save(dbUser);
                });
    }

    public Mono<User> deleteUser(Integer userId){
        return userRepository.findById(userId)
                .flatMap(existingUser -> userRepository.delete(existingUser)
                .then(Mono.just(existingUser)));
    }
    
    public Flux<User> findUsersByAge(int age){
        return userRepository.findByAge(age);
    }

    public Flux<User> fetchUsers(List<Integer> userIds) {
        return Flux.fromIterable(userIds)
                .parallel()
                .runOn(Schedulers.elastic())
                .flatMap(i -> findById(i))
                .ordered((u1, u2) -> u2.getId() - u1.getId());
    }

    private Mono<Department> getDepartmentByUserId(Integer userId){
        return departmentRepository.findByUserId(userId);
    }

    public Mono<UserDepartmentDTO> fetchUserAndDepartment(Integer userId){
        Mono<User> user = findById(userId).subscribeOn(Schedulers.elastic());
        Mono<Department> department = getDepartmentByUserId(userId).subscribeOn(Schedulers.elastic());
        return Mono.zip(user, department, userDepartmentDTOBiFunction);
    }


    private BiFunction<User, Department, UserDepartmentDTO> userDepartmentDTOBiFunction = (x1, x2) 
    		-> new UserDepartmentDTO.UserDepartmentDTOBuilder()
    			.age(x1.getAge())
    			.userId(x1.getId())
    			.userName(x1.getName())
    			.departmentId(x2.getId())
	            .departmentName(x2.getName())
	            .loc(x2.getLoc())
	            .salary(x1.getSalary()).build();
}
