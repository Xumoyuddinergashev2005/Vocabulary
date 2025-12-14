package org.example.vocabulary.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.vocabulary.dto.request.RestartPasswordDto;
import org.example.vocabulary.dto.response.VerifyCodeDto;
import org.example.vocabulary.dto.request.UserLoginDto;
import org.example.vocabulary.dto.request.UserRegisterDto;
import org.example.vocabulary.dto.response.MessageResponse;
import org.example.vocabulary.dto.response.TokenResponse;
import org.example.vocabulary.entity.User;
import org.example.vocabulary.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "User authentication and registration APIs")
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegisterDto dto) {
        authService.register(dto);
        return ResponseEntity.status(201).body(MessageResponse.success("User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto dto) {
        TokenResponse response = authService.login(dto);
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@AuthenticationPrincipal User user) {
        authService.logOut(user);
        return ResponseEntity.status(200).body(MessageResponse.success("Logout successfully"));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> restePassword(@RequestBody RestartPasswordDto dto) {
        String code = authService.restartPassword(dto);
        return ResponseEntity.status(200).body(MessageResponse.success("OTP send successfully : " + code));
    }

    @PostMapping("/reset-password/verify-code")
    public ResponseEntity<?> verifyCode(@RequestBody @Valid VerifyCodeDto dto) {
        authService.verifyCode(dto);
        return ResponseEntity.status(200).body(MessageResponse.success("Password reset successfully"));
    }
}