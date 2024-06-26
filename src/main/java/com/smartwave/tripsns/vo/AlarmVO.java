package com.smartwave.tripsns.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AlarmVO {
    private String user_id= null;
    private String other_id= null;
    private String message= null;
    private String in_date= null;
    private int alarm_no;
}
