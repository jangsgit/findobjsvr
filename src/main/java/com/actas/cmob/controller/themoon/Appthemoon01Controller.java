package com.actas.cmob.controller.themoon;


import com.actas.cmob.DTO.Kosep.KosepDa037Dto;
import com.actas.cmob.DTO.Kosep.KosepDa037HDto;
import com.actas.cmob.DTO.Kosep.KosepList01Dto;
import com.actas.cmob.DTO.Themoon.*;
import com.actas.cmob.DTO.UserFormDto;
import com.actas.cmob.Service.kosep.KosepAppService;
import com.actas.cmob.Service.themoon.ThemoonAppService;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    List<ThemoonListDto2> list03Dto = new ArrayList<>();
    PopDto popDto3 = new PopDto();

    PopDto popDto4 = new PopDto();

    PopDto popDto5 = new PopDto();

    PopDto popDto6 = new PopDto();

    PopDto popDto7 = new PopDto();

    List<ThemoonListDto> list01Dto = new ArrayList<>();

    List<PopDto> popDtoList = new ArrayList<>();

    List<PopDto> popDtoList2 = new ArrayList<>();


    List<DIVICDDto> divicdDtoList = new ArrayList<>();

    List<DA036Dto> da036DtoList = new ArrayList<>();
    List<DA099Dto> da099DtoList = new ArrayList<>();
    List<CA602_01> CA602_01List = new ArrayList<>();





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

                log.info(loginLogDto.getUserid() + " id");
                log.info(loginLogDto.getOnoffdt() + " onoffdt");
                log.info(loginLogDto.getIpaddr() + " ipaddr");

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
                case "wendt":
                    popDto.setWendt(values.toString());
                    break;
                default:
                    break;
            }
        });

        log.info(popDto.getCode88() + " Code88");

        list01Dto = themoonAppService.TB_CA501list(popDto);
        log.info(list01Dto.get(0).getPhm_pcod() + "aa");
        popDto.setPcode(list01Dto.get(0).getPhm_pcod());



        if(list03Dto.isEmpty()){
            return list03Dto;
       }
        if(!list03Dto.isEmpty()){

            list01Dto.get(0).setWfokqt_sum(list03Dto.get(0).getWfokqt_sum());
            return list01Dto;
        }
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

        String time = "";

        String dbnm = data.get("dbnm").toString();
        String closePerid = data.get("close_perid").toString();
        String close_date = data.get("close_date").toString();

        List<String> wokqtList = (List<String>) data.get("wokqtList");
        List<String> planNoList = (List<String>) data.get("planNoList");
        List<String> wonoList = (List<String>) data.get("wonolist");
        List<String> lotnoList = (List<String>) data.get("lotnoList");
        List<String> pcodeList = (List<String>) data.get("pcodeList");
        List<String> endqtyList = (List<String>) data.get("end_qty");

        time = themoonAppService.TimeCheck(popDto);




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
            popDto2.setTime(time);

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
            popDto3.setTime(time);

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
            popDto6.setGs_today(close_date);
            popDto6.setTime(time);
           themoonAppService.INSERT_TB_FPLAN_WTIME(popDto6);

        }

        log.info(popDto6.getPlan_no() + " 6번째, Plan_no");
        log.info(popDto6.getGs_today());

        for(int i=0; i< planNoList.size(); i++){
            popDto7.setPlan_no(planNoList.get(i));
            popDto7.setGs_today(close_date);
            popDto7.setClose_perid(closePerid);
            popDto7.setWotqt(endqtyList.get(i));
            themoonAppService.insert_tb_fplan_sub(popDto7);

        }

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
                    loginLogDto.setUserid(values.toString().replaceAll("p",""));
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
            themoonAppService.delete_tb_fplan_sub(popDto);

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


            List<ThemoonListDto2> list011Dto = new ArrayList<>();
            list011Dto = themoonAppService.insert_check(popDto);


            if(!list011Dto.isEmpty()){
                if(list011Dto.get(0).getBanflag().contains("1")){
                    return "exist";
                }
            }


        }

        for(int i=0; i < pcodeList.size(); i++){

            popDto.setGs_today(close_date);
            popDto.setGs_perid(closePerid);
            popDto.setPcode(pcodeList.get(i));
            popDto.setJaeqty(jaeqtyList.get(i));
            popDto.setSilqty(silqty.get(i));
            popDto.setLotno(lotno.get(i));

            log.info(pcodeList.size() + " 사이즈");

            log.info(popDto.getGs_today() + " " +  i +"번쨰");
            log.info(popDto.getPcode() + " " + i + "번째");
            log.info(popDto.getJaeqty() + " " + i + "번째");
            log.info(popDto.getSilqty() + " " + i + "번째");


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

    /**대분류 조회**/
    @RequestMapping(value = "pop_Main", method = RequestMethod.POST, headers = ("content-type=multipart/*"),
    consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object POP_MAIN(@RequestParam Map<String, String> param, Model model, HttpServletRequest request)
    {


        String ls_contnm = "";

        param.forEach((key, values) -> {
            switch (key) {
                case "dbnm":
                    popDto.setDbnm(values.toString());
                    break;
                case "contnm":
                    popDto.setContnm(values.toString());
                    break;
                default:
                    break;

            }
        });
        if(ls_contnm.length() == 0){
            ls_contnm = "%";
        }

        try {
            //popDto.setContnm(ls_contnm);
            popDtoList = themoonAppService.GetContnmList(popDto);

        }catch (IllegalStateException e){
            return "error";
        }
        return popDtoList;
    }


    /**중분류 조회**/

    //  고장부위상세조회
    @RequestMapping(value = "/pop_Mid", method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object AppReginmList(@RequestParam Map<String, String> param
            , Model model
            , HttpServletRequest request){

        String ls_gregicd = "";
        String ls_reginm = "";
        param.forEach((key, values) -> {
            switch (key){
                case "dbnm":
                    popDto.setDbnm(values.toString());
                    break;
                case "hcod":
                    popDto.setHcod(values.toString());
                    break;
                default:
                    break;
            }
        });
        if(ls_reginm.length() == 0){
            ls_reginm = "%";
        }
        try {
            ls_gregicd = popDto.getHcod();
            int stCnt = ls_gregicd.indexOf('[') + 1 ;
            int etCnt = ls_gregicd.indexOf(']');

            ls_gregicd = ls_gregicd.substring(stCnt, etCnt);
            popDto.setHcod(ls_gregicd);
            popDtoList = themoonAppService.GetMidList(popDto);

        }catch (IllegalStateException e){
            return "error";
        }
        return popDtoList;
    }


    /**관리처 조회**/

    @RequestMapping(value = "pop_Per", method = RequestMethod.POST, headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object POP_PER(@RequestParam Map<String, String> param, Model model, HttpServletRequest request)
    {

        param.forEach((key, values) -> {
            switch (key) {
                case "dbnm":
                    popDto.setDbnm(values.toString());
                    break;
                default:
                    break;

            }
        });

        try {
            //popDto.setContnm(ls_contnm);
            popDtoList = themoonAppService.GetPerList(popDto);

        }catch (IllegalStateException e){
            return "error";
        }
        return popDtoList;
    }

    /**거래처 검색 **/
    @RequestMapping(value = "/tbe601list", method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public Object TBE601ListForm(@RequestParam Map<String, String> param,
                                 Model model, HttpServletRequest request) throws Exception{

        String ls_dbnm = "";


        param.forEach((key, values) -> {
            switch (key){
                case "dbnm":
                    popDto.setDbnm(values.toString());
                    break;
                case "cltnm":
                    popDto.setCltnm(values.toString());
                    break;
                default:
                    break;

            }
        });

                try{
                    popDtoList = themoonAppService.cltnmList(popDto);
                }catch (DataAccessException e){
                    log.info("App01001Tab01Form DataAccessException ================================================================");
                    log.info(e.toString());

                }catch (Exception ex) {
//                dispatchException = ex;
                    log.info("App01001Tab01Form Exception ================================================================");
                    log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
                }


        return popDtoList;
    }


    /**기간별매출현황 검색 **/
    @RequestMapping(value = "/da036list", method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public Object MainList(@RequestParam Map<String, String> param,
                                 Model model, HttpServletRequest request) throws Exception{

        String ls_dbnm = "";
        String extranctedValue = "";
        String extranctedValue2 = "";
        String extranctedValue3 = "";



        param.forEach((key, values) -> {
            switch (key){
                case "dbnm":
                    popDto.setDbnm(values.toString());
                    break;
                case "cltnm":
                    popDto.setCltnm(values.toString());
                    break;
                case "cltcd":
                    popDto.setCltcd(values.toString());
                    break;
                case "frdate":
                    popDto.setFrdate(values.toString().replaceAll("-",""));
                    break;
                case "todate":
                    popDto.setTodate(values.toString().replaceAll("-", ""));
                    break;
                case "spcd":
                    popDto.setSpcd(values.toString());
                    break;
                case "area":
                    popDto.setArea(values.toString());
                    break;
                case "comcd":
                    popDto.setComcd(values.toString());
                default:
                    break;

            }
        });

        String pattern = "\\[(\\d+)\\]";
        String pattern2 = "\\[(\\w+)\\]";
        Pattern compiledPattern = Pattern.compile(pattern);
        Pattern compiledPattern2 = Pattern.compile(pattern2);

        Matcher matcher = compiledPattern.matcher(popDto.getSpcd());
        Matcher matcher2 = compiledPattern.matcher(popDto.getArea());
        Matcher matcher3 = compiledPattern2.matcher(popDto.getComcd());

        if(matcher.find())
        {
            extranctedValue = matcher.group(1);

        }
        if(matcher2.find())
        {
            extranctedValue2 = matcher2.group(1);

        }
        if(matcher3.find())
        {
            extranctedValue3 = matcher3.group(1);

        }




        try{
            log.info(extranctedValue);
            log.info(extranctedValue2);
            log.info(extranctedValue3);



            popDto.setPS_FDATE(popDto.getFrdate());
            popDto.setPS_TDATE(popDto.getTodate());

            popDtoList = themoonAppService.GETDATE_PROC(popDto);
           /* log.info(popDtoList.get(0).getJfrdate() + "-1년");
            log.info(popDtoList.get(0).getJtodate() + "-1년");*/



            popDto.setPS_FDATE(popDto.getFrdate());
            popDto.setPS_TDATE(popDto.getTodate());
            popDto.setPS_JFDATE(popDtoList.get(0).getJfrdate());
            popDto.setPS_JTDATE(popDtoList.get(0).getJtodate());
            popDto.setPS_SPCD(extranctedValue);
            popDto.setPS_AREA(extranctedValue2);
            popDto.setPS_CLTCD(popDto.getCltcd());
            popDto.setPS_COMCD(extranctedValue3);

            if (popDto.getPS_AREA() == null || popDto.getPS_AREA() == "")
            {
                popDto.setPS_AREA("%");

            }
            if(popDto.getPS_SPCD() == null || popDto.getPS_SPCD() == "")
            {
                popDto.setPS_SPCD("%");
            }
            if(popDto.getPS_COMCD() == null || popDto.getPS_COMCD() == "")
            {
                popDto.setPS_COMCD("%");
            }
            if(popDto.getPS_CLTCD() == null || popDto.getPS_CLTCD() == "" || popDto.getCltnm() == "")
            {
                popDto.setPS_CLTCD("%");
            }

            log.info(popDto.getCltcd() + "1");
            log.info(popDto.getCltnm() + "2");
            log.info(popDto.getFrdate() + "3");
            log.info(popDto.getTodate() + "4");
            log.info(popDto.getSpcd() + "5");
            log.info(popDto.getArea() + "6");
            log.info(popDto.getComcd() + "7");


            log.info(popDto.getPS_FDATE() + " 1번째");
            log.info(popDto.getPS_TDATE() + " 2번째");
            log.info(popDto.getPS_JFDATE() + " 3번째");
            log.info(popDto.getPS_JTDATE() + " 4번째");
            log.info(popDto.getPS_SPCD() + " 5번째");
            log.info(popDto.getPS_AREA() + " 6번째");
            log.info(popDto.getPS_CLTCD() + " 7번째");
            log.info(popDto.getPS_COMCD() + " 8번째");

            da036DtoList = themoonAppService.Get_DA036_PROC(popDto);

            //popDtoList = themoonAppService.cltnmList(popDto);

        }catch (DataAccessException e){
            log.info("App01001Tab01Form DataAccessException ================================================================");
            log.info(e.toString());

        }catch (Exception ex) {
//                dispatchException = ex;
            log.info("App01001Tab01Form Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }


        return da036DtoList;
    }


    /**판매거래처현황 리스트 **/
    @RequestMapping(value = "/da099list", method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public Object MainList2(@RequestParam Map<String, String> param,
                           Model model, HttpServletRequest request) throws Exception{

        String extranctedValue = "";
        String extranctedValue2 = "";
        String extranctedValue3 = "";



        param.forEach((key, values) -> {
            switch (key){
                case "dbnm":
                    popDto.setDbnm(values.toString());
                    break;
                case "cltnm":
                    popDto.setCltnm(values.toString());
                    break;
                case "cltcd":
                    popDto.setCltcd(values.toString());
                    break;
                case "frdate":
                    popDto.setFrdate(values.toString().replaceAll("-",""));
                    break;
                case "todate":
                    popDto.setTodate(values.toString().replaceAll("-", ""));
                    break;
                case "comcd":
                    popDto.setComcd(values.toString());
                default:
                    break;

            }
        });


        String pattern2 = "\\[(\\w+)\\]";
        Pattern compiledPattern2 = Pattern.compile(pattern2);


        Matcher matcher3 = compiledPattern2.matcher(popDto.getComcd());


        if(matcher3.find())
        {
            extranctedValue3 = matcher3.group(1);

        }




        try{

            log.info(extranctedValue3);

            log.info(popDto.getCltcd());
            log.info(popDto.getCltnm());
            log.info(popDto.getFrdate());
            log.info(popDto.getTodate());
            log.info(popDto.getComcd());


            popDto.setPS_FDATE(popDto.getFrdate());
            popDto.setPS_TDATE(popDto.getTodate());
            popDto.setPS_CLTCD(popDto.getCltcd());
            popDto.setPS_COMCD(extranctedValue3);

            if(popDto.getPS_COMCD() == null || popDto.getPS_COMCD() == "")
            {
                popDto.setPS_COMCD("%");

            }
            log.info(popDto.getPS_CLTCD()+ " cltcd");
            log.info(popDto.getPS_COMCD() + " drive");

            da099DtoList = themoonAppService.Get_DA099_PROC(popDto);

        }catch (DataAccessException e){
            log.info("App01001Tab01Form DataAccessException ================================================================");
            log.info(e.toString());

        }catch (Exception ex) {
//                dispatchException = ex;
            log.info("App01001Tab01Form Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }


        return da099DtoList;
    }

    /**창고 리스트**/
    @RequestMapping(value = "store", method = RequestMethod.POST, headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object STORE(@RequestParam Map<String, String> param, Model model, HttpServletRequest request)
    {

        param.forEach((key, values) -> {
            switch (key) {
                case "dbnm":
                    popDto.setDbnm(values.toString());
                    break;
                default:
                    break;

            }
        });

        try {
            //popDto.setContnm(ls_contnm);
            popDtoList = themoonAppService.storelist(popDto);

        }catch (IllegalStateException e){
            return "error";
        }
        return popDtoList;
    }



    /**품목조회 리스트**/
    @RequestMapping(value = "pgun", method = RequestMethod.POST, headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object pgunlist(@RequestParam Map<String, String> param, Model model, HttpServletRequest request)
    {

        param.forEach((key, values) -> {
            switch (key) {
                case "dbnm":
                    popDto.setDbnm(values.toString());
                    break;
                default:
                    break;

            }
        });

        try {
            //popDto.setContnm(ls_contnm);
            popDtoList = themoonAppService.pgunlist(popDto);

        }catch (IllegalStateException e){
            return "error";
        }
        return popDtoList;
    }

    /**대분류 리스트**/
    @RequestMapping(value = "agrdlist", method = RequestMethod.POST, headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object agrdlist(@RequestParam Map<String, String> param, Model model, HttpServletRequest request)
    {

        String ls_gregicd = "";
        String ls_reginm = "";
        List<String> combineValues = new ArrayList<>();

        param.forEach((key, values) -> {
            switch (key){
                case "dbnm":
                    popDto.setDbnm(values.toString());
                    break;
                case "pgun":
                    popDto.setPgun3(values.toString());
                    break;
                default:
                    break;
            }
        });
        if(ls_reginm.length() == 0){
            ls_reginm = "%";
        }
        try {
            ls_gregicd = popDto.getPgun3();
            int stCnt = ls_gregicd.indexOf('[') + 1 ;
            int etCnt = ls_gregicd.indexOf(']');

            ls_gregicd = ls_gregicd.substring(stCnt, etCnt);

            popDto.setPgun3(ls_gregicd);
            popDtoList = themoonAppService.agrdlist(popDto);

            for (int i=0; i < popDtoList.size(); i++){
                String combineValue = popDtoList.get(i).getHcod() + popDtoList.get(i).getPgun();
                popDtoList.get(i).setCombinedValue(combineValue);

            }
        }catch (IllegalStateException e){
            return "error";
        }
        return popDtoList;
    }

    /**품목 검색 **/
    @RequestMapping(value = "/ca501list", method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public Object ca501list(@RequestParam Map<String, String> param,
                                 Model model, HttpServletRequest request) throws Exception{

        String ls_dbnm = "";


        param.forEach((key, values) -> {
            switch (key){
                case "dbnm":
                    popDto.setDbnm(values.toString());
                    break;
                case "pnam":
                    popDto.setPhm_pnam(values.toString());
                    break;
                default:
                    break;

            }
        });

        try{
            popDtoList = themoonAppService.pnamelist(popDto);
        }catch (DataAccessException e){
            log.info("App01001Tab01Form DataAccessException ================================================================");
            log.info(e.toString());

        }catch (Exception ex) {
//                dispatchException = ex;
            log.info("App01001Tab01Form Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }


        return popDtoList;
    }


    /**중분류 리스트**/
    @RequestMapping(value = "bgrdlist", method = RequestMethod.POST, headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object bgrdlist(@RequestParam Map<String, String> param, Model model, HttpServletRequest request)
    {

        String number = "";
        String letter = "";


        param.forEach((key, values) -> {
            switch (key){
                case "dbnm":
                    popDto.setDbnm(values.toString());
                    break;
                case "param":
                    popDto.setCombinedValue(values.toString());
                    break;
                default:
                    break;
            }
        });

        try {
            log.info(popDto.getCombinedValue());


            String pattern = "\\[(\\d+)([A-Za-z]+)\\]";
            Pattern compiledPattern = Pattern.compile(pattern);
            Matcher matcher = compiledPattern.matcher(popDto.getCombinedValue());

            if (matcher.find()) {
                // 첫 번째 그룹: 숫자
                 number = matcher.group(1);

                // 두 번째 그룹: 문자
                letter = matcher.group(2);

                // 추출된 값 출력
                log.info(number);
                log.info(letter);
            }

           popDto.setPgun2(letter);
           popDto.setHcod2(number);
           popDtoList = themoonAppService.bgrdlist(popDto);

        }catch (IllegalStateException e){
            return "error";
        }
        return popDtoList;
    }

    /**판매거래처현황 리스트 **/
    @RequestMapping(value = "/ca602_01", method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public Object MainList3(@RequestParam Map<String, String> param,
                            Model model, HttpServletRequest request) throws Exception{

        String extranctedValue = "";
        String extranctedValue2 = "";
        String extranctedValue3 = "";
        String extranctedValue4 = "";
        String extractedText = "";




        param.forEach((key, values) -> {
            switch (key){
                case "dbnm":
                    popDto.setDbnm(values.toString());
                    break;
                case "store":
                    popDto.setPS_STORE(values.toString());
                    break;
                case "pgun":
                    popDto.setPS_PGUN(values.toString());
                    break;
                case "agrb":
                    popDto.setPS_AGRB(values.toString());
                    break;
                case "bgrb":
                    popDto.setPS_BGRB(values.toString());
                    break;
                case "pcode":
                    popDto.setPS_PCODE(values.toString());
                    break;
                default:
                    break;

            }
        });


        String ls_gregicd = popDto.getPS_AGRB();
        int stCnt = ls_gregicd.indexOf('[') + 1 ;
        int etCnt = stCnt + 1;

        ls_gregicd = ls_gregicd.substring(stCnt, etCnt);
        popDto.setPS_AGRB(ls_gregicd);

        String pattern = "\\[(\\w+)\\]";


        Pattern compiledPattern = Pattern.compile(pattern);
        Pattern compiledPattern2 = Pattern.compile(pattern);
      //  Pattern compiledPattern3 = Pattern.compile(pattern);
        Pattern compiledPattern4 = Pattern.compile(pattern);


        Matcher matcher = compiledPattern.matcher(popDto.getPS_STORE());
        Matcher matcher2 = compiledPattern2.matcher(popDto.getPS_PGUN());
       // Matcher matcher3 = compiledPattern3.matcher(popDto.getPS_AGRB());
        Matcher matcher4 = compiledPattern4.matcher(popDto.getPS_BGRB());



        if(matcher.find())
        {
            extranctedValue = matcher.group(1);

        }
        if(matcher2.find())
        {
            extranctedValue2 = matcher2.group(1);

        }

        if(matcher4.find())
        {
            extranctedValue4 = matcher4.group(1);

        }



        try{

            popDto.setPS_STORE(extranctedValue);
            popDto.setPS_PGUN(extranctedValue2);

            popDto.setPS_BGRB(extranctedValue4);


            if(popDto.getPS_PGUN() == null || popDto.getPS_PGUN() == "")
            {
                popDto.setPS_PGUN("%");

            }

            if(popDto.getPS_AGRB() == null || popDto.getPS_AGRB() == "")
            {
                popDto.setPS_AGRB("%");

            }
            if(popDto.getPS_BGRB() == null || popDto.getPS_BGRB() == "")
            {
                popDto.setPS_BGRB("%");

            }
            if(popDto.getPS_PCODE() == null || popDto.getPS_PCODE() == "")
            {
                popDto.setPS_PCODE("%");

            }

            LocalDate today = LocalDate.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String formattedDate = today.format(formatter);

            LocalDate futureDate = today.plusDays(3);

            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyyMMdd");
            String formattedDate2 = futureDate.format(formatter);


            log.info(formattedDate + " 오늘날짜");
            popDto.setGs_today(formattedDate);
            popDto.setLs_store(popDto.getPS_STORE());
            popDto.setLs_pcode(popDto.getPS_PCODE());


            log.info(popDto.getGs_today() + " hh2");
            log.info(popDto.getLs_pcode() + " hh");
            log.info(popDto.getLs_store() + " jj");


            popDtoList2 = themoonAppService.Get_date_proc(popDto);

            popDto.setPS_SDATE(popDtoList2.get(0).getPS_SDATE());

            popDto.setPS_TODAY(formattedDate2);




            log.info(popDto.getPS_STORE() +"  1");
            log.info(popDto.getPS_PGUN() + "   2");
            log.info(popDto.getPS_AGRB() + "   3");
            log.info(popDto.getPS_BGRB() + "   4");
            log.info(popDto.getPS_PCODE() +"  5");
            log.info(popDto.getPS_SDATE()+"   6");
            log.info(popDto.getPS_TODAY()+"  7");



            CA602_01List = themoonAppService.Get_CA602_01_PROC(popDto);

        }catch (DataAccessException e){
            log.info("App01001Tab01Form DataAccessException ================================================================");
            log.info(e.toString());

        }catch (Exception ex) {
//                dispatchException = ex;
            log.info("App01001Tab01Form Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }


        return CA602_01List;
    }

    /**생산부서 조화**/

    /**관리처 조회**/

    @RequestMapping(value = "TB_JC002", method = RequestMethod.POST, headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object TB_JC002(@RequestParam Map<String, String> param, Model model, HttpServletRequest request)
    {

        param.forEach((key, values) -> {
            switch (key) {
                case "dbnm":
                    popDto.setDbnm(values.toString());
                    break;
                default:
                    break;

            }
        });

        try {
            //popDto.setContnm(ls_contnm);
            popDtoList = themoonAppService.TB_JC002(popDto);

        }catch (IllegalStateException e){
            return "error";
        }
        return popDtoList;
    }



    /**기간별 생산부서별 생산현황 리스트 **/
    @RequestMapping(value = "/tb_last", method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public Object tb_last(@RequestParam Map<String, String> param,
                            Model model, HttpServletRequest request) throws Exception{

        String extranctedValue = "";
        String extranctedValue2 = "";
        String extranctedValue3 = "";
        String extranctedValue4 = "";
        String extractedText = "";




        param.forEach((key, values) -> {
            switch (key){
                case "dbnm":
                    popDto.setDbnm(values.toString());
                    break;
                case "ps_fdate":
                    popDto.setPS_FDATE(values.toString().replaceAll("-",""));
                    break;
                case "ps_tdate":
                    popDto.setPS_TDATE(values.toString().replaceAll("-", ""));
                    break;
                case "divicd":
                    popDto.setPS_DIVICD(values.toString());
                    break;

                default:
                    break;

            }
        });

        String pattern = "\\[(\\w+)\\]";

        Pattern compiledPattern = Pattern.compile(pattern);



        Matcher matcher = compiledPattern.matcher(popDto.getPS_DIVICD());




        if(matcher.find())
        {
            extranctedValue = matcher.group(1);

        }

        popDto.setPS_DIVICD(extranctedValue);




        try{


            LocalDate today = LocalDate.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String formattedDate = today.format(formatter);

            popDto.setGs_today(formattedDate);
            popDto.setLs_pcode("%");
            popDto.setLs_store("P01");

            popDtoList = themoonAppService.Get_date_proc(popDto);

            popDto.setPS_SDATE(popDtoList.get(0).getPS_SDATE());


            if(popDto.getPS_DIVICD() == null || popDto.getPS_DIVICD() == "")
            {
                popDto.setPS_DIVICD("%");
            }
            log.info(popDto.getPS_DIVICD());
            log.info(popDto.getPS_TDATE());
            log.info(popDto.getPS_FDATE());
            log.info(popDto.getPS_SDATE());

            divicdDtoList = themoonAppService.SP_PLAN_WORK_DIVI(popDto);


        }catch (DataAccessException e){
            log.info("App01001Tab01Form DataAccessException ================================================================");
            log.info(e.toString());

        }catch (Exception ex) {
//                dispatchException = ex;
            log.info("App01001Tab01Form Exception ================================================================");
            log.info("Exception =====>" + ex.toString());
//            log.debug("Exception =====>" + ex.toString() );
        }


        return divicdDtoList;
    }
}
