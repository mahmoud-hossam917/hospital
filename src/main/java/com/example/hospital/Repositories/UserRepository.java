package com.example.hospital.Repositories;

import com.example.hospital.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    public Optional<User> findUsersByEmailAndPassword(String email, String password);
    public Optional<User>findUsersByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);
}
