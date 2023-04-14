package com.actas.cmob.AppXController;


import com.actas.cmob.DTO.UserFormDto;
import com.actas.cmob.Entity.User;
import com.actas.cmob.auth.PrincipalDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final PrincipalDetailsService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value="/new")
    public String memberForm(Model model){
        model.addAttribute("userFormDto", new UserFormDto());
        return "register";
    }

    @PostMapping(value="/new")
    public String memberForm(@Valid UserFormDto userFormDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "register";
        }
        try {
            userFormDto.setCustcd("actas");
            userFormDto.setSpjangcd("ZZ");
            userFormDto.setUseyn("0");
            userFormDto.setRnum("0");
//            User user = User.createMember(userFormDto);
//            userService.saveMember(user);
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }
        return "loginForm";
    }

    @GetMapping("/user")
    public  String user(){
        return "memberframe";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/admin")
    public  String admin(){
        return "adminframe";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN') ")
    @GetMapping("/manager")
    public   String member(){
        return "memberframe";
    }


    @GetMapping(value="/loginForm")
    public String loginMember(){
        return "/loginForm";
    }




    @GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "/loginForm";
    }

}