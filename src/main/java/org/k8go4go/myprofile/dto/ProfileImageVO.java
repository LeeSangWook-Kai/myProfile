package org.k8go4go.myprofile.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProfileImageVO {
    private Long pid;
    private Long fid;
    private String fname;
    private String oname;
    private String ftype;
    private Long fsize;
    private String deleteYn;
    private LocalDateTime createDate;
}
