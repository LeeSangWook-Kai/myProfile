package org.k8go4go.myprofile.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.k8go4go.myprofile.dto.ProfileDTO;
import org.k8go4go.myprofile.dto.ProfileImageDTO;
import org.k8go4go.myprofile.service.FileService;
import org.k8go4go.myprofile.service.ProfileService;
import org.k8go4go.myprofile.session.SessionConst;
import org.k8go4go.myprofile.util.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;

@Controller
@RequiredArgsConstructor
@RequestMapping("/file")
@Slf4j
public class FileController {
    private final FileUtils fileUtils;
    private final ProfileService profileService;
    private final FileService fileService;

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<ProfileImageDTO> upload(@RequestParam("multipartFile") MultipartFile multipartFile) throws IOException {
        ProfileImageDTO uploadInfo = fileService.uploadProfile(multipartFile);
        uploadInfo.setFullPath("/upload/profile/temp/" + uploadInfo.getFname());
        return ResponseEntity.ok().body(uploadInfo);
    }

    @GetMapping("/upload/profile")
    @ResponseBody
    public Resource profileImage(HttpServletRequest request) throws MalformedURLException {
        HttpSession session =  request.getSession();
        ProfileDTO user = (ProfileDTO)session.getAttribute(SessionConst.LOGIN_MEMBER);

        if(user != null) {
            String imagefileName = profileService.getProfile(user.getPid()).getFname();
            log.info("profile : " + imagefileName);
            UrlResource resource = new UrlResource("file:" + fileUtils.getFileFullPath(imagefileName));
            return resource;
        } else {
            throw new IllegalArgumentException("it\'s not right user");
        }
    }


    @GetMapping("/upload/profile/image")
    @ResponseBody
    public ResponseEntity<Resource> profile(HttpServletRequest request) throws IOException {
        HttpSession session =  request.getSession();
        ProfileDTO user = (ProfileDTO)session.getAttribute(SessionConst.LOGIN_MEMBER);

        if(user != null) {
            String imagefileName = profileService.getProfile(user.getPid()).getFname();
            log.info("profile : " + imagefileName);
            UrlResource resource = new UrlResource("file:" + fileUtils.getFileFullPath(imagefileName));
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(resource.getFile().toPath()))
                    .body(resource);
        } else {
            throw new IllegalArgumentException("it\'s not right user");
        }
    }
}
