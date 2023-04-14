package com.actas.cmob.DTO;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Getter @Setter
public class UserFormDto {
    @Id
    @NotBlank(message="사용자아이디는 필수입니다.")
    private String userid;

    /** 사용자명  */
    private String username;

    /** 회사코드  */
    private String custcd;
    /** 사업장코드  */
    private String spjangcd;
    /** rnum  */
    private String rnum;

    /** password  */
    @NotBlank(message="비밀번호는 필수입니다.")
    @Length(min=4, max=8, message="비밀번호는 4자이상 8자 이하로 입력하세요.")
    private String password;


    @NotBlank(message="비밀번호는 필수입니다.")
    @Length(min=4, max=8, message="비밀번호는 4자이상 8자 이하로 입력하세요.")
    private String passwd1;

    @NotBlank(message="비밀번호는 필수입니다.")
    @Length(min=4, max=8, message="비밀번호는 4자이상 8자 이하로 입력하세요.")
    private String passwd2;

    /** role  */
//    @Enumerated(EnumType.STRING)
    private String role;

    @NotBlank(message="사용명은 필수입니다.")
    private String pernm;
    /** 아이디사용여부  */
    private String useyn;
    /** 사원번호  */
    private String perid;



    /** 사업자번호  */
    private String saupnum;
    /** 핸드폰번호  */
    private String phone;
    /** 현장코드  */
    private String actcd;
    /** 현장명  */
    private String actnm;
    /** 거래처코드(보수업체)  */
    private String cltcd;
    /** 거래처명(보수업체)  */
    private String cltnm;
    /** 보수업체  AA  고객 CC  */
    private String flag;
    /**  사용자명칭  */
    private String custnm;
    /**  db name  */
    private String dbnm;
    /** callflag  */
    private String callflag;
    /** 콜 아이디  */
    private String calluserid;
    /** 콜 패스워드  */
    private String calluserpw;

    @NotBlank(message = "이메일은 필수입니다.")
    private String email;
    /** 암호화 pw */
    private String encodepw;
    /** ip주소 */
    private String ipaddr;
    /** 로그인 실패 횟수 */
    private String wrongnum;
    /** 상위분류01 */
    private String pagetree01;
    /** 상위분류02 */
    private String pagetree02;
    /** 상위분류03 */
    private String pagetree03;
    /** 화면명 */
    private String pagenm;

    private Integer seq;
}
