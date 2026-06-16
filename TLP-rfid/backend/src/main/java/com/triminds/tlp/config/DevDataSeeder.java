package com.triminds.tlp.config;

import com.triminds.tlp.company.model.Company;
import com.triminds.tlp.company.repository.CompanyRepository;
import com.triminds.tlp.user.model.Role;
import com.triminds.tlp.user.model.User;
import com.triminds.tlp.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile({"dev", "default"})
public class DevDataSeeder {

    @Bean
    CommandLineRunner seedDemoUser(
            CompanyRepository companies,
            UserRepository users,
            PasswordEncoder encoder
    ) {
        return args -> {
            if (users.findByEmail("admin@triminds.com").isPresent()) {
                System.out.println("[Seeder] Usuário demo já existe.");
                return;
            }

            Company c = new Company();
            c.setName("Triminds Demo");
            c.setTenantId("demo");
            companies.save(c);

            User u = new User();
            u.setEmail("admin@triminds.com");
            u.setPassword(encoder.encode("admin123"));
            u.setRole(Role.ADMIN);
            u.setCompany(c);
            users.save(u);

            System.out.println("=================================================");
            System.out.println("[Seeder] Usuário demo criado:");
            System.out.println("   email:    admin@triminds.com");
            System.out.println("   senha:    admin123");
            System.out.println("=================================================");
        };
    }
}
