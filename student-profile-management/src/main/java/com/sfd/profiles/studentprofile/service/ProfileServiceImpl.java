package com.sfd.profiles.studentprofile.service;

import com.sfd.profiles.role.Role;
import com.sfd.profiles.role.RoleService;
import com.sfd.profiles.exceptions.ProfileException;
import com.sfd.profiles.studentprofile.dtos.CreateProfileRequest;
import com.sfd.profiles.studentprofile.entity.Profile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author kuldeep
 */
@Service
@Slf4j
public record ProfileServiceImpl(MongoTemplate mongoTemplate,
                                 RoleService roleService) implements ProfileService {

    @Override
    public Profile createProfile(CreateProfileRequest createProfileRequest) {
        if(Objects.nonNull(findByUsername(createProfileRequest.getUsername()))){
            throw new ProfileException("Username is already taken, please try something different.");
        }
        return save(createProfileRequest);
    }

    private Profile save(CreateProfileRequest createProfileRequest) {
        Role role = roleService.findByName(createProfileRequest.getRoleName());
        Profile profile = new Profile();
        profile.setUsername(createProfileRequest.getUsername());
        profile.setPassword(createProfileRequest.getPassword());
        profile.setEmail(createProfileRequest.getEmail());
        profile.setPhone(createProfileRequest.getPhone());
        profile.setFirstName(createProfileRequest.getFirstName());
        profile.setMiddleName(createProfileRequest.getMiddleName());
        profile.setLastName(createProfileRequest.getLastName());
        profile.setRole(role);
        profile = mongoTemplate.save(profile);
        return profile;
    }

    @Override
    public List<Profile> findAll() {
        return mongoTemplate().findAll(Profile.class);
    }

    @Override
    public Profile findByUsername(String username) {
        Criteria criteria = Criteria.where("username").is(username);
        Query query = Query.query(criteria);
        return mongoTemplate.findOne(query, Profile.class);
    }
}
