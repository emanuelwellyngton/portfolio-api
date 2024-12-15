package tech.wellyngton.portfolio_api.domain.user;

public record AuthenticationDTO(
        String user,
        String password) {
}
