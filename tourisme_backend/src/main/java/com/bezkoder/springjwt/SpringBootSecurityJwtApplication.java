package com.bezkoder.springjwt;

import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.RegionsRepository;
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
	@Autowired
	private RegionsRepository regionsRepository;

	@Override
	public void run(String... args) throws Exception {
		Role role = new Role();
		User user = new User();
		role.setName(ERole.ROLE_ADMIN);
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


		Role R = new Role();
		R.setName(ERole.ROLE_USER);

		if (regionsRepository.findAll().size()== 0){
			roleRepository.save(R);

		}

	}
}
