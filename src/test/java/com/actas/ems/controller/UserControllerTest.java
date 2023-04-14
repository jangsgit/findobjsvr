package com.actas.ems.controller;


import com.actas.cmob.DTO.UserFormDto;
import com.actas.cmob.Entity.User;
import com.actas.cmob.auth.PrincipalDetailsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;
import javax.transaction.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
//@TestPropertySource(locations = "classpath:application-test.properties")
public class UserControllerTest {

    @Autowired
    private PrincipalDetailsService memberService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User createMember(String userid, String password){
        UserFormDto memberFormDto = new UserFormDto();
        memberFormDto.setUsername(userid);
        memberFormDto.setPernm("개발자");
        memberFormDto.setCustcd("actas");
        memberFormDto.setPassword(password);
        User member = User.createMember(memberFormDto);
        return  memberService.saveMember(member);
    }

    @Test
    @DisplayName("로그인테스트성공")
    public void loginSuccessTest() throws  Exception{
        String userid = "dev00";
        String password = "1234";
        this.createMember(userid,password);
        mockMvc.perform(formLogin().userParameter("userid")
                .loginProcessingUrl("/members/login")
                .user(userid).password(password))
                .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }


}
