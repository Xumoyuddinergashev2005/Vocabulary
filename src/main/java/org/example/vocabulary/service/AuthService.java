package org.example.vocabulary.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.vocabulary.dto.request.RestartPasswordDto;
import org.example.vocabulary.dto.response.VerifyCodeDto;
import org.example.vocabulary.dto.request.UserLoginDto;
import org.example.vocabulary.dto.request.UserRegisterDto;
import org.example.vocabulary.dto.response.TokenResponse;
import org.example.vocabulary.entity.User;
import org.example.vocabulary.entity.UserRole;
import org.example.vocabulary.exception.AlreadyExistException;
import org.example.vocabulary.exception.BadCredentialsException;
import org.example.vocabulary.exception.NotFoundException;
import org.example.vocabulary.repository.UserRepository;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final Random random;
    private final StringRedisTemplate redis;


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

    public void logOut(User user) {
        user.setToken(null);
        userRepository.save(user);
    }

    public String restartPassword(RestartPasswordDto dto) {

        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new NotFoundException("User not found with email: " + dto.email()));

        if (!user.getUsername().equals(dto.username())) {
            throw new BadCredentialsException("Username does not match");
        }

        String code = String.valueOf(random.nextInt(1000, 9999));
        redis.opsForValue().set(user.getEmail(), code, 20, TimeUnit.SECONDS);


        return code;
    }

    public void verifyCode(@Valid VerifyCodeDto dto) {
        String ramCode = redis.opsForValue().get(dto.email());

        if (ramCode == null || !ramCode.equals(dto.code())) {
            throw new BadCredentialsException("Invalid code");
        }
        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new NotFoundException("User not found with email: " + dto.email()));
        user.setPassword(encoder.encode(dto.newPassword()));
        userRepository.save(user);

        redis.delete(dto.email());
    }

}