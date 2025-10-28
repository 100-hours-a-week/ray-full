package com.example.spring_practice.domain.shared;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String saveImg(MultipartFile file);

    String getFullImgUrl(String imgUrl);
}
