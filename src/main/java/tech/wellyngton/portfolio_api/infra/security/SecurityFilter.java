package tech.wellyngton.portfolio_api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tech.wellyngton.portfolio_api.domain.user.UserEntity;
import tech.wellyngton.portfolio_api.domain.user.UserRepository;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService service;

    @Autowired
    private UserRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = getToken(request);
        if(jwtToken != null) {
            String subject = service.getSubject(jwtToken);
            UserEntity user = (UserEntity) repository.findByLogin(subject);

            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(user.getLogin(), null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    public String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
         if(token != null) {
             return token.replace("Bearer ", "");
         }
         return null;
    }
}
