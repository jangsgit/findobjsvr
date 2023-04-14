package com.actas.cmob.controller;


import com.actas.cmob.DTO.CommonDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


@Controller
public class MainController {
    protected Log log =  LogFactory.getLog(this.getClass());
    CommonDto CommDto = new CommonDto();

    @GetMapping({"","/"})
    public  String index(){
        //머스테치 기본폴더 src/main/resources/
        //머스테치 뷰리졸버 기본설정 templates(prefix), .mustache(suffix)   생략가능
        return "index";
    }

    @GetMapping(value = "/mainframe")
    public  String main(){
        return "mainframe";
    }


    @GetMapping(value="/logout")
    public String logoutMember(){
        return "/logoutForm";
    }



}
