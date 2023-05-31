package com.actas.cmob.controller.themoon;


import com.actas.cmob.DTO.Kosep.KosepDa037Dto;
import com.actas.cmob.DTO.Kosep.KosepDa037HDto;
import com.actas.cmob.DTO.Kosep.KosepList01Dto;
import com.actas.cmob.DTO.Themoon.*;
import com.actas.cmob.DTO.UserFormDto;
import com.actas.cmob.Service.kosep.KosepAppService;
import com.actas.cmob.Service.themoon.ThemoonAppService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/themoon", method = RequestMethod.POST)
public class Appthemoon01Controller {
    protected Log log = LogFactory.getLog(this.getClass());


    private final KosepAppService authService;
    UserFormDto userFormDto = new UserFormDto();

    LoginLogDto loginLogDto = new LoginLogDto();
    PopDto popDto = new PopDto();

    PopDto popDto2 = new PopDto();



    PopDto popDto3 = new PopDto();

    PopDto popDto4 = new PopDto();

    PopDto popDto5 = new PopDto();

    PopDto popDto6 = new PopDto();

    List<ThemoonListDto> list01Dto = new ArrayList<>();


    List<ThemoonListDto2> list02Dto = new ArrayList<>();

    List<ThemoonListDto2> lists = new ArrayList<>();

    List<ThemoonListDto2> lists2 = new ArrayList<>();


    String phm_pcode;

    String stdate;



    private final ThemoonAppService themoonAppService;








    /**로그인시 로그 저장*/
    @RequestMapping(value = "/loginlog", method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public String mfixlistSaveForm(@RequestParam Map<String, String> param,
                                   Model model, HttpServletRequest request) throws Exception {

        String ls_dbnm = "";

        String ls_perid;
        String ls_pernm;


        param.forEach((key, values) -> {
            switch (key) {

                case "custcd":
                    loginLogDto.setCustcd(values.toString());
                    break;
                case "userid":
                    loginLogDto.setUserid(values.toString());
                    break;
                case "ipaddr":
                    loginLogDto.setIpaddr(values.toString());
                    break;
                case "usernm":
                    loginLogDto.setUsernm(values.toString());
                    break;
                default:
                    break;
            }
        });

        log.info(loginLogDto.getCustcd() + " chek");
        log.info(loginLogDto.getIpaddr() + " ipaddr");
        log.info(getToDate() + " 오늘날짜");
        log.info(loginLogDto.getUsernm() + " Usernm");

        loginLogDto.setOnoffdt(getToDate());
        loginLogDto.setCustnm("(주)두문");
        loginLogDto.setOndate(getToDate());


        int result = themoonAppService.LoginCount(loginLogDto);
        if(result == 0){
            boolean resultset = themoonAppService.InsertLog(loginLogDto);
            if (!resultset) {
                log.info("error1");
                return "error";

            }
        }else{
            boolean resultset2 = themoonAppService.UpdateLog(loginLogDto);
            if(!resultset2){
                log.info("error2");
                return "error";
            }
        }


        return "success";
    }




    /**품목 조회*/
    @RequestMapping(value="/list01",method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object Appcom01_index(@RequestParam Map<String, String> param
            , Model model
            , HttpServletRequest request) throws Exception{


        param.forEach((key, values) -> {
            switch (key){
                case "dbnm":
                    popDto.setDbnm(values.toString());
                    break;
                case "code88":
                    popDto.setCode88(values.replace("\n",""));
                    break;
                default:
                    break;
            }
        });

        log.info(popDto.getCode88() + " Code88");

        list01Dto = themoonAppService.TB_CA501list(popDto);
        return list01Dto;
    }


    /**입고예정 조회*/
    @RequestMapping(value="/list02",method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object Appcom02_index(@RequestParam Map<String, String> param
            , Model model
            , HttpServletRequest request) throws Exception{
        param.forEach((key, values) -> {
            switch (key){
                case "dbnm":
                    popDto.setDbnm(values.toString());
                    break;
                case "pcode":
                    popDto.setPcode(values.toString());
                    break;
                case "wendt":
                    popDto.setWendt(values.toString());
                    break;
                default:
                    break;
            }
        });

        log.info(popDto.getPcode());
        log.info(popDto.getWendt());

        list02Dto = themoonAppService.Store_Info(popDto);
        return list02Dto;
    }


    /**로그인시 로그 저장*/
    @RequestMapping(value = "/Update_TB_FPLAN", method = RequestMethod.POST,
            headers = {"Content-Type=application/json"},
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public String TBFPLANUpdateForm(@RequestBody Map<String, Object> data,
                                   Model model, HttpServletRequest request) throws Exception {

        String ls_dbnm = "";



        String dbnm = data.get("dbnm").toString();
        String closePerid = data.get("close_perid").toString();
        String close_date = data.get("close_date").toString();

        List<String> wokqtList = (List<String>) data.get("wokqtList");
        List<String> planNoList = (List<String>) data.get("planNoList");
        List<String> wonoList = (List<String>) data.get("wonolist");
        List<String> lotnoList = (List<String>) data.get("lotnoList");
        List<String> pcodeList = (List<String>) data.get("pcodeList");
        List<String> endqtyList = (List<String>) data.get("end_qty");



        for(int i = 0; i < planNoList.size(); i++){

            popDto.setClose_date(close_date);
            popDto.setEnd_qty(endqtyList.get(i));
            //popDto.setEnd_qty(wokqtList.get(i));
            popDto.setClose_perid(closePerid);
            popDto.setPlan_no(planNoList.get(i));

            log.info(popDto.getEnd_qty() + " check");
              themoonAppService.Update_TB_FPLAN(popDto);


        }
        log.info(popDto.getClose_date());
        log.info(popDto.getEnd_qty());
        log.info(popDto.getClose_perid());
        log.info(popDto.getPlan_no());

        for(int i=0; i < planNoList.size(); i++){

            popDto2.setPlan_no(planNoList.get(i));
            popDto2.setWono(wonoList.get(i));
            popDto2.setGs_today(close_date);
            popDto2.setWotqt(endqtyList.get(i));
            popDto2.setClose_perid(closePerid);

            themoonAppService.Insert_TB_FPLAN_WORK(popDto2);


        }

        log.info(popDto2.getWono() + " 2번째");
        log.info(popDto2.getPlan_no() + " 2번째");
        log.info(popDto2.getClose_date() + " 2번째");
        log.info(popDto2.getWotqt() +  " 2번째" );
        log.info(popDto2.getClose_perid() + " 2번째");



        for(int i=0; i< planNoList.size(); i++){

            popDto3.setPlan_no(planNoList.get(i));
            popDto3.setWotqt(endqtyList.get(i));
            popDto3.setClose_perid(closePerid);
            popDto3.setGs_today(close_date);

            themoonAppService.Insert_TB_FPLAN_W030(popDto3);

        }

        log.info(popDto3.getPlan_no() + " 3번째");
        log.info(popDto3.getWotqt() + " 3번째");
        log.info(popDto3.getClose_perid() + " 3번째");
        log.info(popDto3.getGs_today() + " 3번째");


        for(int i=0; i< planNoList.size(); i++){

            popDto4.setPlan_no(planNoList.get(i));
            popDto4.setPcode(pcodeList.get(i));
            popDto4.setLotno(lotnoList.get(i));
            popDto4.setGs_today(close_date);
            popDto4.setWotqt(endqtyList.get(i));
            popDto4.setClose_perid(closePerid);

            themoonAppService.Insert_tb_fplan_owork(popDto4);

        }

        log.info(popDto4.getPlan_no() + " 4번째");
        log.info(popDto4.getPcode() + " 4번째");
        log.info(popDto4.getLotno() + " 4번째");
        log.info(popDto4.getGs_today() + " 4번째");
        log.info(popDto4.getWotqt() + " 4번째");
        log.info(popDto4.getClose_perid() + " 4번째");

        for(int i=0; i< planNoList.size(); i++){
            popDto5.setPlan_no(planNoList.get(i));
            popDto5.setClose_perid(closePerid);
            popDto5.setGs_today(close_date);

            themoonAppService.INSERT_TB_FPLAN_WPERID(popDto5);
        }


        log.info(popDto5.getPlan_no() + " 5번째, Plan_no");
        log.info(popDto5.getClose_perid() + " 5번째");
        log.info(popDto5.getGs_today() + " 5번째");


        for(int i=0; i< planNoList.size(); i++){
            popDto6.setPlan_no(planNoList.get(i));
           themoonAppService.INSERT_TB_FPLAN_WTIME(popDto6);

        }

        log.info(popDto6.getPlan_no() + " 6번째, Plan_no");

        return "success";
    }



    private String getToDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date      = new Date(System.currentTimeMillis());

        return formatter.format(date);
    }




    /**로그인시 로그 저장*/
    @RequestMapping(value = "/loginlog_h", method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public String InsertLog_h_SaveForm(@RequestParam Map<String, String> param,
                                   Model model, HttpServletRequest request) throws Exception {

        String ls_dbnm = "";

        String ls_perid;
        String ls_pernm;


        param.forEach((key, values) -> {
            switch (key) {

                case "userid":
                    loginLogDto.setUserid(values.toString());
                    break;
                case "ipaddr":
                    loginLogDto.setIpaddr(values.toString());
                    break;
                case "usernm":
                    loginLogDto.setUsernm(values.toString());
                    break;
                case "winnm":
                    loginLogDto.setWinnm(values.toString());
                    break;
                case "winid":
                    loginLogDto.setWinid(values.toString());
                    break;
                case "buton":
                    loginLogDto.setButon(values.toString());
                    break;
                default:
                    break;
            }
        });

        log.info(loginLogDto.getCustcd() + " chek");
        log.info(loginLogDto.getIpaddr() + " ipaddr");
        log.info(getToDate() + " 오늘날짜");
        log.info(loginLogDto.getUsernm() + " Usernm");


        themoonAppService.Insert_login_h(loginLogDto);


        return "success";
    }



    /**입고현황 조회*/
    @RequestMapping(value="/list03",method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object Select_List_index(@RequestParam Map<String, String> param
            , Model model
            , HttpServletRequest request) throws Exception{
        param.forEach((key, values) -> {
            switch (key){
                case "dbnm":
                    popDto.setDbnm(values.toString());
                    break;

                case "gs_today":
                    popDto.setGs_today(values.toString());
                    break;
                default:
                    break;
            }
        });

        log.info(popDto.getGs_today());

        list02Dto = themoonAppService.Select_List(popDto);

        return list02Dto;
    }



    /**입고취소 업데이트*/
    @RequestMapping(value = "/Update_cancel", method = RequestMethod.POST,
            headers = {"Content-Type=application/json"},
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public String TBFPLANCancelForm(@RequestBody Map<String, Object> data,
                                    Model model, HttpServletRequest request) throws Exception {


        List<String> planNoList = (List<String>) data.get("planNoList");



        for(int i = 0; i < planNoList.size(); i++){

            popDto.setPlan_no(planNoList.get(i));

            themoonAppService.Update_tb_fplan(popDto);
            themoonAppService.delete_tb_fplan_work(popDto);
            themoonAppService.delete_tb_fplan_w030(popDto);
            themoonAppService.delete_tb_fplan_owork(popDto);
            themoonAppService.delete_tb_fplan_wperid(popDto);
            themoonAppService.delete_tb_fplan_wtime(popDto);

        }
        log.info(popDto.getPlan_no());





        return "success";
    }


    /** 재고실사 조회*/
    @RequestMapping(value="/list04",method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object Appcom03_index(@RequestParam Map<String, String> param
            , Model model
            , HttpServletRequest request) throws Exception{
        param.forEach((key, values) -> {
            switch (key){
                case "dbnm":
                    popDto.setDbnm(values.toString());
                    break;
                case "gs_today":
                    popDto.setGs_today(values.toString());
                    break;
                case "code88":
                    popDto.setCode88(values.toString().replace("\n", ""));
                    break;
                default:
                    break;
            }
        });

        log.info(popDto.getGs_today()  + " : Gs_today");
        log.info(popDto.getCode88()    + " : Code88");


        lists = themoonAppService.select_tb_ca630(popDto);


        return lists;
    }




    /**재고실사  저장*/
    @RequestMapping(value = "/Insert_tb_ca630", method = RequestMethod.POST,
            headers = {"Content-Type=application/json"},
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public String TBCA630UpdateForm(@RequestBody Map<String, Object> data,
                                    Model model, HttpServletRequest request) throws Exception {


        String closePerid = data.get("close_perid").toString();
        String close_date = data.get("gs_today").toString();

        List<String> pcodeList = (List<String>) data.get("pcodeList");
        List<String> jaeqtyList = (List<String>) data.get("jaeqtyList");
        List<String> silqty = (List<String>) data.get("silqty");
        List<String> lotno = (List<String>) data.get("lotnoList");




        for(int i=0; i < pcodeList.size(); i++){

            popDto.setGs_today(close_date);
            popDto.setGs_perid(closePerid);
            popDto.setPcode(pcodeList.get(i));
            popDto.setJaeqty(jaeqtyList.get(i));
            popDto.setSilqty(silqty.get(i));
            popDto.setLotno(lotno.get(i));

            log.info(pcodeList.size() + " 사이즈");

            List<ThemoonListDto2> list01Dto = new ArrayList<>();

            list01Dto = themoonAppService.insert_check(popDto);


            if(list01Dto.isEmpty()){
                themoonAppService.insert_tb_ca630(popDto);
                log.info("1");
            }else{
                themoonAppService.Update_tb_ca630Int(popDto);
                log.info("2");
            }


        }

        return "success";
    }



}
