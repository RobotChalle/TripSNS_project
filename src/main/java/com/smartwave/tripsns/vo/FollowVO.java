package com.smartwave.tripsns.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FollowVO {
    private String follow_id =null;// 팔로우당한 사람 {팔로우 수 } 상대방
    private String follower_id =null;// 팔로우 누른사람{팔로워수}  로그인한 아이디

}
