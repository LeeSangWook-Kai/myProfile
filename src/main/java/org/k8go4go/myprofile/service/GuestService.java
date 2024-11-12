package org.k8go4go.myprofile.service;

import com.sun.nio.sctp.IllegalReceiveException;
import lombok.RequiredArgsConstructor;
import org.k8go4go.myprofile.dto.ProfileDTO;
import org.k8go4go.myprofile.dto.ProfileImageDTO;
import org.k8go4go.myprofile.dto.ProfileImageVO;
import org.k8go4go.myprofile.dto.ProfileVO;
import org.k8go4go.myprofile.mapper.GuestMapper;
import org.k8go4go.myprofile.util.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.FileSystemException;

@Service
@RequiredArgsConstructor
public class GuestService {
    private final FileUtils fileUtils;

    private final GuestMapper guestMapper;

    public ProfileDTO login(final String id, final String passwd) throws IllegalReceiveException {
        ProfileVO vo = guestMapper.findProfileById(id);
        ProfileDTO user = new ProfileDTO(vo);
        String getuserPasswd = user == null ? "" : user.getPasswd();

        if(user == null || !passwd.equals(getuserPasswd)) {
            throw new IllegalReceiveException("사용자 정보를 다시확인해 주세요.");
        }
        user.setPasswd("");
        return user;
    }

    @Transactional
    public void saveProfile(ProfileDTO request, ProfileImageDTO file) throws FileSystemException {
        ProfileVO profileVO = request.toEntity();
        guestMapper.insertProfile(profileVO);
        ProfileImageVO imgVO = file.toEntity();
        imgVO.setFid(profileVO.getPid());
        guestMapper.insertProfileImg(imgVO);
        fileUtils.moveFileToRealFromTemp(imgVO.getFname());
    }

    public ProfileDTO findUserById(String id) {
        return new ProfileDTO(guestMapper.findProfileById(id));
    }
}
