package tech.wellyngton.portfolio_api.domain.user;

import org.springframework.data.repository.Repository;

public interface UserVerificationCodeRepository extends Repository<UserRepository, String> {
}
