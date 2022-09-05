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
    public void testCreateUser()
    {
        Role roleAdmin = entityManager.find(Role.class,1);
        User user1 = new User("xhaka@gmail.com","123","xuan","xhaka");
        user1.addRole(roleAdmin);
        User savedUser = userRepo.save(user1);

        assertThat(savedUser.getId()).isGreaterThan(0);
    }
    @Test
    public void testCreateUserWithTwoRoles()
    {
        User user2 = new User("messi@gmail.com","123","ngoc","messi");
        Role roleSales = entityManager.find(Role.class,3);
        Role roleEditor = entityManager.find(Role.class,4);
        user2.addRole(roleSales);
        user2.addRole(roleEditor);

        User savedUser2 = userRepo.save(user2);

        assertThat(savedUser2.getId()).isGreaterThan(0);
    }
    @Test
    public void testPrintListUser()
    {
        Iterable<User> listUsers = userRepo.findAll();
        listUsers.forEach(user -> System.out.println(user));
    }

    @Test
    public void testGetUserById()
    {
        User userXhaka  = userRepo.findById(1).get();
        System.out.println(userXhaka);
        assertThat(userXhaka).isNotNull();
    }
    @Test
    public void testUpdateUserDetails()
    {
        User userMessi = userRepo.findById(7).get();
        userMessi.setEnabled(true);
        userMessi.setEmail("ngoc@gmail.com");

        userRepo.save(userMessi);
    }
    @Test
    public void testUpdateUserRoles()
    {
        User userMessi = userRepo.findById(7).get();

        Role roleSales = entityManager.find(Role.class, 3);

        Role roleShipper = entityManager.find(Role.class,5);

        userMessi.getRoles().remove(roleSales);

        userMessi.getRoles().add(roleShipper);

        userRepo.save(userMessi);
    }
    @Test
    public void testDeleteUser()
    {
        Integer userId  = 6;
        userRepo.deleteById(userId);
    }
    @Test
    public void testGetUserByEmail()
    {
        String email = "xhaka@gmail.com";

        User userByEmail = userRepo.getUserByEmail(email);

        assertThat(userByEmail).isNotNull();
    }
}
