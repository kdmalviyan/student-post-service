package com.sfd.students;

import lombok.Data;

/**
 * @author kuldeep
 */
@Data
public class CreateStudentRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;
    private String username;
    private String password;
    private String roleName;
}
