package com.actas.cmob.DTO.Daegun;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class DaegunComlist {
    private int seq;
    private int itemseq;
    private String custcd;
    private String comment;
    private String userid;
    private String inputdate;
    private int point;
    private Timestamp today;

}
