package tech.wellyngton.portfolio_api.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

public record UserDTO(
        @NotNull
        @Email(message = "invalid_email_format")
        String login,

        @NotNull
        @Length(min = 8, message = "password_min")
        String password
        ) {
}
