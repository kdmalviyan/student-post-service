package com.sfd.remoteclients;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;

/**
 * @author kuldeep
 */
public class UserException extends RuntimeException {
    public UserException(HttpStatusCode statusCode, HttpHeaders headers) {
        super("Request failed: status code: " + statusCode);
    }
}
