package com.example.spring_practice.domain.shared;

import com.example.spring_practice.global.response.CustomException;
import com.example.spring_practice.global.response.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Service
public class LocalImageService implements ImageService {

    @Value("${file.upload.path}")
    private String uploadPath;  // 예: /Users/username/uploads

    @Value("${file.upload.url}")
    private String uploadUrl;   // 예: http://localhost:8080/images

    @Override
    public String saveImg(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }

        try {
            // 업로드 디렉토리 생성
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // 고유한 파일명 생성 (UUID + 원본 확장자)
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String filePath = UUID.randomUUID() + extension;

            // 파일 저장
            Path destinationPath = Paths.get(uploadPath, filePath);
            file.transferTo(destinationPath.toFile());

            return filePath;

        } catch (IOException e) {
            throw new CustomException(ErrorCode.IMAGE_UPLOAD_FAILED);
        }
    }

    @Override
    public String getFullImgUrl(String imgUrl) {
        if (imgUrl == null || imgUrl.isEmpty()) {
            return null;
        }
        return uploadUrl + "/" + imgUrl;
    }
}
