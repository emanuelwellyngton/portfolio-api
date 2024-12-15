package tech.wellyngton.portfolio_api.domain.verification_code;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeRepository extends JpaRepository<VerificationCodeEntity, String> {
    VerificationCodeEntity findByCode(String code);
}
