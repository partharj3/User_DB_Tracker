package com.jsp.ums.requestdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
	
//	@NotEmpty(message="Username should not be either null or blank")

	@Pattern(regexp = "^[A-Z][a-z]*(?: [A-Z][a-z]*)*$")
	@NotBlank(message = "Username should not be BLANK")
	@NotNull(message = "Username should not be NULL")
	private String username;
	
	@NotBlank(message="Email should not be BLANK")
	@Email(regexp = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}", message = "invalid email ") // regexp - regex pattern
	private String useremail;
	
	@NotBlank(message = "Password is required")
	@NotNull(message = "Password is required")
	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must"
			+ " contain at least one upper case, one lower case, one number, one special character")
	private String userpassword;
}
