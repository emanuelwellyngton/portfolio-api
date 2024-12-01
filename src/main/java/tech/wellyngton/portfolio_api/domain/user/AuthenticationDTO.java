package tech.wellyngton.portfolio_api.domain.user;

import jakarta.validation.Valid;

public record AuthenticationDTO(
        String user,
        String password) {
}
