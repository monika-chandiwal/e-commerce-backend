package com.backend.repository;

import com.backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UsersRepo extends JpaRepository<Users,Integer> {
    Users findByEmail(String email);


}
