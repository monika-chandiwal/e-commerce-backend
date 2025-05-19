package com.backend.customer.repository;

import com.backend.customer.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users,Integer> {
    Users findByEmail(String email);
    Users findByEmailAndPassword(String email,String password);

}
