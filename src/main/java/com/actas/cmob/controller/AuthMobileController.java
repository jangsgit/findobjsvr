package com.actas.cmob.controller;


import com.actas.cmob.DTO.UserFormDto;
import com.actas.cmob.Service.Auth.AuthMobileService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/authm", method = RequestMethod.POST)
public class AuthMobileController {

    private final AuthMobileService authService;
    UserFormDto userformDto = new UserFormDto();

    protected Log log =  LogFactory.getLog(this.getClass());


    @RequestMapping(value = "/loginmchk", method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object memberLoginChkFrom(@RequestParam Map<String, String> param
            , Model model
            , HttpServletRequest request)throws Exception{
        param.forEach((key, values) -> {
            switch (key){
                case "userid":
                    userformDto.setUserid(values.toString());

                    break;
                case "userpw":
                    userformDto.setPasswd1(values.toString());

                    break;
                case "dbnm":
                    userformDto.setDbnm(values.toString());

                    break;
                default:
                    break;
            }
        });

        String _dbnm = userformDto.getDbnm();
        UserFormDto userReturnDto = authService.GetUserInfo(userformDto);
        userReturnDto.setDbnm(_dbnm);
        userReturnDto.setUsername(userReturnDto.getPernm());
        log.info("로그인 진입");
        return userReturnDto;

    }



}
