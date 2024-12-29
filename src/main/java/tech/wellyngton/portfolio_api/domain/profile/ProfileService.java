package tech.wellyngton.portfolio_api.domain.profile;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tech.wellyngton.portfolio_api.domain.user.UserEntity;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository repository;

    public ProfileEntity register(ProfileEntity entity, UserEntity user) {
        entity.setUser(user);
        return repository.save(entity);
    }

    public ProfileEntity getProfile(long id) {
        var entity = repository.findById(id);

        if(entity.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return entity.get();
    }

}
