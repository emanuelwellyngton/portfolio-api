package tech.wellyngton.portfolio_api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;
import tech.wellyngton.portfolio_api.domain.user.UserEntity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    public String getToken(UserEntity user) {
        Algorithm algorithm = Algorithm.HMAC256("12345678");
        return JWT.create()
                .withIssuer("Portfolio API")
                .withSubject(user.getLogin())
                .withExpiresAt(getExpirationDate())
                .sign(algorithm);
    }

    public Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
