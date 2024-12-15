package tech.wellyngton.portfolio_api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tech.wellyngton.portfolio_api.domain.user.UserEntity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.secret_key}")
    private String secretKey;

    public String getToken(UserEntity user) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withIssuer("Portfolio API")
                .withSubject(user.getLogin())
                .withExpiresAt(getExpirationDate())
                .sign(algorithm);
    }

    public String getSubject(String jwtToken) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.require(algorithm)
                .withIssuer("Portfolio API")
                .build()
                .verify(jwtToken)
                .getSubject();
    }

    public Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
