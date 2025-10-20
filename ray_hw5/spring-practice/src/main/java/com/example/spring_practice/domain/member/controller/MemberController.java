package com.example.spring_practice.domain.member.controller;

import com.example.spring_practice.domain.member.dto.*;
import com.example.spring_practice.domain.member.entity.Member;
import com.example.spring_practice.domain.member.service.MemberService;
import com.example.spring_practice.global.response.ApiResponse;
import com.example.spring_practice.global.response.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MemberController {
    private final MemberService memberService;

    @PostMapping(value = "/signup", consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<Void>> signUp(
            @ModelAttribute SignUpRequestDto signUpRequestDto){
        memberService.signUp(signUpRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(Message.SIGNUP_SUCCESS));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JwtTokenResponseDto>> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity
                .ok(ApiResponse.success(Message.LOGIN_SUCCESS, memberService.login(loginRequestDto)));
    }

    @GetMapping("/my-profile")
    public ResponseEntity<ApiResponse<ProfileResponseDto>> getMyProfile(){
        return ResponseEntity.ok(ApiResponse.success(Message.GET_MY_PROFILE_SUCCESS, memberService.getMyProfile()));
    }
    @PatchMapping(value = "/profile", consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<Void>> editProfile(@ModelAttribute EditProfileRequestDto editProfileRequestDto){
        memberService.editProfile(editProfileRequestDto);
        return ResponseEntity.ok(ApiResponse.success(Message.EDIT_PROFILE_SUCCESS));
    }
    @PatchMapping("/password")
    public ResponseEntity<ApiResponse<Void>> editPassword(@RequestParam("password")String password){
        memberService.editPassword(password);
        return ResponseEntity.ok(ApiResponse.success(Message.EDIT_PASSWORD_SUCCESS));
    }

    @GetMapping("/email/duplicate-check")
    public ResponseEntity<ApiResponse<DuplicateCheckResponseDto>> emailDuplicateCheck(@RequestParam("email")String email){
        return ResponseEntity.ok(ApiResponse.success(
                Message.CHECK_DUPLICATE_EMAIL_SUCCESS, memberService.emailDuplicateCheck(email)));
    }
    @GetMapping("/nickname/duplicate-check")
    public ResponseEntity<ApiResponse<DuplicateCheckResponseDto>> passwordDuplicateCheck(@RequestParam("nickname")String nickname){
        return ResponseEntity.ok(ApiResponse.success(
                Message.CHECK_DUPLICATE_NICKNAME_SUCCESS, memberService.passwordDuplicateCheck(nickname)));
    }

    @GetMapping("/test")
    public ResponseEntity test(){
        return ResponseEntity.ok(memberService.getAllMembers());
    }
}
