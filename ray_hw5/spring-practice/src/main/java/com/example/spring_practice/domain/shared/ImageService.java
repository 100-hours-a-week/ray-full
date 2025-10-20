package com.example.spring_practice.domain.shared;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
    // 추후 이미지 저장 기능 구현 예정
    public static String getFullImgUrl(String imgUrl){
        return imgUrl;
    }
    public String saveImg(MultipartFile file){
        return "img";
    }
}
