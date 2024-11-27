package com.sfd.security;

import com.sfd.common.Constants;
import com.sfd.students.Student;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Log4j2
@Component
public class JwtTokenGenerator {
    @Value("${security.token.expires}")
    private int tokenExpiryInMinutes;
    @Value("${security.secret.salt}")
    private String signingKey;

    public String generate(Student student, boolean isRefreshToken) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Instant now = Instant.now();
        log.info("Generating security tokens");
        return isRefreshToken ? Jwts.builder()
                .claim("isRefreshToken", true)
                .signWith(SignatureAlgorithm.HS512, signingKey)
                .setSubject(student.getUsername())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(tokenExpiryInMinutes, ChronoUnit.MINUTES)))
                .compact()

                : Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, signingKey)
                .claim(Constants.USERNAME, student.getUsername())
                .claim(Constants.ROLE, student.getAuthorities())
                .claim("isRefreshToken", false)
                .setSubject(student.getUsername())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(tokenExpiryInMinutes, ChronoUnit.MINUTES)))
                .compact();
    }
}
