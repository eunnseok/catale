package com.catale.backend.domain.image.controller;

import com.catale.backend.domain.image.dto.MemberImageUpdateRequestDto;
import com.catale.backend.domain.image.entity.Image;
import com.catale.backend.domain.image.service.ImageService;
import com.catale.backend.domain.member.entity.Member;
import com.catale.backend.domain.member.service.MemberService;
import com.catale.backend.global.format.code.ApiResponse;
import com.catale.backend.global.format.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Tag(name = "Image 컨트롤러", description = "Image Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/image")
public class ImageController {
    private final ApiResponse response;
    private final ImageService imageService;
    private final MemberService memberService;

    @Operation(summary = "사용자 프로필 사진 수정", description = "사용자 프로필 사진 수정")
    //consumes 메소드가 받아들일 수 있는 요청 미디어 값, produces 메소드가 생성하는 응답 값
    @PutMapping(value = "/member/profileimage", consumes = "multipart/form-data")
    public ResponseEntity<?> putMemberImage(@Parameter(hidden = true) Authentication authentication,
                                            @RequestPart(value = "file") MultipartFile multipartFile)throws IOException {

        Member me = memberService.findMember(authentication.getName());
        Long memberId = me.getId();

        String imageUrl = imageService.updateMemberImage(memberId, multipartFile);
        return response.success(ResponseCode.IMAGE_UPDATED,imageUrl);
    }
}
