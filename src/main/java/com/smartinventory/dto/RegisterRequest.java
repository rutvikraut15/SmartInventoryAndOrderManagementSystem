package com.smartinventory.dto;

import com.smartinventory.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    private String fullName;

    private String email;

    private String password;

    private Role role;

}