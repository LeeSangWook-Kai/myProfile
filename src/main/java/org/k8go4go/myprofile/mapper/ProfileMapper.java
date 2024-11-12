package org.k8go4go.myprofile.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.k8go4go.myprofile.dto.Pager;
import org.k8go4go.myprofile.dto.ProfileImageVO;
import org.k8go4go.myprofile.dto.ProfileVO;

import java.util.List;

@Mapper
public interface ProfileMapper {
    List<ProfileVO> findProfileAll(Pager pager);

    ProfileVO findProfileById(String id);

    ProfileImageVO findImageById(Long fid);

    List<ProfileImageVO> findImageAll();

    void updateProfile(ProfileVO vo);

    void deleteProfile(Long pid);

    void deleteProfileImage(Long fid);

    void updateProfileImage(ProfileImageVO imgVO);

    long totalCount(Pager pager);
}
