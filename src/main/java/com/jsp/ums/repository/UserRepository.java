package com.jsp.ums.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jsp.ums.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
