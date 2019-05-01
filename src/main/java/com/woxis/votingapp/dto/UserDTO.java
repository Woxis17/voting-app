package com.woxis.votingapp.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserDTO {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private String firstName;
    private String lastName;
}
