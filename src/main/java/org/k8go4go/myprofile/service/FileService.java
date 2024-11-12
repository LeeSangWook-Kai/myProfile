package org.k8go4go.myprofile.service;

import lombok.RequiredArgsConstructor;
import org.k8go4go.myprofile.dto.ProfileImageDTO;
import org.k8go4go.myprofile.util.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileUtils fileUtils;

    public ProfileImageDTO uploadProfile(MultipartFile multipartFile) throws IOException {
        ProfileImageDTO response = new ProfileImageDTO();
        String uploadPathStr = fileUtils.getUploadRoot() + "profile";
        String uploadTempPathStr = uploadPathStr + File.separator + "temp";

        fileUtils.createUploadDirectory(new File(uploadPathStr), new File(uploadTempPathStr));

        String oname = multipartFile.getOriginalFilename();
        String fname = getFname(oname);

        response.setFname(fname);
        response.setOname(oname);
        response.setFullPath(fname);
        response.setFsize(multipartFile.getSize());
        response.setFtype(multipartFile.getContentType());

        multipartFile.transferTo(new File(uploadTempPathStr + File.separator + fname));
        return response;
    }

    private String getFname(String userFileName) {
        return UUID.randomUUID().toString() + "."+ userFileName.substring(userFileName.lastIndexOf(".") + 1);
    }
}
