package com.jsp.ums.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.ums.requestdto.UserRequest;
import com.jsp.ums.responsedto.UserResponse;
import com.jsp.ums.util.ResponseStructure;

public interface UserService {

	ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userRequest);
	ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userid);
	ResponseEntity<ResponseStructure<UserResponse>> updateUser(UserRequest userRequest, int userid);
	ResponseEntity<ResponseStructure<List<UserResponse>>> findAllUsers();
	ResponseEntity<ResponseStructure<UserResponse>> findById(int userid);
}
