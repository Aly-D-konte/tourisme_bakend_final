package com.bezkoder.springjwt;

import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringBootSecurityJwtApplication implements CommandLineRunner {

	public static void main(String[] args) {
    SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
	}


	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;
	@Override
	public void run(String... args) throws Exception {
		Role role = new Role();
		User user = new User();
		role.setName(ERole.ROLE_ADMIN);
		role.setName(ERole.ROLE_USER);
		Set<Role> roles = new HashSet<>();

		roles.add(role);

		if (userRepository.findAll().size() == 0) {

			user.setEmail("alykonte@gmail.com");
			user.setRoles(roles);
			user.setUsername("aly");
//
//			user.setEmail("adama@gmail.com");
//			user.setRoles(roles);
//			user.setUsername("adama");

			user.setPassword(encoder.encode("aly@123"));
			//	user.setPassword(encoder.encode("adama@123"));


			roleRepository.save(role);
			userRepository.save(user);

		}
	}
}
