package com.example.shoponline.repositories;

import com.example.shoponline.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("select u from User u where u.email=:email")
    public User getUserByEmail(@Param("email") String email);

    public Long countById(Integer id);
}
