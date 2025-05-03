package com.backend.service;

import com.backend.model.Users;

import java.util.List;


public interface UsersService {
    public Users saveUser(Users users);
    public List<Users> getAllUsers();
    public Users checkUser(Users users);
    public void  deleteAllUsers();
}
