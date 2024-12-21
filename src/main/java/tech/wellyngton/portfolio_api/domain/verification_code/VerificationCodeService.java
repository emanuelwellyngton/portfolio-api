package tech.wellyngton.portfolio_api.domain.verification_code;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.wellyngton.portfolio_api.domain.user.UserEntity;
import tech.wellyngton.portfolio_api.infra.exception.ExpiredCodeException;
import tech.wellyngton.portfolio_api.infra.exception.InvalidCodeException;

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
        var entity = repository.findByCode(code);
        if(entity == null) {
            throw new EntityNotFoundException();
        } else {
            return entity;
        }
    }

    public boolean isCodeValid(String code) throws InvalidCodeException, ExpiredCodeException {
        var codeEntity = getCode(code);
        var expired = codeEntity.getExpiresAt().isBefore(LocalDateTime.now());

        if (!codeEntity.isValid()) {
            throw new InvalidCodeException();
        } else if (expired) {
            throw new ExpiredCodeException();
        }

        return true;
    }
}
