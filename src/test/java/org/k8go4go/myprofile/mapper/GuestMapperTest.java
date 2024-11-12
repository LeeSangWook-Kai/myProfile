package org.k8go4go.myprofile.mapper;

import org.junit.jupiter.api.Test;
import org.k8go4go.myprofile.dto.ProfileImageVO;
import org.k8go4go.myprofile.dto.ProfileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

class GuestMapperTest {
    @Autowired
    private GuestMapper guestMapper;
    @Test
    public void insert() {
        for(int i = 0 ; i < 100 ; i++) {
            ProfileVO vo = new ProfileVO();
            vo.setId("k8go4og" + i);
            vo.setName("이상욱"+ i);
            vo.setPasswd("1231231"+i);
            vo.setEmail("8go4go" + i + "@gmail.com");
            guestMapper.insertProfile(vo);
            ProfileImageVO voImg = new ProfileImageVO();
            voImg.setFid(vo.getPid());
            voImg.setFname("40fe55b4-e9da-41af-bcdc-89c8c0bec349.jpg");
            voImg.setFtype("image/jpeg");
            voImg.setOname("digital_camera_photo-980x653.jpg");
            voImg.setFsize((long)16205);
            guestMapper.insertProfileImg(voImg);
        }
    }
}