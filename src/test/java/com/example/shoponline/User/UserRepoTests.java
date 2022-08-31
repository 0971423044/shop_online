package com.example.shoponline.User;

import com.example.shoponline.models.Role;
import com.example.shoponline.models.User;
import com.example.shoponline.repositories.RoleRepository;
import com.example.shoponline.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepoTests {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUer()
    {
        Role roleAdmin = entityManager.find(Role.class,1);
        User user1 = new User("xhaka@gmail.com","123","xuan","xhaka");
        user1.addRole(roleAdmin);
        User savedUser = userRepo.save(user1);

        assertThat(savedUser.getId()).isGreaterThan(0);
    }

}
