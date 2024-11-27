package com.sfd.profiles;

import com.sfd.role.Role;
import lombok.Builder;
import lombok.Data;

/**
 * @author kuldeep
 */
@Data
@Builder
public class AuthResponse {
    private String token;
    private Role role;
}
