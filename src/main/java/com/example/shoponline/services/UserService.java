package com.example.shoponline.services;

import com.example.shoponline.exception.UserNotFoundException;
import com.example.shoponline.models.Role;
import com.example.shoponline.models.User;

import java.util.List;

public interface UserService {

    public List<User> getListUsers();

    public void saveUser(User user);

    public List<Role> getListRoles();

    public void encodePass(User user);

    public boolean isEmailUnique(Integer id, String email);

    public User getUserById(Integer id) throws UserNotFoundException;

    public void deleteUser(Integer id) throws UserNotFoundException;
}
