package com.actas.cmob.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_xa012")
@Getter
@Setter
@ToString
public class Tbxa012 {
    @Id
    @Column(name="spjangcd", length = 2)
    private String spjangcd;        //사업장코드
    @Column(nullable = false)
    private String spjangnm;        //사업장명
    private String custperclsf;     //사업형태
    private String saupnum;         //사업자번호
    private String prenm;           //대표자
    private String corpnum;         //법인번호
    @Column(nullable = false)
    private String biztype;         //업태
    @Column(nullable = false)
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

}
