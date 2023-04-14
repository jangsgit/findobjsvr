package com.actas.cmob.Entity;


import com.actas.cmob.DTO.UserFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name="tb_xusers")
@Getter @Setter
@ToString
public class User {
    @Id
    @Column(unique = true)
    private String username;

    /** 회사코드  */
    private String custcd;
    /** 사업장코드  */
    private String spjangcd;
    /** 주민번호  */
    private String rnum;

    /** password  */
    private String password;

    private String passwd2;

    /** role  */
    //@Enumerated(EnumType.STRING)
    private String role;

    private String pernm;

    /** 아이디사용여부  */
    private String useyn;
    /** 사원번호  */
    private String perid;



    public static User createMember(UserFormDto userFomDto ){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setCustcd(userFomDto.getCustcd());
        user.setSpjangcd(userFomDto.getSpjangcd());
        user.setUsername(userFomDto.getUsername());
        user.setPernm(userFomDto.getPernm());
        user.setUseyn(userFomDto.getUseyn());
        user.setRnum(userFomDto.getRnum());
        String password = passwordEncoder.encode(userFomDto.getPassword());
        user.setPassword(password);
        user.setPasswd2(userFomDto.getPasswd2());
        if(userFomDto.getUsername() == "ADMIN" || userFomDto.getUsername() == "admin"){
            user.setRole("ROLE_ADMIN");
        }else if(userFomDto.getUsername() == "MANAGER" || userFomDto.getUsername() == "manager"){
            user.setRole("ROLE_MANAGER");
        }else{
            user.setRole("ROLE_USER");
        }
        return user;
    }


}


