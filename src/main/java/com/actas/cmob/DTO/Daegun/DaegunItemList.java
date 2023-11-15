package com.actas.cmob.DTO.Daegun;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class DaegunItemList {
    private int seq;
    private String custcd;
    private String flag;
    private String flagnm;
    private String inputdate;
    private String itemsubject;
    private String pernm;
    private String itemmemo;
    private String location;
    private int point;

}
