package com.actas.ems.Service;


import com.actas.cmob.DTO.UserFormDto;
import com.actas.cmob.Entity.User;
import com.actas.cmob.auth.PrincipalDetailsService;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional

public class UserServiceTest {
    @Autowired
    PrincipalDetailsService memberService;
    @Autowired
    PasswordEncoder passwordEncoder;

    public User createMember(){
        UserFormDto memberFormDto = new UserFormDto();
        memberFormDto.setCustcd("actas");
        memberFormDto.setSpjangcd("ZZ");
        memberFormDto.setUsername("actas");
        memberFormDto.setPernm("개발자테스트");
        memberFormDto.setUseyn("0");
        memberFormDto.setPassword("1234");
        memberFormDto.setPasswd2("1234");
        memberFormDto.setRole("ROLE_USER");
        return User.createMember(memberFormDto);
    }

    @Test
    @DisplayName("회원가입테스트")
    public void saveMemberTest(){
        User member = createMember();
        User saveMember = memberService.saveMember(member);
        Assertions.assertEquals(member.getUsername(), saveMember.getUsername());
        Assertions.assertEquals(member.getPernm(), saveMember.getPernm());

    }

    @Test
    @DisplayName("중복 회원 가입 테스트")
    public void saveDuplicateMemberTest(){
        User member1 = createMember();
        User member2 = createMember();
        memberService.saveMember(member1);

//        Throwable e = assertThrows(IllegalStateException.class, () -> {
//            memberService.saveMember(member2);
//        });
//        assertEquals("이미가입된 회원입니다.", e.getMessage());
    }
}

