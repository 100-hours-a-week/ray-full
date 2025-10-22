package com.example.spring_practice.domain.member.service;

import com.example.spring_practice.domain.member.dto.*;
import com.example.spring_practice.domain.member.entity.Member;
import com.example.spring_practice.domain.member.repository.MemberRepository;
import com.example.spring_practice.domain.shared.ImageService;
import com.example.spring_practice.global.security.AuthContext;
import com.example.spring_practice.global.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final ImageService imageService;
    private final AuthService authService;
    private final MemberDtoConverter memberDtoConverter;

    public ProfileResponseDto getMyProfile() {
        return memberDtoConverter.toProfileResponseDto(authService.getCurrentMember());
    }

    public void editPassword(String password) {
        authService.getCurrentMember().setPassword(password);
    }

    public void editProfile(EditProfileRequestDto editProfileRequestDto) {
        if(editProfileRequestDto.getProfileImage() != null){
            authService.getCurrentMember().setProfileImgUrl(imageService.saveImg(editProfileRequestDto.getProfileImage()));
        }
        if(editProfileRequestDto.getNickname()!= null){
            authService.getCurrentMember().setNickname(editProfileRequestDto.getNickname());
        }
    }

    public DuplicateCheckResponseDto emailDuplicateCheck(String email) {
        return memberDtoConverter.toDuplicateCheckResponseDto(memberRepository.existsByEmail(email));
    }

    public DuplicateCheckResponseDto passwordDuplicateCheck(String nickname) {
        return memberDtoConverter.toDuplicateCheckResponseDto(memberRepository.existsByNickname(nickname));
    }
}
