package com.jsp.ums.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ums.exception.ApplicationExceptionHandler;
import com.jsp.ums.requestdto.UserRequest;
import com.jsp.ums.responsedto.UserResponse;
import com.jsp.ums.service.UserService;
import com.jsp.ums.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "User", description = "API endpoints that are related to User Entity")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Operation(description = "**Add User -** "
			+ "the API endpoint is used to register the user", 
			responses = {
					@ApiResponse(responseCode = "201", description = "user added", content = {
							@Content(schema = @Schema(implementation = UserResponse.class)) }),
					@ApiResponse(responseCode = "400", description = "failed to add user", content = {
							@Content(schema = @Schema(implementation = ApplicationExceptionHandler.class, 
									                  description = "Method: structure")) }) 
					    })
	
	@PostMapping("/users")
	ResponseEntity<ResponseStructure<UserResponse>> saveData(@RequestBody @Valid UserRequest userRequest) {
		return userService.saveUser(userRequest);
	}
	
	@Operation(description = "**Delete User by Id -** "
			+ "the API endpoint is used to delete the user data based on the Id", responses = {
					@ApiResponse(responseCode = "200", description = "user deleted", content = {
							@Content(schema = @Schema(implementation = UserResponse.class)) }),
					@ApiResponse(responseCode = "404", description = "failed to delete user", content = {
							@Content(schema = @Schema(implementation = ApplicationExceptionHandler.class)) }) })
	@DeleteMapping("/users/{userid}")
	ResponseEntity<ResponseStructure<UserResponse>> deleteUser(@PathVariable int userid) {
		return userService.deleteUser(userid);
	}
	
	@Operation(description = "**Update User by Id -** "
			+ "the API endpoint is used to update the user data based on the Id", responses = {
					@ApiResponse(responseCode = "200", description = "user updated", content = {
							@Content(schema = @Schema(implementation = UserResponse.class)) }),
					@ApiResponse(responseCode = "404", description = "failed to update user", content = {
							@Content(schema = @Schema(implementation = ApplicationExceptionHandler.class)) }) })
	@PutMapping("/users/{userid}")
	ResponseEntity<ResponseStructure<UserResponse>> updateUser(@RequestBody @Valid UserRequest user,@PathVariable int userid) {
		return userService.updateUser(user, userid);
	}
	
	@Operation(description = "**Find all Users -** "
			+ "the API endpoint is used to list of users", responses = {
					@ApiResponse(responseCode = "302", description = "user found", content = {
							@Content(schema = @Schema(implementation = UserResponse.class)) }),
					@ApiResponse(responseCode = "404", description = "no user found", content = {
							@Content(schema = @Schema(implementation = ApplicationExceptionHandler.class)) }) })
	@GetMapping("/users")
	ResponseEntity<ResponseStructure<List<UserResponse>>> findAllUsers(){
		return userService.findAllUsers();
	}
	
	@Operation(description = "**Find User by Id -** "
			+ "the API endpoint is used to fetch the user data based on the Id", responses = {
					@ApiResponse(responseCode = "302", description = "user found", content = {
							@Content(schema = @Schema(implementation = UserResponse.class)) }),
					@ApiResponse(responseCode = "404", description = "user not found", content = {
							@Content(schema = @Schema(implementation = ApplicationExceptionHandler.class)) }) })
	@GetMapping("/users/{userid}")
	ResponseEntity<ResponseStructure<UserResponse>> findById(@PathVariable int userid) {
		return userService.findById(userid);
	}	
}
