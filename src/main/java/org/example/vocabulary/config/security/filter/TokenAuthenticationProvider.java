package org.example.vocabulary.config.security.filter;

import lombok.RequiredArgsConstructor;
import org.example.vocabulary.entity.User;
import org.example.vocabulary.exception.InvalidTokenException;
import org.example.vocabulary.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();

        User user = userRepository.findByToken(token)
                .orElseThrow(() -> new InvalidTokenException("Invalid Token"));

        if (!user.getIsActive()) {
            throw new InvalidTokenException("User is blocked");
        }

        TokenAuthentication auth = new TokenAuthentication(token);
        auth.setUser(user);
        auth.setAuthenticated(true);
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.isAssignableFrom(authentication);
    }
}