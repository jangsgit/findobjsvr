package com.actas.cmob.controller;

import com.actas.cmob.DTO.CommonDto;
import com.actas.cmob.Service.Appcom01Service;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// @RestController : return을 텍스트로 반환함.
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/appcom01", method = RequestMethod.POST)
//@RequestMapping(value = "/test6", method = RequestMethod.GET)
//@RequestMapping(value = "/test6", method = RequestMethod.POST)
//@RequestMapping(value = "/test6_1", method = RequestMethod.POST)
//public String test6_1(HttpServletRequest request, Model model, Book book) {
public class Appcom01Controller {
    private final Appcom01Service appcom01Service;
    protected Log log =  LogFactory.getLog(this.getClass());
    CommonDto CommDto = new CommonDto();
//
//    //사업장정보조회
//    @GetMapping(value="/list")
//    public String Appcom01_index(@RequestParam("spcode") String spcode, Model model, HttpServletRequest request) throws Exception{
//        CommDto.setMenuTitle("사업장정보");
//        CommDto.setMenuUrl("기준정보>사업장정보");
//        CommDto.setMenuCode("appcom01");
//
//        TBXa012VO itemDto = new TBXa012VO();
//        TBXa012VO itemDtoInput = new TBXa012VO();
//        List<TBXa012VO> itemDtoList = new ArrayList<>();
//
//        itemDtoList = appcom01Service.GetXa012List();
//        itemDto.setSpjangcd(spcode);
//        if(appcom01Service.Getbxa012Detail(itemDto) == null){
//            model.addAttribute("itemDto", appcom01Service.GetTBXa012Blank());
//        }else{
//            model.addAttribute("itemDto", appcom01Service.Getbxa012Detail(itemDto));
//        }
//        log.debug("map check=====>" );
//        model.addAttribute("itemDtoList", itemDtoList);
//        model.addAttribute("itemDtoInput", itemDtoInput);
//        model.addAttribute("CommDto", CommDto);
//        return "AppCom/LayTbxa012";
//    }

//
//    //사업장정보등록
//    @RequestMapping(value="/insert" , method = RequestMethod.POST)
//    public String Appcom01_input(@ModelAttribute TBXa012VO itemDtoInput,  HttpServletRequest request){
//        appcom01Service.TBXa012Insert(itemDtoInput);
//        var lsSpjangcd = itemDtoInput.getSpjangcd();
//        return "redirect:/appcom01/list?spcode=" + lsSpjangcd;
//    }
//
//    //사업장정보수정
//    @RequestMapping(value="/update" , method = RequestMethod.POST)
//    public String Appcom01_update(HttpServletRequest request,  Model model, @ModelAttribute TBXa012VO itemDto ){
//        log.debug("map check=====>" + itemDto.toString() );
//        appcom01Service.TBXa012Update(itemDto);
//        model.addAttribute("itemDto", appcom01Service.Getbxa012Detail(itemDto));
//        var ls_spjangcd =   itemDto.getSpjangcd();
//        //"redirect:/appcom01/list";
//        return   "redirect:/appcom01/list?spcode=" + ls_spjangcd;
//    }
//
//    //사업장정보삭제
//    @RequestMapping(value="/delete", method = RequestMethod.GET)
//    public String Appcom01_delete(@RequestParam("spcode") String spcode,  HttpServletRequest request){
//        appcom01Service.TBXa012Delete(spcode);
//        var ls_spjangcd = "ZZ";
//        return "redirect:/appcom01/list?spcode=" + ls_spjangcd;
//    }

}
