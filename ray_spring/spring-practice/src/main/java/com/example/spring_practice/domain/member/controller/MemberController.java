package com.example.spring_practice.domain.member.controller;

import com.example.spring_practice.domain.member.dto.*;
import com.example.spring_practice.domain.member.service.MemberService;
import com.example.spring_practice.global.response.ApiResponse;
import com.example.spring_practice.global.response.Message;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "내 프로필 조회", description = "로그인한 사용자의 프로필 정보를 조회합니다.")
    @GetMapping("/my-profile")
    public ResponseEntity<ApiResponse<ProfileResponseDto>> getMyProfile(){
        return ResponseEntity.ok(ApiResponse.success(Message.GET_MY_PROFILE_SUCCESS, memberService.getMyProfile()));
    }

    @Operation(summary = "프로필 정보 변경", description = "로그인한 사용자의 닉네임 및 프로필 사진을 변경합니다.")
    @PatchMapping(value = "/profile", consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<Void>> editProfile(@Valid @ModelAttribute EditProfileRequestDto editProfileRequestDto){
        memberService.editProfile(editProfileRequestDto);
        return ResponseEntity.ok(ApiResponse.success(Message.EDIT_PROFILE_SUCCESS));
    }

    @Operation(summary = "비밀번호 변경", description = "로그인한 사용자의 비밀번호를 변경합니다.")
    @Parameter(name = "password", description = "변경할 비밀번호", required = true)
    @PatchMapping("/password")
    public ResponseEntity<ApiResponse<Void>> editPassword(@RequestParam("password") String password){
        memberService.editPassword(password);
        return ResponseEntity.ok(ApiResponse.success(Message.EDIT_PASSWORD_SUCCESS));
    }

    @Operation(summary = "이메일 중복 체크", description = "특정 이메일이 이미 등록된 이메일인지 확인합니다.")
    @Parameter(name = "email", description = "중복 확인할 이메일", required = true)
    @GetMapping("/email/duplicate-check")
    public ResponseEntity<ApiResponse<DuplicateCheckResponseDto>> emailDuplicateCheck(@RequestParam("email")String email){
        return ResponseEntity.ok(ApiResponse.success(
                Message.CHECK_DUPLICATE_EMAIL_SUCCESS, memberService.emailDuplicateCheck(email)));
    }

    @Operation(summary = "닉네임 중복 체크", description = "특정 닉네임이 이미 등록된 닉네임인지 확인합니다.")
    @Parameter(name = "nickname", description = "중복 확인할 닉네임", required = true)
    @GetMapping("/nickname/duplicate-check")
    public ResponseEntity<ApiResponse<DuplicateCheckResponseDto>> nicknameDuplicateCheck(@RequestParam("nickname")String nickname){
        return ResponseEntity.ok(ApiResponse.success(
                Message.CHECK_DUPLICATE_NICKNAME_SUCCESS, memberService.nicknameDuplicateCheck(nickname)));
    }
}
