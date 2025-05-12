package com.backend.service;

import com.backend.model.Users;
import com.backend.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Service
public class UserServiceImp implements UsersService{
    @Autowired
    private UsersRepo usersRepo;

    @Override
    public Users saveUser(Users user) {
        try {

            System.out.println("Received user: " + user);
            return usersRepo.save(user);
        } catch (DataIntegrityViolationException e) {
            // Handle the duplicate entry (unique constraint violation)
            throw new RuntimeException("Email already exists: " + user.getEmail());
        }
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
        Users existingUser = usersRepo.findByEmailAndPassword(users.getEmail(),users.getPassword());
        System.out.println("user exist "+ existingUser );
        return existingUser;
    }

    @Override
    public void deleteAllUsers() {
        usersRepo.deleteAll();
    }

    @Override
    public boolean userExist(Users users) {
        return usersRepo.findByEmail(users.getEmail()) != null;
    }

}