package com.jsp.ums.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jsp.ums.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByUsername(String username);
}
