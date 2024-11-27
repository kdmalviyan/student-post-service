package com.sfd.profiles.studentprofile.controller;

import com.sfd.profiles.studentprofile.service.ProfileServiceImpl;
import com.sfd.profiles.studentprofile.dtos.CreateProfileRequest;
import com.sfd.profiles.studentprofile.entity.Profile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kuldeep
 */
@RestController
@RequestMapping("profiles")
@Slf4j
public record ProfileController(ProfileServiceImpl profileServiceImpl) {

    @PostMapping("create")
    public ResponseEntity<Profile> create(@RequestBody CreateProfileRequest createProfileRequest) {
        return ResponseEntity.ok(profileServiceImpl.createProfile(createProfileRequest));
    }

    @GetMapping("{username}")
    public ResponseEntity<Profile> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(profileServiceImpl.findByUsername(username));
    }

    @GetMapping("")
    public ResponseEntity<List<Profile>> getAllProfiles() {
        return ResponseEntity.ok(profileServiceImpl.findAll());
    }
}
