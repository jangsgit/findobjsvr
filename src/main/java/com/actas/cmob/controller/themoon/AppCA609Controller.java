package com.actas.cmob.controller.themoon;

import com.actas.cmob.DTO.Themoon.CA609Dto;
import com.actas.cmob.DTO.Themoon.CA609PopDto;
import com.actas.cmob.Service.themoon.CA609Service;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

// @RestController : return을 텍스트로 반환함.

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/ca609", method = RequestMethod.POST)
public class AppCA609Controller {
    protected Log log =  LogFactory.getLog(this.getClass());
    CA609Dto ca609Dto = new CA609Dto();
    CA609Dto authCA609Dto = new CA609Dto();
    CA609PopDto popDto = new CA609PopDto();

    List<CA609Dto> list = new ArrayList<>();
    private final CA609Service authService;


    String result;

    String result2;

    String result3;

    String result4;

    String ll_ctn;
    String ll_cnt1;
    String ll_cnt2;
    String ll_cnt3;

    String ls_qcflag2;
    String ls_ibgflag2;



    // 바코드 스캔
    @RequestMapping(value="/list01",method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object getList01(@RequestParam Map<String, String> param
            , Model model
            , HttpServletRequest request) throws Exception{
        param.forEach((key, values) -> {
            switch (key){
                case "dbnm":
                    popDto.setDbnm(values.toString());
                    break;
                case "code88":
                    popDto.setLotno(values.toString());
                case "custcd":
                    popDto.setCustcd(values.toString());
                    ca609Dto.setCustcd(values.toString());
                default:
                    break;
            }
        });

        //바코드를 스캔하여 DTO 정보를 불러옴
        list = authService.GetBarcodeInfo(popDto);



        return list;
    }

    @RequestMapping(value="/list02",method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object getList02(@RequestParam Map<String, String> param
            , Model model
            , HttpServletRequest request) throws Exception{
        param.forEach((key, values) -> {
            switch (key){
                case "dbnm":
                    ca609Dto.setDbnm(values.toString());
                    log.info(ca609Dto.getDbnm());
                    break;
                case "custcd":
                    ca609Dto.setCustcd(values.toString());
                    break;
                case "spjangcd":
                    ca609Dto.setSpjangcd(values.toString());
                    break;
                case "pcode":
                    ca609Dto.setPcode(values.toString());
                    log.info(ca609Dto.getPcode());
                    break;
                default:
                    break;
            }
        });

        log.info("aaaa 진입");
        log.info(ca609Dto);

        if(ca609Dto.getQcdate() == null){
            ca609Dto.setQcdate(getToDate());
        }
        list = authService.GetQtyInfo(ca609Dto);

        log.info(list);

        return list;
    }


    /**수입검사 저장*/
    @RequestMapping(value = "/Update_TB_CA", method = RequestMethod.POST,
            headers = {"Content-Type=application/json"},
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public String TBFPLANUpdateForm(@RequestBody Map<String, Object> data,
                                    Model model, HttpServletRequest request) throws Exception {


        String gsPerid = data.get("gs_perid").toString();
        String gsToday = data.get("gs_today").toString();



        List<String> as_baldate = (List<String>) data.get("as_baldate");
        List<String> as_balnum = (List<String>) data.get("as_balnum");
        List<String> as_cltcd = (List<String>) data.get("as_cltcd");
        List<String> as_divicd = (List<String>) data.get("as_divicd");
        List<String> as_store = (List<String>) data.get("as_store");
        List<String> as_ischdate = (List<String>) data.get("as_ischdate");
        List<String> as_comcd = (List<String>) data.get("as_comcd");
        List<String> as_pcode = (List<String>) data.get("as_pcode");
        List<String> as_pname = (List<String>) data.get("as_pname");
        List<String> as_psize = (List<String>) data.get("as_psize");
        List<String> as_punit = (List<String>) data.get("as_punit");
        List<String> ae_qty = (List<String>) data.get("ae_qty");
        List<String> as_qcdv = (List<String>) data.get("as_qcdv");
        List<String> as_balseq = (List<String>) data.get("as_balseq");
        List<String> as_wqcqty = (List<String>) data.get("ae_wqcqty");





        for(int i = 0; i < 1; i++){



            popDto.setGs_today(gsToday);
            result = authService.select_tb_CA623(popDto);

            int ll_result = 0;

            if (result == "0000") {
                result = "0001";
            } else {
                ll_result = Integer.parseInt(result);
                ll_result++;
                result = String.format("%04d", ll_result);

            }

            popDto.setGs_today(gsToday);
            popDto.setCltcd(as_cltcd.get(i));
            popDto.setGs_perid(gsPerid);
            popDto.setAs_qcnum(result);
            popDto.setGs_divicd(as_divicd.get(i));
            popDto.setAs_store(as_store.get(i));
            popDto.setAs_ischdate(as_ischdate.get(i));
            popDto.setAs_cltcd(as_cltcd.get(i));
            popDto.setAs_baldate(as_baldate.get(i));
            popDto.setAs_balnum(as_balnum.get(i));
            popDto.setAs_comcd(as_comcd.get(i));


//                log.info(popDto.getGs_today() + " Gstoday");
//                log.info(popDto.getCltcd() + " Cltcd");
//                log.info(popDto.getGs_perid() + " gs_perid");
//                log.info(popDto.getGs_divicd() + " gs_divicd");
//                log.info(popDto.getAs_store() + " store");
//                log.info(popDto.getAs_ischdate() + " ischdate");
//                log.info(popDto.getAs_baldate() + " baldate");
//                log.info(popDto.getAs_balnum() + " balnum");
//                log.info(popDto.getAs_comcd() + " comcd");
//                log.info(popDto.getAs_qcnum() + " qcnum");

            authService.Insert_TB_CA623(popDto);
        }




        for(int i=0; i < as_pcode.size(); i++){

            popDto.setGs_today(gsToday);
            result3 =  authService.select_tb_CA624(popDto);

            int ll_result = 0;

            if(result3.equals("000")){
                result3 = "001";
            }else{
                ll_result = Integer.parseInt(result3);
                ll_result++;
                result3 = String.format("%03d", ll_result);
            }



            popDto.setAs_pcode(as_pcode.get(i));
            popDto.setAs_cltcd(as_cltcd.get(i));
            result2 = authService.select_DF_DANGA(popDto);
            popDto.setAe_uamt(result2);
//            popDto.setAs_qcnum(CountNum(gsToday));
            popDto.setAs_qcseq(result3);
            popDto.setAs_pname(as_pname.get(i));
            popDto.setAs_psize(as_psize.get(i));
            popDto.setAs_punit(as_punit.get(i));
            popDto.setAe_qty(ae_qty.get(i));
            popDto.setAs_qcdv(as_qcdv.get(i));
            popDto.setAs_balseq(as_balseq.get(i));
            popDto.setAs_baldate(as_baldate.get(i));
            popDto.setAs_balnum(as_balnum.get(i));
            popDto.setAs_ischdate(as_ischdate.get(i));
            popDto.setGs_perid(gsPerid);


            log.info(as_pcode.get(i) + " : pcode");
            log.info(as_cltcd.get(i) + " : cltcd");
            log.info(popDto.getAe_uamt() + " : uamt");
            log.info(popDto.getAs_qcnum() + " : qcnum");
            log.info(popDto.getAs_qcseq() + " : qcseq");
            log.info(as_pname.get(i)       + ": pname");
            log.info(as_psize.get(i)       + ": psize");
            log.info(as_punit.get(i)       + ": punit");
            log.info(ae_qty.get(i)         + ": qty");
            log.info(as_qcdv.get(i)        + ": qcdv");
            log.info(as_balseq.get(i)      + ": balseq");
            log.info(as_baldate.get(i)     + ": baldate");
            log.info(as_balnum.get(i)      + ": balnum");
            log.info(as_ischdate.get(i)    + ": ischdate");
            log.info(popDto.getGs_perid()  + ": perid");

            authService.Insert_TB_CA624(popDto);



        }

        for(int i=0; i< as_wqcqty.size(); i++){

            popDto.setWmqty(as_wqcqty.get(i));
            popDto.setAs_baldate(as_baldate.get(i));
            popDto.setAs_balnum(as_balnum.get(i));
            popDto.setAs_balseq(as_balseq.get(i));

            list = authService.select_tb_ca608_head(popDto);


//            log.info(list.get(0).getLl_cnt());
//            log.info(list.get(0).getLl_cnt1());
//            log.info(list.get(0).getLl_cnt2());
//            log.info(list.get(0).getLl_cnt3());
//            log.info(list.get(0).getLl_cnt4());
//            log.info(as_wqcqty.get(i) + " : wqcqty");
//            log.info(as_baldate.get(i) + " : baldate");
//            log.info(as_balnum.get(i) + " : balnum");
//            log.info(as_balseq.get(i) + " : balseq");




            if(list.get(0).getLl_cnt() > list.get(0).getLl_cnt2()) {
                if (list.get(0).getLl_cnt1() > 0) {
                    ls_qcflag2 = "1";
                } else {
                    if (list.get(0).getLl_cnt2() > 0) {
                        ls_qcflag2 = "1";
                    } else {
                        ls_qcflag2 = "0";
                    }
                }
            }else{
                ls_qcflag2 = "2";
            }

            if(list.get(0).getLl_cnt() > list.get(0).getLl_cnt4()){
                if(list.get(0).getLl_cnt3() > 0){
                    ls_ibgflag2 = "1";
                }else{
                    if(list.get(0).getLl_cnt4() > 0){
                        ls_ibgflag2 = "1";
                    }else{
                        ls_ibgflag2 = "0";
                    }
                }

            }else{
                ls_ibgflag2 = "2";
            }

            popDto.setLs_qcflag(ls_qcflag2);
            popDto.setLs_ibgflag(ls_ibgflag2);

//            log.info(popDto.getLs_qcflag() + " : qcflg");
//            log.info(popDto.getLs_ibgflag() + " : ibgflg");


            authService.Update_TB_CA609(popDto);
            authService.Update_TB_CA608(popDto);
        }


        return "success";
    }


    @RequestMapping(value="/list03",method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object getList03(@RequestParam Map<String, String> param
            , Model model
            , HttpServletRequest request) throws Exception{
        param.forEach((key, values) -> {
            switch (key){
                case "dbnm":
                    ca609Dto.setDbnm(values.toString());
                case "custcd":
                    ca609Dto.setCustcd(values.toString());
                case "spjangcd":
                    ca609Dto.setSpjangcd(values.toString());
                case "qcdate":
                    ca609Dto.setQcdate(values.toString());
                default:
                    break;
            }
        });

        if(ca609Dto.getQcdate() == null){
            ca609Dto.setQcdate(getToDate());
        }
        System.out.println(ca609Dto.getDbnm());
        System.out.println(ca609Dto.getCustcd());
        System.out.println(ca609Dto.getSpjangcd());
        System.out.println(ca609Dto.getQcdate());

        list = authService.GetNowList(ca609Dto);

        for(int i=0; i<list.size(); i++){
            System.out.println(list.get(i).getPname());
        }

        return list;
    }

    // 업데이트(body)
    @RequestMapping(value="/updateBody",method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object UpdateBalInfoBody(@RequestParam Map<String, String> param
            , Model model
            , HttpServletRequest request) throws Exception{
        param.forEach((key, values) -> {
            switch (key){
                case "dbnm":
                    ca609Dto.setDbnm(values.toString());
                    break;
                case "custcd":
                    ca609Dto.setCustcd(values.toString());
                case "spjangcd":
                    ca609Dto.setSpjangcd(values.toString());
                case "qcflag":
                    ca609Dto.setQcflag(values.toString());
                case "ibgflag":
                    ca609Dto.setIbgflag(values.toString());
                case "baldate":
                    ca609Dto.setBaldate(values.toString());
                case "balnum":
                    ca609Dto.setBalnum(values.toString());
                default:
                    break;
            }
        });


        authService.UpdateBal(ca609Dto);

        return null;
    }

    // 업데이트(head)
    @RequestMapping(value="/updateHead",method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String UpdateBalInfoHead(@RequestParam Map<String, String> param
            , Model model
            , HttpServletRequest request) throws Exception{
        param.forEach((key, values) -> {
            switch (key){
                case "dbnm":
                    ca609Dto.setDbnm(values.toString());
                    break;
                case "custcd":
                    ca609Dto.setCustcd(values.toString());
                case "spjangcd":
                    ca609Dto.setSpjangcd(values.toString());
                case "qcflag":
                    ca609Dto.setQcflag(values.toString());
                case "ibgflag":
                    ca609Dto.setIbgflag(values.toString());
                case "baldate":
                    ca609Dto.setBaldate(values.toString());
                case "balnum":
                    ca609Dto.setBalnum(values.toString());
                default:
                    break;
            }
        });


        authService.UpdateBalInfoHead(ca609Dto);

        return "success";
    }



    // 로그
    @RequestMapping(value="/insertLog",method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object InsertLog(@RequestParam Map<String, String> param
            , Model model
            , HttpServletRequest request) throws Exception{
        param.forEach((key, values) -> {
            switch (key){
                case "dbnm":
                    ca609Dto.setDbnm(values.toString());
                    break;
                case "custcd":
                    ca609Dto.setCustcd(values.toString());
                case "spjangcd":
                    ca609Dto.setSpjangcd(values.toString());
                case "userid":
                    ca609Dto.setUserid(values.toString());
                case "winid":
                    ca609Dto.setWinid(values.toString());
                case "ipaddr":
                    ca609Dto.setIpaddr(values.toString());
                case "usernm":
                    ca609Dto.setUsernm(values.toString());
                case "winnm":
                    ca609Dto.setWinnm(values.toString());
                case "buton":
                    ca609Dto.setButon(values.toString());
                default:
                    break;
            }
        });


        authService.InsertLog(ca609Dto);

        return null;
    }

    // deleteQc
    @RequestMapping(value="/delqc",method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String DeleteQcData(@RequestParam Map<String, String> param,
                               @RequestParam Map<String, List<String>> paramList
            , Model model
            , HttpServletRequest request) throws Exception{
        System.out.println("delqc 진입------------------------");

        param.forEach((key, values) -> {
            switch (key){
                case "dbnm":
                    ca609Dto.setDbnm(values.toString());
                    break;
                case "custcd":
                    ca609Dto.setCustcd(values.toString());
                    break;
                case "spjangcd":
                    ca609Dto.setSpjangcd(values.toString());
                    break;
                case "baldate":
                    ca609Dto.setBaldateList(values.toString());
                    break;
                case "balnum":
                    ca609Dto.setBalnumList(values.toString());
                    break;
                case "balseq":
                    ca609Dto.setBalseqList(values.toString());
                    break;
                case "wqty":
                    ca609Dto.setWqtyList(values.toString());
                    break;
                case "qcdate":
                    ca609Dto.setQcdateList(values.toString());
                    break;
                case "qcnum":
                    ca609Dto.setQcnumList(values.toString());
                    break;
                case "qcseq":
                    ca609Dto.setQcseqList(values.toString());
                default:
                    break;
            }
        });
        System.out.println("param 저장------------------------");

        List<String> baldateList = Arrays.asList(ca609Dto.getBaldateList().split(","));
        List<String> balnumList = Arrays.asList(ca609Dto.getBalnumList().split(","));
        List<String> balseqList = Arrays.asList(ca609Dto.getBalseqList().split(","));
        List<String> wqtyList = Arrays.asList(ca609Dto.getWqtyList().split(","));
        List<String> qcdateList = Arrays.asList(ca609Dto.getQcdateList().split(","));
        List<String> qcnumList = Arrays.asList(ca609Dto.getQcnumList().split(","));
        List<String> qcseqList = Arrays.asList(ca609Dto.getQcseqList().split(","));

        for(int i=0; i<baldateList.size(); i++){
            ca609Dto.setBaldate(baldateList.get(i));
            ca609Dto.setBalnum(balnumList.get(i));
            ca609Dto.setBalseq(balseqList.get(i));
            ca609Dto.setWqty(wqtyList.get(i));
            ca609Dto.setQcdate(qcdateList.get(i));
            ca609Dto.setQcnum(qcnumList.get(i));
            ca609Dto.setQcseq(qcseqList.get(i));
//            System.out.println(ca609Dto.getBaldate());
//            System.out.println(ca609Dto.getBalnum());
//            System.out.println(ca609Dto.getBalseq());
//            System.out.println(ca609Dto.getWqty());
//            System.out.println(ca609Dto.getQcdate());
//            System.out.println(ca609Dto.getQcnum());

            authCA609Dto = authService.GetQcqty(ca609Dto);

            if(authCA609Dto.getBalqty() == authCA609Dto.getQcqty()){
                ca609Dto.setQcflag("0");
                ca609Dto.setQcqty(0);
            }else{
                if(authCA609Dto.getQcqty() > Integer.parseInt(ca609Dto.getWqty())){
                    if(authCA609Dto.getBalqty() > (authCA609Dto.getQcqty() - Integer.parseInt(ca609Dto.getWqty()))){
                        ca609Dto.setQcflag("1");
                    }else{
                        ca609Dto.setQcflag("2");
                    }
                    ca609Dto.setQcqty(authCA609Dto.getQcqty() - Integer.parseInt(ca609Dto.getWqty()));
                }else{
                    ca609Dto.setQcflag("0");
                    ca609Dto.setQcqty(0);
                }
            }

            authService.UpdateQcflag(ca609Dto);

            authCA609Dto = authService.GetCntData(ca609Dto);

            if(authCA609Dto.getLl_cnt() > authCA609Dto.getLl_cnt2()){
                if(authCA609Dto.getLl_cnt1() > 0){
                    ca609Dto.setQcflag("1");
                }else{
                    if(authCA609Dto.getLl_cnt2() > 0){
                        ca609Dto.setQcflag("1");
                    }else{
                        ca609Dto.setQcflag("0");
                    }
                }
            }else{
                ca609Dto.setQcflag("2");
            }

            if(authCA609Dto.getLl_cnt() > authCA609Dto.getLl_cnt4()){
                if(authCA609Dto.getLl_cnt3() > 0){
                    ca609Dto.setIbgflag("1");
                }else{
                    if(authCA609Dto.getLl_cnt4() > 0){
                        ca609Dto.setIbgflag("1");
                    }else{
                        ca609Dto.setIbgflag("0");
                    }
                }
            }else{
                ca609Dto.setIbgflag("2");
            }

            authService.DeleteBalBody(ca609Dto);
            authService.DeleteBalHead(ca609Dto);

        }


        return "success";
    }



    private String getToDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date      = new Date(System.currentTimeMillis());

        return formatter.format(date);
    }

    public String CountNum(String compdate){


        String ls_compnum = authService.Maxseq(compdate);
        int ll_compnum = 0;
        if(ls_compnum == null){
            ls_compnum = "0001";
        }else{
            ll_compnum = Integer.parseInt(ls_compnum);
            log.info(ll_compnum);
            if(ll_compnum > 9) {
                ls_compnum = "00" + Integer.toString(ll_compnum + 01);
            }else{
                ls_compnum = "000" + Integer.toString(ll_compnum + 01);
            }
        }
        return ls_compnum;
    }




}
