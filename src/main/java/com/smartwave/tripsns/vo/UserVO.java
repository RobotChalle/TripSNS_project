package com.smartwave.tripsns.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVO {
    private String id=null;
    private String pw=null;

    @Override
    public String toString() {
        return "UserVO{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                ", birth='" + birth + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    private String name=null;
    private String birth=null;
    private String email=null;
}
