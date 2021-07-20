package com.example.usermongodb;

import com.example.usermongodb.config.sequence.SequenceService;
import com.example.usermongodb.dao.RoleRepository;
import com.example.usermongodb.enums.RoleEnum;
import com.example.usermongodb.models.Role;
import com.example.usermongodb.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserMongodbApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private SequenceService sequenceService;

	public static void  main(String[] args) {
		SpringApplication.run(UserMongodbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		roleRepository.deleteAll();
		Role roleAdmin = new Role(sequenceService.generateSequence(Role.SEQUENCE_NAME), RoleEnum.ADMIN.name());
		Role roleUser = new Role(sequenceService.generateSequence(Role.SEQUENCE_NAME), RoleEnum.USER.name());
		roleRepository.save(roleAdmin);
		roleRepository.save(roleUser);
	}

	}
