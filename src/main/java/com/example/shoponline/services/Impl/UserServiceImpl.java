package com.example.shoponline.services.Impl;

import com.example.shoponline.exception.UserNotFoundException;
import com.example.shoponline.models.Role;
import com.example.shoponline.models.User;
import com.example.shoponline.repositories.RoleRepository;
import com.example.shoponline.repositories.UserRepository;
import com.example.shoponline.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public List<User> getListUsers() {
        return userRepo.findAll();
    }

    @Override
    public void saveUser(User user) {
        encodePass(user);
        userRepo.save(user);
    }

    @Override
    public List<Role> getListRoles() {
        return roleRepo.findAll();
    }

    @Override
    public void encodePass(User user) {
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
    }

    @Override
    public boolean isEmailUnique(Integer id, String email) {
        User userByEmail = userRepo.getUserByEmail(email);
        if(userByEmail == null) return true;

        boolean isCreatingNew  = (id==null);

        if(isCreatingNew)
        {
            if(userByEmail != null)
                return false;
        }else {
            if(userByEmail.getId() != id)
                return false;
        }
        return true;
    }

    @Override
    public User getUserById(Integer id) throws UserNotFoundException{
        try{
            return userRepo.findById(id).get();
        }catch(NoSuchElementException ex){
            throw new UserNotFoundException("Could not find any user with ID = " + id);
        }

    }
}
