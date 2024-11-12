package org.k8go4go.myprofile.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProfileDTO {
    private Long pid;
    private String id;
    private String name;
    private String passwd;
    private String email;
    private String deleteYn;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private MultipartFile profileImage;

    public ProfileDTO(ProfileVO vo) {
        if(vo != null) {
            this.pid = vo.getPid();
            this.id = vo.getId();
            this.name = vo.getName();
            this.passwd = vo.getPasswd();
            this.email = vo.getEmail();
            this.deleteYn = vo.getDeleteYn();
            this.createDate = vo.getCreateDate();
            this.updateDate = vo.getUpdateDate();
        }
    }

    public ProfileVO toEntity() {
        return ProfileVO.builder()
                .pid(pid)
                .id(id)
                .name(name)
                .passwd(passwd)
                .email(email)
                .deleteYn(deleteYn)
                .createDate(createDate)
                .updateDate(updateDate)
                .build();
    }
}
