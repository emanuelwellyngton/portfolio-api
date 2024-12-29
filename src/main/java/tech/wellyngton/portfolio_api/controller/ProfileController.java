package tech.wellyngton.portfolio_api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import tech.wellyngton.portfolio_api.domain.profile.ProfileDTO;
import tech.wellyngton.portfolio_api.domain.profile.ProfileEntity;
import tech.wellyngton.portfolio_api.domain.profile.ProfileService;
import tech.wellyngton.portfolio_api.domain.user.UserService;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity<ProfileDTO> createNewProfile(@Valid @RequestBody ProfileDTO dto, UriComponentsBuilder builder) {
        var login = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userService.getUserByLogin(login);
        var profile = profileService.register(new ProfileEntity(dto), user);
        var uri = builder.path("/profile?id={profileId}")
                .buildAndExpand(profile.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProfileDTO(profile));
    }

    @GetMapping
    public ResponseEntity<ProfileDTO> getProfile(@RequestParam long id) {
        var profile = profileService.getProfile(id);
        return ResponseEntity.ok().body(new ProfileDTO(profile));
    }

}
