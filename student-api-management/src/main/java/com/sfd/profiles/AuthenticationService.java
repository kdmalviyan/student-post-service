package com.sfd.profiles;

import com.sfd.role.RoleService;
import com.sfd.security.InvalidCredentialsException;
import com.sfd.security.JwtTokenGenerator;
import com.sfd.students.Student;
import com.sfd.students.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Objects;

/**
 * @author kuldeep
 */
@Service
@Slf4j
public record AuthenticationService(StudentService studentService,
                                    JwtTokenGenerator jwtTokenGenerator,
                                    PasswordEncoder passwordEncoder,
                                    RoleService roleService) {
    public AuthResponse login(final LoginRequest loginRequest) {

        UserDetails userDetails = studentService.loadUserByUsername(loginRequest.getUsername());
        if(Objects.isNull(userDetails)) {
            throw new InvalidCredentialsException("Username or Password is incorrect.", HttpStatus.UNAUTHORIZED.value());
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        try {
            String authToken = jwtTokenGenerator.generate((Student) userDetails, false);
            return AuthResponse.builder()
                    .token(authToken)
                    .role(((Student) userDetails).getRole())
                    .build();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}
