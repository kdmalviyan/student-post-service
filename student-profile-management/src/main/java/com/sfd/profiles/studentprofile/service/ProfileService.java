package com.sfd.profiles.studentprofile.service;

import com.sfd.profiles.studentprofile.dtos.CreateProfileRequest;
import com.sfd.profiles.studentprofile.entity.Profile;

import java.util.List;

/**
 * @author kuldeep
 */
public interface ProfileService {
    Profile createProfile(CreateProfileRequest createProfileRequest);

    List<Profile> findAll();

    Profile findByUsername(String username);
}
