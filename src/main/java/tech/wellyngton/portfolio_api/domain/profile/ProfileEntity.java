package tech.wellyngton.portfolio_api.domain.profile;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.wellyngton.portfolio_api.domain.user.UserEntity;

@Entity
@Table(name = "profiles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @OneToOne
    @JoinColumn(name = "user")
    private UserEntity user;

    private String firstName;
    private String lastName;

    @Setter
    private String bio;

    @Setter
    private String aboutMe;

    @Setter
    private String localization;

    @Setter
    private String role;

    @Setter
    private String avatar;

    @Setter
    private boolean showBlog;

    public ProfileEntity(ProfileDTO dto) {
        firstName = dto.firstName();
        lastName = dto.lastName();
        bio = dto.bio();
        aboutMe = dto.aboutMe();
        localization = dto.localization();
        role = dto.role();
        avatar = dto.avatar();
        showBlog = dto.showBlog();
    }
}
