package tech.wellyngton.portfolio_api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.wellyngton.portfolio_api.domain.user.AuthenticationDTO;
import tech.wellyngton.portfolio_api.domain.user.UserEntity;
import tech.wellyngton.portfolio_api.domain.user.UserRepository;

@RestController
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto) {
        var token = new UsernamePasswordAuthenticationToken(dto.user(), dto.password());
        authenticationManager.authenticate(token);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody @Valid AuthenticationDTO dto) {
        UserEntity newUser = new UserEntity();
        newUser.setLogin(dto.user());
        newUser.setPassword(encoder.encode(dto.password()));
        repository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
