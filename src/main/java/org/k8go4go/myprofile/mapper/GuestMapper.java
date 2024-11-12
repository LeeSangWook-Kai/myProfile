package org.k8go4go.myprofile.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.k8go4go.myprofile.dto.ProfileImageVO;
import org.k8go4go.myprofile.dto.ProfileVO;

@Mapper
public interface GuestMapper {
    ProfileVO findProfileById(String id);

    void insertProfile(ProfileVO vo);

    void insertProfileImg(ProfileImageVO vo);
}
