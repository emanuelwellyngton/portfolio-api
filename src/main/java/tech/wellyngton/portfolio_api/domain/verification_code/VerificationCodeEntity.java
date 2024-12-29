package tech.wellyngton.portfolio_api.domain.verification_code;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.wellyngton.portfolio_api.domain.user.UserEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_verification_code")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VerificationCodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "CHAR(36)", unique = true, nullable = false)
    private String code;

    @ManyToOne
    @JoinColumn(name = "user")
    private UserEntity user;

    private LocalDateTime expiresAt;

    @Setter
    private boolean valid;

    public VerificationCodeEntity(UserEntity user) {
        this.code = UUID.randomUUID().toString();
        this.user = user;
        expiresAt = LocalDateTime
                .now()
                .plusHours(2);
        this.valid = true;
    }
}
