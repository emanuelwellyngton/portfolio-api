package tech.wellyngton.portfolio_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.wellyngton.portfolio_api.domain.user.LoginDTO;
import tech.wellyngton.portfolio_api.domain.user.UserEntity;
import tech.wellyngton.portfolio_api.domain.verification_code.VerificationCodeEntity;
import tech.wellyngton.portfolio_api.domain.verification_code.VerificationCodeRepository;
import tech.wellyngton.portfolio_api.domain.verification_code.VerificationCodeService;
import tech.wellyngton.portfolio_api.domain.user.UserService;

@RestController
@RequestMapping("/verification-code")
public class VerificationCodeCotroller {
    @Autowired
    private VerificationCodeService codeService;

    @Autowired
    private UserService userService;
    @Autowired
    private VerificationCodeRepository verificationCodeRepository;
    @Autowired
    private VerificationCodeService verificationCodeService;

    @PostMapping
    @Transactional
    //TODO Verificar que usuário não está confirmado
    public VerificationCodeEntity registration(@RequestBody LoginDTO dto){
        //TODO Definir código existente do usuário como inválido
        UserEntity user = userService.getUserByLogin(dto.login());
        return codeService.codeRegister(user);
    }

    @PostMapping("/{code}")
    @Transactional
    @PreAuthorize("@verificationCodeService.isCodeValid(#code)")
    public ResponseEntity verifyCode(@PathVariable String code){
        var codeEntity = verificationCodeService.getCode(code);
        codeEntity.getUser().setConfirmed(true);
        codeEntity.setValid(false);
        return ResponseEntity.accepted().build();
    }
}
