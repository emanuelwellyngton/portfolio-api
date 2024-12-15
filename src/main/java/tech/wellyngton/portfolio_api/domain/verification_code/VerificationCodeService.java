package tech.wellyngton.portfolio_api.domain.verification_code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.wellyngton.portfolio_api.domain.user.UserEntity;

import java.time.LocalDateTime;

@Service
public class VerificationCodeService {
    @Autowired
    private VerificationCodeRepository repository;

    public VerificationCodeEntity codeRegister(UserEntity userEntity) {
        VerificationCodeEntity verificationCode =
                new VerificationCodeEntity(userEntity);
        return repository.save(verificationCode);
    }

    public VerificationCodeEntity getCode(String code) {
        return repository.findByCode(code);
    }

    public boolean isCodeValid(String code){
        try {
            var codeEntity = getCode(code);
            var notExpired = codeEntity.getExpiresAt().isAfter(LocalDateTime.now());
            var valid = codeEntity.isValid();
            return notExpired & valid;
        } catch (NullPointerException ex) {
            return false;
        }
    }
}
