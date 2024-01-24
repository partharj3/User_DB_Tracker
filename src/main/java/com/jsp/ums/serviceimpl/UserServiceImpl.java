package com.jsp.ums.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jsp.ums.entity.User;
import com.jsp.ums.exception.UserNotFoundByIdException;
import com.jsp.ums.repository.UserRepository;
import com.jsp.ums.requestdto.UserRequest;
import com.jsp.ums.responsedto.UserResponse;
import com.jsp.ums.service.UserService;
import com.jsp.ums.util.ResponseStructure;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	public PasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ResponseStructure<UserResponse> structure;
	
	private User mapToUser(UserRequest request) {
		return User.builder()
				   .username(request.getUsername())
				   .useremail(request.getUseremail())
				   .userpassword(encoder.encode(request.getUserpassword()))
				   .build();
	}
	
	private UserResponse mapToUserResponse(User user) {
		return UserResponse.builder()
						   .userId(user.getUserId())
				           .useremail(user.getUseremail())
				           .username(user.getUsername())
				           .build();
	}
	
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userRequest) {
		User user = mapToUser(userRequest);
		User saved = userRepo.save(user);
	
		UserResponse response = mapToUserResponse(saved);
		
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setMessage("User saved Successfully");
		structure.setData(response);
		
		return new ResponseEntity<ResponseStructure<UserResponse>>(structure,HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(UserRequest request, int userid) {
		/** 1st Approach **/
//		User obj=userRepo.findById(userid).orElseThrow(()-> new RuntimeException());
//		user.setUserId(obj.getUserId());
//		
//		userRepo.save(user);
		
//		UserResponse response = mapToUserResponse(userObj);
//		structure.setStatus(HttpStatus.OK.value());
//		structure.setMessage("User updated Successfully");
//		structure.setData(response);
//		
//		return new ResponseEntity<ResponseStructure<UserResponse>>(structure,HttpStatus.OK);
		
		/** 2nd Approach **/
		User user = mapToUser(request);
		User userObj = userRepo.findById(userid).map(u->{
													user.setUserId(u.getUserId());
													return userRepo.save(user);
												  })
												.orElseThrow(()->new UserNotFoundByIdException("Failed to UPDATE the record"));
		
		UserResponse response = mapToUserResponse(userObj);
		
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("User updated Successfully");
		structure.setData(response);
		
		return new ResponseEntity<ResponseStructure<UserResponse>>(structure,HttpStatus.OK);		
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userid) {
		User user = userRepo.findById(userid).orElseThrow(()-> new UserNotFoundByIdException("Failed to DELETE the record"));
		userRepo.delete(user);
		
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("User Data Deleted Successfully!!");
		structure.setData(mapToUserResponse(user));
		
		return new ResponseEntity<ResponseStructure<UserResponse>>(structure,HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ResponseStructure<List<UserResponse>>> findAllUsers() {
		List<User> list = userRepo.findAll();
		if(!list.isEmpty()) {
			List<UserResponse> result = new ArrayList<>();
			
			for(User user : list) { result.add(mapToUserResponse(user));}
			
			ResponseStructure<List<UserResponse>> structure = new ResponseStructure<>();
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("User Data Found");
			structure.setData(result);
			return new ResponseEntity<ResponseStructure<List<UserResponse>>>(structure,HttpStatus.FOUND);
		}
		else
			throw new RuntimeException();
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> findById(int userid) {
		User user = userRepo.findById(userid).orElseThrow(()-> new UserNotFoundByIdException("Failed to FETCH the record"));
		
		structure.setStatus(HttpStatus.FOUND.value());
		structure.setMessage("User Data Found");
		structure.setData(mapToUserResponse(user));
		
		return new ResponseEntity<ResponseStructure<UserResponse>>(structure,HttpStatus.FOUND);
	}

}
