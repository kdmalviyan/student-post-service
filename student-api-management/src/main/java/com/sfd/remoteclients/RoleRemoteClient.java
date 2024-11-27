package com.sfd.remoteclients;

import com.sfd.role.Role;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * @author kuldeep
 */
@Service
public class RoleRemoteClient {
    private RestClient restClient;

    @PostConstruct
    public void restClient() {
        restClient = RestClient.builder()
                .baseUrl("http://localhost:8081/roles/")
                .build();
    }

    public Role createRole(Role role) {
        return restClient
                .post()
                .uri("create")
                .body(role)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new UserException(response.getStatusCode(), response.getHeaders());
                })
                .body(Role.class);
    }

    public Role findRoleByName(String name) {
        return null;
    }

    public List<Role> findAll() {
        return null;
    }

    public Role findById(String roleId) {
        return null;
    }
}
