package com.example.spring_practice.domain.member.controller;

import com.example.spring_practice.domain.member.dto.JwtTokenResponseDto;
import com.example.spring_practice.domain.member.dto.LoginRequestDto;
import com.example.spring_practice.domain.member.dto.SignUpRequestDto;
import com.example.spring_practice.domain.member.service.AuthService;
import com.example.spring_practice.global.response.ApiResponse;
import com.example.spring_practice.global.response.Message;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "회원가입", description = "신규 회원을 등록합니다.")
    @PostMapping(value = "/signup", consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<Void>> signUp(@Valid @ModelAttribute SignUpRequestDto signUpRequestDto){
        authService.signUp(signUpRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(Message.SIGNUP_SUCCESS));
    }

    @Operation(summary = "로그인", description = "로그인을 진행합니다.")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JwtTokenResponseDto>> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity
                .ok(ApiResponse.success(Message.LOGIN_SUCCESS, authService.login(loginRequestDto)));
    }
}
