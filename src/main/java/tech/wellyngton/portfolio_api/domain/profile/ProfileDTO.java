package tech.wellyngton.portfolio_api.domain.profile;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record ProfileDTO(
        long id,

        @NotNull
        @Length(min=3, max=50)
        String firstName,

        @NotNull
        @Length(min = 3, max = 50)
        String lastName,

        long userId,

        @Length(max = 255)
        String bio,

        @Length(max = 65535)
        String aboutMe,

        @Length(max = 100)
        String localization,

        @Length(max = 100)
        String role,

        @Length(max = 255)
        String avatar,

        boolean showBlog
) {
        public ProfileDTO(ProfileEntity entity) {
                this(entity.getId(), entity.getFirstName(), entity.getLastName(),
                        entity.getId(), entity.getBio(), entity.getAboutMe(),
                        entity.getLocalization(), entity.getRole(), entity.getAvatar(),
                        entity.isShowBlog());
        }
}
