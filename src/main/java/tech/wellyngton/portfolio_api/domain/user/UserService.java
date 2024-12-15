package tech.wellyngton.portfolio_api.domain.user;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.wellyngton.portfolio_api.infra.exception.EmailNotConfirmedExeption;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public UserEntity registerUser(UserDTO dto) {
        UserEntity newUser = new UserEntity(dto.login(), encoder.encode(dto.password()), dto.fristName(),
                dto.lastName());
        return repository.save(newUser);
    }

    public UserEntity getUserByLogin(String login) {
        var user = (UserEntity) repository.findByLogin(login);
        if(user != null) {
            return (UserEntity) repository.findByLogin(login);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public boolean isUserConfirmed(String user) throws EmailNotConfirmedExeption {
        var userEntity = getUserByLogin(user);
        if(userEntity.isConfirmed()) {
            return true;
        } else {
            throw new EmailNotConfirmedExeption();
        }
    }

    public boolean isEmailAlreadyRegistered(String login) {
        return getUserByLogin(login) != null;
    }
}
