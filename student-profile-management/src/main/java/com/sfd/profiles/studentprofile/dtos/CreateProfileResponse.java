package com.sfd.profiles.studentprofile.dtos;

import lombok.Builder;
import lombok.Data;

/**
 * @author kuldeep
 */
@Data
@Builder
public class CreateProfileResponse {
    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private String message;
}
