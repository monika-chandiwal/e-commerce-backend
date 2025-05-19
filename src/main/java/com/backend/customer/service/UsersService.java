package com.backend.customer.service;

import com.backend.customer.model.Users;

import java.util.List;


public interface UsersService {
    public Users saveUser(Users users);
    public List<Users> getAllUsers();
    public Users checkUser(Users users);
    public void  deleteAllUsers();
    public  boolean userExist(Users users);
}
