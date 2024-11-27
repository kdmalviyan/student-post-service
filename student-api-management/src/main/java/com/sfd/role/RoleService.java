package com.sfd.role;

import com.sfd.remoteclients.RoleRemoteClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author kuldeep
 */

@Service
public record RoleService(RoleRemoteClient roleRemoteClient) {
    public Role createRole(Role role) {
        validateRoleName(role);
        Role existingRole = findByName(role.getName());
        if(Objects.isNull(existingRole)){
            return roleRemoteClient.createRole(role);
        } else {
            return existingRole;
        }
    }

    public Role findByName(String name) {
        return roleRemoteClient.findRoleByName(name);
    }

    public List<Role> findAll() {
        return roleRemoteClient.findAll();
    }

    public Role findById(String roleId) {
        return roleRemoteClient.findById(roleId);
    }


    private void validateRoleName(Role role) {
        if(Objects.nonNull(role) && Objects.nonNull(role.getName()) && !Objects.equals(role.getName().trim(), "")) {
            if(!role.getName().toUpperCase().startsWith("ROLE_")) {
                role.setName("ROLE_" + role.getName().toUpperCase());
            } else {
                role.setName(role.getName().toUpperCase());
            }
        } else {
            throw new RoleException("Invalid role name", HttpStatus.NOT_ACCEPTABLE.value());
        }
    }
}
