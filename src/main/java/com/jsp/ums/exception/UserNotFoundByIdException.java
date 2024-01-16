package com.jsp.ums.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserNotFoundByIdException extends RuntimeException{
	private String message;
}
