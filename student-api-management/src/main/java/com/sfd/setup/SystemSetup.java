package com.sfd.setup;

import com.sfd.role.Role;
import com.sfd.role.RoleService;
import com.sfd.students.CreateStudentRequest;
import com.sfd.students.StudentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Objects;

/**
 * @author kuldeep
 */
@Configuration
public class SystemSetup {

    public SystemSetup(final RoleService roleService,
                       final StudentService studentService,
                       final PasswordEncoder passwordEncoder,
                       @Value("${system.setup.roles}") List<String> roles,
                       @Value("${system.setup.users}") List<String> users) {
        createRoles(roles, roleService);
        createAdminUser(users, studentService, passwordEncoder);
    }

    private void createAdminUser(final List<String> users,
                                 final StudentService studentService,
                                 final PasswordEncoder passwordEncoder) {
        users.stream()
                .filter(username -> Objects.isNull(studentService.findByUsername(username)))
                .forEach(username -> {
                    CreateStudentRequest createStudentRequest = new CreateStudentRequest();
                    createStudentRequest.setFirstName("Super Admin");
                    createStudentRequest.setUsername(username);
                    createStudentRequest.setPassword(passwordEncoder.encode("Password@1"));
                    createStudentRequest.setRoleName("ROLE_SUPERADMIN");
                    studentService.createUser(createStudentRequest);
                });
    }

    private void createRoles(final List<String> roles, final RoleService roleService) {
        roles.stream()
                .filter(roleName -> Objects.isNull(roleService.findByName(roleName)))
                .forEach(roleName -> {
                    Role role = new Role();
                    role.setName(roleName);
                    roleService.createRole(role);
                });
    }
}
