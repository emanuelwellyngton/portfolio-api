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
import tech.wellyngton.portfolio_api.domain.user.UserDTO;
import tech.wellyngton.portfolio_api.domain.user.UserEntity;
import tech.wellyngton.portfolio_api.domain.user.UserRepository;
import tech.wellyngton.portfolio_api.infra.security.TokenService;

@RestController
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto) {
        var token = new UsernamePasswordAuthenticationToken(dto.user(), dto.password());
        var authentication = authenticationManager.authenticate(token);
        return ResponseEntity.ok(tokenService.getToken((UserEntity) authentication.getPrincipal()));
    }

    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody @Valid UserDTO dto) {
        UserEntity newUser = new UserEntity();
        newUser.setLogin(dto.login());
        newUser.setPassword(encoder.encode(dto.password()));
        newUser.setFirstName(dto.fristName());
        newUser.setLastName(dto.lastName());
        userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
