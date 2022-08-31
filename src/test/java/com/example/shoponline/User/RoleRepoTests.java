package com.example.shoponline.User;

import com.example.shoponline.models.Role;
import com.example.shoponline.repositories.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoleRepoTests {
    @Autowired
    private RoleRepository roleRepo;

    @Test
    public void testCreateRole()
    {
        Role roleAdmin = new Role("Admin","Do everything!");
        Role savedRole = roleRepo.save(roleAdmin);

        assertThat(savedRole.getId()).isGreaterThan(0);

    }
}
