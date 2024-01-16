package com.jsp.ums.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDataNotFoundException extends RuntimeException{
	private String message;
}
