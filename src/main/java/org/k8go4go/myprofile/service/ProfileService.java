package org.k8go4go.myprofile.service;

import lombok.RequiredArgsConstructor;
import org.k8go4go.myprofile.dto.*;
import org.k8go4go.myprofile.mapper.ProfileMapper;
import org.k8go4go.myprofile.util.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.FileSystemException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileMapper profileMapper;
    private final FileUtils fileUtils;

    public ProfileDTO findUserById(String id) {
        return new ProfileDTO(profileMapper.findProfileById(id));
    }

    public List<ProfileDTO> findAllUser(Pager pager) {
        return profileMapper.findProfileAll(pager).stream().map((m) -> {
            m.setPasswd("******");
            return m;
        }).map(ProfileDTO::new).toList();
    }

    public ProfileImageDTO getProfile(Long pid) {
        ProfileImageVO vo = profileMapper.findImageById(pid);
        ProfileImageDTO response = new ProfileImageDTO(vo);
        return response;
    }
    @Transactional
    public void modifyProfile(ProfileDTO modifyUser, ProfileImageDTO modifyFIle) throws FileSystemException {
        ProfileVO profileVO = modifyUser.toEntity();
        profileMapper.updateProfile(profileVO);
        ProfileImageVO imgVO = modifyFIle.toEntity();
        imgVO.setFid(profileVO.getPid());
        profileMapper.updateProfileImage(imgVO);
        fileUtils.moveFileToRealFromTemp(imgVO.getFname());
    }

    public long totalCount(Pager pager) {
        return profileMapper.totalCount(pager);
    }
}
