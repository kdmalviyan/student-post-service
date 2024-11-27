package com.sfd.profiles.studentprofile.dtos;

import lombok.Data;

/**
 * @author kuldeep
 */
@Data
public class CreateProfileRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;
    private String username;
    private String password;
    private String roleName;
}
