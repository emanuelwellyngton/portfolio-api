package tech.wellyngton.portfolio_api.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "users-verification-code")
@AllArgsConstructor
@NoArgsConstructor
public class UserVerificationCodeEntity {
    @Id
    private String code;
    @ManyToOne
    private UserEntity user;
    private Date expiresAt;
    private boolean valid;
}
