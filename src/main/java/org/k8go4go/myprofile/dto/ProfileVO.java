package org.k8go4go.myprofile.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProfileVO {
    private Long pid;
    private String id;
    private String passwd;
    private String name;
    private String email;
    private String deleteYn;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
