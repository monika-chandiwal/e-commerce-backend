package com.backend.service;

import com.backend.model.Users;
import com.backend.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Service
public class UserServiceImp implements UsersService{
    @Autowired
    private UsersRepo usersRepo;

    @Override
    public Users saveUser(Users user) {
        System.out.println("Received user: " + user);

        return usersRepo.save(user);
    }

    @Override
    public List<Users> getAllUsers() {
        return usersRepo.findAll();
    }

    /**
     * @param users
     * @return
     */
    @Override
    public Users checkUser(Users users) {
        Users existingUser = usersRepo.findByEmail(users.getEmail());
        System.out.println("user exist "+ existingUser );
        if (existingUser != null && existingUser.getPassword().equals(users.getPassword())) {
            return existingUser;
        }
        return null;
    }

    @Override
    public void deleteAllUsers() {
        usersRepo.deleteAll();
    }
}
