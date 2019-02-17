package com.arjunsk.springsecurity.security.model;

import lombok.Data;

@Data
public class User {

	private String userName;
    private long id;
    private String role;

}
