package tech.wellyngton.portfolio_api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.wellyngton.portfolio_api.domain.user.*;
import tech.wellyngton.portfolio_api.domain.verification_code.VerificationCodeEntity;
import tech.wellyngton.portfolio_api.domain.verification_code.VerificationCodeService;
import tech.wellyngton.portfolio_api.infra.security.TokenDTO;
import tech.wellyngton.portfolio_api.infra.security.TokenService;

@RestController
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private VerificationCodeService verificationCodeService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto) {
        var token = new UsernamePasswordAuthenticationToken(dto.user(), dto.password());
        var authentication = authenticationManager.authenticate(token);
        var jwtToken = tokenService.getToken((UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenDTO(jwtToken));
    }

    @PostMapping("/registration")
    @Transactional
    @PreAuthorize("@userService.isEmailAlreadyRegistered(#dto.login())")
    public ResponseEntity registration(@RequestBody @Valid UserDTO dto) {
        UserEntity user = userService.registerUser(dto);
        verificationCodeService.codeRegister(user);
        return ResponseEntity.noContent().build();
    }
}
