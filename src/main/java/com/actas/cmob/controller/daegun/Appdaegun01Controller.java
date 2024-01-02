package com.actas.cmob.controller.daegun;

import com.actas.cmob.DTO.Daegun.DaegunItemList;
import com.actas.cmob.DTO.Daegun.DaegunXusers;
import com.actas.cmob.DTO.Kosep.*;
import com.actas.cmob.Service.daegun.DaegunAppService;
import com.actas.cmob.Service.kosep.KosepAppService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

// @RestController : return을 텍스트로 반환함.

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/daegun", method = RequestMethod.POST)
public class Appdaegun01Controller {
    protected Log log =  LogFactory.getLog(this.getClass());
    DaegunXusers userinfo = new DaegunXusers();
    DaegunItemList itemData = new DaegunItemList();
    List<DaegunItemList> itemDataList = new ArrayList<>();

    private final DaegunAppService authService;



    //  //출고등록
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class, SQLException.class})
    @RequestMapping(value="/usersave",method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object Appcom01Save_index(@RequestParam Map<String, String> param
            , Model model
            , HttpServletRequest request) throws Exception {
        String ls_itemcd = "";
        param.forEach((key, values) -> {
            switch (key) {
                case "custcd":
                    userinfo.setCustcd(values.toString());
                    break;
                case "userid":
                    userinfo.setUserid(values.toString());
                    break;
                case "pass":
                    userinfo.setPasswd1(values.toString());
                    userinfo.setPasswd2(values.toString());
                    break;
                case "email":
                    userinfo.setEmailtxt(values.toString());
                    break;
                case "name":
                    userinfo.setPernm(values.toString());
                    break;
                default:
                    break;
            }
        });

        userinfo.setUseyn("1");
        userinfo.setCustnm("DAEGUN");

        int queryResult = 0;
        queryResult = authService.InsertXusers(userinfo);
        if (queryResult < 1) {
            queryResult = 0;
                return "InsertXusers ERROR";
        }

        return "SUCCESS";
    }


    @RequestMapping(value="/itemsave",method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object Appcom01ItemSave_index(@RequestParam Map<String, String> param
            , Model model
            , HttpServletRequest request) throws Exception {
        String ls_inputdate = "";
        param.forEach((key, values) -> {
            switch (key) {
                case "custcd":
                    itemData.setCustcd(values.toString());
                    break;
                case "inputdate":
                    itemData.setInputdate(values.toString());
                    break;
                case "subject":
                    itemData.setItemsubject(values.toString());
                    break;
                case "itemmemo":
                    itemData.setItemmemo(values.toString());
                    break;
                case "flag":
                    itemData.setFlag(values.toString());
                    break;
                case "location":
                    itemData.setLocation(values.toString());
                    break;
                case "pernm":
                    itemData.setPernm(values.toString());
                    break;
                case "userid":
                    itemData.setUserid(values.toString());
                    break;
                case "boxpass":
                    itemData.setBoxpass(values.toString());
                    break;
                default:
                    break;
            }
        });
        ls_inputdate = itemData.getInputdate();

        String year = ls_inputdate.substring(0,4) ;
        String month = ls_inputdate.substring(5,7) ;
        String day   = ls_inputdate.substring(8,10) ;
        ls_inputdate = year + month + day ;
        itemData.setInputdate(ls_inputdate);
        int queryResult = 0;
        queryResult = authService.InsertItem(itemData);
        if (queryResult < 1) {
            queryResult = 0;
            return "InsertItemData ERROR";
        }

        return "SUCCESS";
    }


    @RequestMapping(value="/itemupdate",method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object Appcom01ItemUpdate_index(@RequestParam Map<String, String> param
            , Model model
            , HttpServletRequest request) throws Exception {
        String ls_inputdate = "";
        param.forEach((key, values) -> {
            switch (key) {
                case "custcd":
                    itemData.setCustcd(values.toString());
                    break;
                case "inputdate":
                    itemData.setInputdate(values.toString());
                    break;
                case "subject":
                    itemData.setItemsubject(values.toString());
                    break;
                case "itemmemo":
                    itemData.setItemmemo(values.toString());
                    break;
                case "flag":
                    itemData.setFlag(values.toString());
                    break;
                case "location":
                    itemData.setLocation(values.toString());
                    break;
                case "pernm":
                    itemData.setPernm(values.toString());
                    break;
                case "boxpass":
                    itemData.setBoxpass(values.toString());
                    break;
                case "seq":
                    itemData.setSeq( Integer.parseInt(values.toString()));
                    break;
                default:
                    break;
            }
        });
        ls_inputdate = itemData.getInputdate();

        //log.info("------------");
        //log.info(itemData.getItemsubject());
        String year = ls_inputdate.substring(0,4) ;
        String month = ls_inputdate.substring(5,7) ;
        String day   = ls_inputdate.substring(8,10) ;
        ls_inputdate = year + month + day ;
        itemData.setInputdate(ls_inputdate);
        int queryResult = 0;

        queryResult = authService.UpdateItem(itemData);
        if (queryResult < 1) {
            queryResult = 0;
            return "UpdateItemData ERROR";
        }

        return "SUCCESS";
    }

    @RequestMapping(value="/itemdelete",method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object Appcom01ItemDelete_index(@RequestParam Map<String, String> param
            , Model model
            , HttpServletRequest request) throws Exception {
        String ls_inputdate = "";
        param.forEach((key, values) -> {
            switch (key) {
                case "seq":
                    itemData.setSeq( Integer.parseInt(values.toString()));
                    break;
                default:
                    break;
            }
        });
        int queryResult = 0;
        queryResult = authService.DeleteItem(itemData);
        if (queryResult < 1) {
            queryResult = 0;
            return "DeleteItemData ERROR";
        }

        return "SUCCESS";
    }


    @RequestMapping(value="/itemcomplete",method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object Appcom01ItemComplete_index(@RequestParam Map<String, String> param
            , Model model
            , HttpServletRequest request) throws Exception {
        String ls_inputdate = "";
        param.forEach((key, values) -> {
            switch (key) {
                case "endmemo":
                    itemData.setEndmemo(values.toString());
                    break;
                case "seq":
                    itemData.setSeq( Integer.parseInt(values.toString()));
                    break;
                default:
                    break;
            }
        });
        itemData.setEnddate(getToDate());

        int queryResult = 0;

        queryResult = authService.CompleteItem(itemData);
        if (queryResult < 1) {
            queryResult = 0;
            return "CompleteItemData ERROR";
        }

        return "SUCCESS";
    }


    @RequestMapping(value="/itemlist",method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object AppcomList_index(@RequestParam Map<String, String> param
            , Model model
            , HttpServletRequest request) throws Exception{
        param.forEach((key, values) -> {
            switch (key){
                case "custcd":
                    itemData.setCustcd(values.toString());
                    break;
                case "searchtxt":
                    itemData.setItemsubject(values.toString());
                default:
                    break;
            }
        });
        String ls_search = itemData.getItemsubject();
        if(ls_search.equals("") || ls_search == null){
            itemData.setItemsubject("%");
        }
        itemDataList = authService.GetItemList(itemData);
        return itemDataList;
    }

    @RequestMapping(value="/itemlistend",method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object AppcomListEnd_index(@RequestParam Map<String, String> param
            , Model model
            , HttpServletRequest request) throws Exception{
        param.forEach((key, values) -> {
            switch (key){
                case "custcd":
                    itemData.setCustcd(values.toString());
                    break;
                case "searchtxt":
                    itemData.setItemsubject(values.toString());
                default:
                    break;
            }
        });
        String ls_search = itemData.getItemsubject();
        if(ls_search.equals("") || ls_search == null){
            itemData.setItemsubject("%");
        }
        itemDataList = authService.GetItemListEnd(itemData);
        return itemDataList;
    }

    private String getToDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date      = new Date(System.currentTimeMillis());

        return formatter.format(date);
    }



}
