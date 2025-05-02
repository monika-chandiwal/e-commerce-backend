package com.backend.repository;

import com.backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UsersRepo extends JpaRepository<Users,Integer> {
    Users findByEmail(String email);

}
