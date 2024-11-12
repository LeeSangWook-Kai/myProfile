package org.k8go4go.myprofile.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProfileImageDTO {
    private Long pid;
    private Long fid;
    private String fname;
    private String oname;
    private String ftype;
    private Long fsize;
    private String deleteYn;
    private String fullPath;
    private LocalDateTime createDate;

    public ProfileImageDTO(ProfileImageVO vo) {
        if(vo != null) {
            this.pid = vo.getPid();
            this.fid = vo.getFid();
            this.fname = vo.getFname();
            this.oname = vo.getOname();
            this.ftype = vo.getFtype();
            this.fsize = vo.getFsize();
            this.deleteYn = vo.getDeleteYn();
            this.createDate = vo.getCreateDate();
        }
    }

    public ProfileImageVO toEntity() {
        return ProfileImageVO.builder()
                .pid(pid)
                .fid(fid)
                .fname(fname)
                .oname(oname)
                .ftype(ftype)
                .fsize(fsize)
                .deleteYn(deleteYn)
                .createDate(createDate)
                .build();
    }
}
