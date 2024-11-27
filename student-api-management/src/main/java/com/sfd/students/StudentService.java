package com.sfd.students;

import com.sfd.remoteclients.ProfileRemoteClient;
import com.sfd.role.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author kuldeep
 */
@Service
@Slf4j
public record StudentService(MongoTemplate mongoTemplate,
                             RoleService roleService,
                             ProfileRemoteClient profileRemoteClient,
                             PasswordEncoder passwordEncoder) implements UserDetailsService {
    public Student createUser(CreateStudentRequest createStudentRequest) {
        createStudentRequest.setRoleName(Objects.nonNull(createStudentRequest.getRoleName())
                ? createStudentRequest.getRoleName() : "ROLE_STUDENT");
        createStudentRequest.setPassword(passwordEncoder.encode(createStudentRequest.getPassword()));
         return profileRemoteClient.createUser(createStudentRequest);
    }

    public List<Student> findAll() {
        return profileRemoteClient.findAll(Student.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username);
    }

    public Student findByUsername(String username) {
        return profileRemoteClient.findByUsername(username);
    }
}
