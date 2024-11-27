package com.sfd.remoteclients;

import com.sfd.students.CreateStudentRequest;
import com.sfd.students.Student;
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
public class ProfileRemoteClient {
    private RestClient restClient;

    @PostConstruct
    public void restClient() {
        restClient = RestClient.builder()
                .baseUrl("http://localhost:8081/profiles")
                .build();
    }

    public Student createUser(CreateStudentRequest createStudentRequest) {
        Student student = restClient
                .post()
                .uri("/create")
                .body(createStudentRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(Student.class);
        return student;
    }

    public Student findByUsername(String username) {
        return restClient
                .get()
                .uri("/" + username)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new UserException(response.getStatusCode(), response.getHeaders());
                })
                .body(Student.class);
    }

    public List<Student> findAll(Class<Student> userInfoClass) {
        return restClient
                .get()
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new UserException(response.getStatusCode(), response.getHeaders());
                })
                .body(List.class);
    }
}
