package org.example.vocabulary.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.vocabulary.dto.request.UserLoginDto;
import org.example.vocabulary.dto.request.UserRegisterDto;
import org.example.vocabulary.dto.response.TokenResponse;
import org.example.vocabulary.entity.User;
import org.example.vocabulary.entity.UserRole;
import org.example.vocabulary.exception.AlreadyExistException;
import org.example.vocabulary.exception.BadCredentialsException;
import org.example.vocabulary.exception.NotFoundException;
import org.example.vocabulary.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final Random random;


    public void register(UserRegisterDto dto) {

        if (userRepository.existsByEmail(dto.email())) {
            throw new AlreadyExistException("User with email " + dto.email() + " already exist");
        }

        User user = User.builder()
                .email(dto.email())
                .username(dto.username())
                .password(encoder.encode(dto.password()))
                .firstname(dto.firstname())
                .isActive(true)
                .role(UserRole.USER)
                .build();

        userRepository.save(user);
    }

    public TokenResponse login(UserLoginDto dto) {

        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new NotFoundException("User not with email: " + dto.email()));

        if (user.getIsActive() == false) {
            throw new BadCredentialsException("User is not active");
        }

        if (!encoder.matches(dto.password(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password or email");
        }

        String token = UUID.randomUUID().toString();

        user.setToken(token);
        User saveUser = userRepository.save(user);

        return TokenResponse.builder().token(token).id(saveUser.getId()).role(user.getRole().name()).build();
    }

}