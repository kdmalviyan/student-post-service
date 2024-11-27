package com.sfd.profiles.studentprofile.entity;

import com.sfd.profiles.role.Role;
import com.sfd.profiles.studentprofile.dtos.CreateProfileRequest;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

/**
 * @author kuldeep
 */

@Document
@Data
public class Profile {
    @Id
    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;
    private String username;
    private String password;

    @DocumentReference
    private Role role;

    public static Profile getInstance(CreateProfileRequest createProfileRequest, Role role) {
        Profile profile = new Profile();
        profile.setFirstName(createProfileRequest.getFirstName());
        profile.setMiddleName(createProfileRequest.getMiddleName());
        profile.setLastName(createProfileRequest.getLastName());
        profile.setEmail(createProfileRequest.getEmail());
        profile.setPhone(createProfileRequest.getPhone());
        profile.setUsername(createProfileRequest.getUsername());
        profile.setPassword(createProfileRequest.getPassword());
        profile.setRole(role);
        return profile;
    }
}
