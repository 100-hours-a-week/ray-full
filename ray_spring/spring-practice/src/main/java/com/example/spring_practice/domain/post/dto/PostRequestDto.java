package com.example.spring_practice.domain.post.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Getter
public class PostRequestDto {
    @Schema(description = "게시글 제목", example = "돈이 없습니다.", requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(max=26)
    @NotBlank(message = "제목, 내용을 모두 작성해주세요")
    private String title;
    @Schema(description = "게시글 내용", example = "소고기 먹고싶은데 돈이 없습니다. 돈을 주세요!", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "제목, 내용을 모두 작성해주세요")
    private String content;
    @Schema(description = "게시글 이미지", type = "String", format = "binary", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private MultipartFile postImage;
}
