package br.com.labs.initialize;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.com.labs.model.Department;
import br.com.labs.model.User;
import br.com.labs.repository.DepartmentRepository;
import br.com.labs.repository.UserRepository;
import reactor.core.publisher.Flux;

@Component
@Profile("!test")
public class UserInitializer implements CommandLineRunner{

	 @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private DepartmentRepository departmentRepository;
	    
	    @Override
	    public void run(String... args) {
	            initialDataSetup();
	    }

	    private List<User> getData(){
	        return Arrays.asList(new User(null,"Suman Das",30,10000),
	                             new User(null,"Arjun Das",5,1000),
	                             new User(null,"Saurabh Ganguly",40,1000000));
	    }

	    private List<Department> getDepartments(){
	        return Arrays.asList(new Department(null,"Mechanical",1,"Mumbai"),
	                new Department(null,"Computer",2,"Bangalore"));
	    }

	    private void initialDataSetup() {
	        userRepository.deleteAll()
	                .thenMany(Flux.fromIterable(getData()))
	                .flatMap(userRepository::save)
	                .thenMany(userRepository.findAll())
	                .subscribe(user -> {
	                   System.out.println("User Inserted from CommandLineRunner " + user);
	                });

	        departmentRepository.deleteAll()
	                .thenMany(Flux.fromIterable(getDepartments()))
	                .flatMap(departmentRepository::save)
	                .thenMany(departmentRepository.findAll())
	                .subscribe(user -> {
	                	System.out.println("Department Inserted from CommandLineRunner " + user);
	                });

	    }
}
