package com.example.shoponline.User;

import com.example.shoponline.models.Role;
import com.example.shoponline.repositories.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoleRepoTests {
    @Autowired
    private RoleRepository roleRepo;

    @Test
    public void testCreateRole()
    {
        Role roleAdmin = new Role("User","Do something!");
        Role savedRole = roleRepo.save(roleAdmin);

        assertThat(savedRole.getId()).isGreaterThan(0);

    }

    @Test
    public void testCreateRestRole()
    {
        Role roleSales = new Role("Sales","manage product price, customers, shipping!");
        Role roleEditor = new Role("Editor","manage categories, brands, customers, products!");
        Role roleShipper = new Role("Shipper","view products, orders, update order status!");
        Role roleAssistant = new Role("Assistant","manage questions and reviews!");

        roleRepo.saveAll(List.of(roleSales,roleEditor,roleShipper,roleAssistant));
    }
}
