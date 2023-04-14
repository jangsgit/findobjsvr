package com.actas.cmob.DTO;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TBXa012VO {
    private String custcd;          //회사 코드 .
    private String spjangcd;        //사업장코드 .
    private String spjangnm;        //사업장명
    private String custperclsf;     //사업형태
    private String saupnum;         //사업자번호
    private String prenm;           //대표자
    private String corpnum;         //법인번호
    private String biztype;         //업태
    private String item;            //종목
    private String tel1;            //전화
    private String fax;             //fax
    private String zipcd;           //우편번호
    private String adresa;          //주소 01
    private String adresb;          //주소 02
    private String popidmsg;        //문자 id
    private String poppw;           //문자 pw
    private String guchung;         //계산서 id
    private String reqcustcd;       //cms납입코드
    private String cmsmemo;         //cms기재내용
    private String cmspass;         //cms pw
    private String agnertel1;         //담당자
    private String agnertel2;         //연락처
}
