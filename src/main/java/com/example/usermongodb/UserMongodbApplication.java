package com.example.usermongodb;

import com.example.usermongodb.config.sequence.SequenceService;
import com.example.usermongodb.dao.RoleRepository;
import com.example.usermongodb.enums.RoleEnum;
import com.example.usermongodb.models.Role;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
		Role roleAdmin = new Role();
		Role roleUser = new Role();
		roleAdmin.setId(sequenceService.generateSequence(Role.SEQUENCE_NAME));
		roleUser.setId(sequenceService.generateSequence(Role.SEQUENCE_NAME));
		roleAdmin.setName(RoleEnum.USER.label);
		roleUser.setName(RoleEnum.ADMIN.label);
		roleRepository.save(roleAdmin);
		roleRepository.save(roleUser);
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	}
