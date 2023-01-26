package com.example.adminStudy.repository;

import com.example.adminStudy.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);
}
