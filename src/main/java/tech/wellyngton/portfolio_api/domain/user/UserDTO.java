package tech.wellyngton.portfolio_api.domain.user;

public record UserDTO(
        String login,
        String password,
        String fristName,
        String lastName
        ) {
}
