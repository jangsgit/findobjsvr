package com.actas.cmob.controller.kosep;

import com.actas.cmob.DTO.Kosep.*;
import com.actas.cmob.Service.kosep.KosepAppService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

// @RestController : return을 텍스트로 반환함.

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/kosep", method = RequestMethod.POST)
public class Appkosep01Controller {
    protected Log log =  LogFactory.getLog(this.getClass());
    KosepPopDto popDto = new KosepPopDto();
    KosepDa036Dto da036Dto = new KosepDa036Dto();
    List<KosepDa037Dto> da037ListDto = new ArrayList<>();
    KosepDa037Dto da037Dto = new KosepDa037Dto();
    KosepDa037Dto da037LotDto = new KosepDa037Dto();
    KosepDa037HDto da037HDto = new KosepDa037HDto();
    List<KosepList01Dto> list01Dto = new ArrayList<>();
    private final KosepAppService authService;



//  //출고예정조회
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
                    case "todate":
                        popDto.setTodate(values.toString());
                    default:
                        break;
                }
            });
            list01Dto = authService.GetTBDA035List(popDto);
        return list01Dto;
    }


    //  //출고등록
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class, SQLException.class})
    @RequestMapping(value="/list02",method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object Appcom02_index(@RequestParam Map<String, String> param
            , Model model
            , HttpServletRequest request) throws Exception{
        String ls_itemcd = "";
        param.forEach((key, values) -> {
            switch (key){
                case "dbnm":
                    popDto.setDbnm(values.toString());
                    break;
                case "fdeldate":
                    popDto.setFdeldate(values.toString());
                    break;
                case "fdelnum":
                    popDto.setFdelnum(values.toString());
                    break;
                case "fdelseq":
                    popDto.setFdelseq(values.toString());
                    break;
                case "cltcd":
                    popDto.setFdelcltcd(values.toString());
                    break;
                case "pcode":
                    popDto.setFdelpcode(values.toString());
                    break;
                case "itemcd":
                    popDto.setItemcd(values.toString());
                    break;
                case "width":
                    popDto.setWidth(values.toString());
                    break;
                case "thick":
                    popDto.setThick(values.toString() + "00");
                    break;
                case "grade":
                    popDto.setGrade(values.toString());
                    break;
                case "color":
                    popDto.setColor(values.toString());
                    break;
                case "perid":
                    popDto.setPerid(values.toString());
                    break;
                case "qty":
                    popDto.setQty(values.toString());
                    break;
                case "uamt":
                    popDto.setUamt(values.toString());
                    break;
                default:
                    break;
            }
        });
        String ls_perid = popDto.getPerid();
        popDto.setPerid(ls_perid.substring(1));

        String ls_uamt = popDto.getUamt();
        String[] listUamt = ls_uamt.split("[.]");
        if (listUamt.length == 0){
            listUamt[0] = "0";
        }

        String ls_qty = popDto.getQty();
        String[] listQty = ls_qty.split("[.]");
        if (listQty.length == 0){
            listQty[0] = "0";
        }

//        int ll_qty = Integer.parseInt(listQty[0]);
//        int ll_uamt = Integer.parseInt(listUamt[0]);
//        popDto.setUamt(String.valueOf(ll_uamt));

        ls_itemcd = popDto.getItemcd();
        String[] listBarcode = ls_itemcd.split("[|]");
        HashMap hm = new HashMap();
        hm.put("color", popDto.getColor()) ;
        hm.put("grade", popDto.getGrade()) ;
        hm.put("thick", popDto.getThick()) ;
        hm.put("width", popDto.getWidth()) ;
        hm.put("itemcdArr", listBarcode) ;
        hm.put("dbnm", popDto.getDbnm()) ;
        //---------------------- DA006 UPDATE Process---------------------------------//
        //----------------------------------------------------------------------------//
        int queryResult = 0;
        for ( var item : listBarcode) {
            popDto.setFdeldate(popDto.getFdeldate());
            popDto.setLotno(item);
            queryResult = authService.UpdateDA006PANNEL(popDto);
            if(queryResult < 1){
                queryResult = 0;
//                return "UpdateDA006PANNEL ERROR";
            }

            queryResult = authService.UpdateDA006WINGBADY(popDto);
            if(queryResult < 1){
                queryResult = 0;
//                return "UpdateDA006WINGBADY ERROR";
            }
        }



        return "SUCCESS";
//        da037Dto = authService.GetLotNoData(hm);
//        if(da037Dto == null){
//            return "LotNo 를 찾을 수 없습니다. ";
//        }
        //---------------------- DA036  DA037 DA037H  등록 Process---------------------//
        //----------------------------------------------------------------------------//
//        String ls_delnum = authService.getDa036MaxSeq(popDto);
//        if (ls_delnum.equals("") || ls_delnum == null){
//            ls_delnum = "0001";
//        }else{
//            ls_delnum = Integer.toString(Integer.parseInt(ls_delnum) + 1) ;
//            if(ls_delnum.length() == 1){
//                ls_delnum = "000" + ls_delnum;
//            }else if(ls_delnum.length() == 2){
//                ls_delnum = "00" + ls_delnum;
//            }else if(ls_delnum.length() == 3){
//                ls_delnum = "0" + ls_delnum;
//            }
//        }

//        int queryResult = 0;
//        authService.DeleteDA037H(popDto);
//        authService.DeleteDA037(popDto);
//        authService.DeleteDA036(popDto);
//
//
//
//        da036Dto.setCustcd("KOSEP");
//        da036Dto.setSpjangcd("ZZ");
//        da036Dto.setDeldate(popDto.getFdeldate());
//        da036Dto.setDelnum(ls_delnum);
//        da036Dto.setDeltype("1");
//        da036Dto.setCltcd(popDto.getFdelcltcd());
//        da036Dto.setTaxcls("01");
//        da036Dto.setSetcls("11");
//        da036Dto.setStore("P01");
//        da036Dto.setDomcls("0");
//        da036Dto.setMoncls("KRW");
//        da036Dto.setMonrate(1);
//        da036Dto.setSunamt(0);
//        da036Dto.setSunflag("0");
//        da036Dto.setTaxflag("0");
//        da036Dto.setSetflag("0");
//        da036Dto.setReqdate(getToDate());
//        da036Dto.setIndate(getToDate());
//        da036Dto.setTaxchk("0");
//        da036Dto.setMcltdv("0");
//        da036Dto.setBanflag("0");
//        da036Dto.setPpgun("P");
//        da036Dto.setPagrb("X");
//        da036Dto.setIndate(getToDate());
//        da036Dto.setGrpflag("0");
//        da036Dto.setInperid(popDto.getPerid());
//        da036Dto.setPerid(popDto.getPerid());
//        int result = authService.InsertDA036(da036Dto);
//        if(result == 0){
//            return "InsertDA036 ERROR";
//        }else{
//            String ls_delseq = "001";
//                for ( var item : listBarcode) {
//
//                    popDto.setDelnum(ls_delnum);
//                    popDto.setDeldate(popDto.getFdeldate());
//                    popDto.setDelseq(ls_delseq);
//                    popDto.setLotno(item);
//                    da037LotDto = authService.GetLotNoList(popDto);
//
//                    Double ll_coilqty = Double.parseDouble(da037LotDto.getCoilqty());
//                    Double ll_uamt = Double.parseDouble(popDto.getUamt());
//                    Double ll_samt = ll_coilqty * ll_uamt;
//                    Double ll_tamt = ll_samt * 0.1;
//                    Double ll_mamt = ll_samt + ll_tamt;
//                    da037LotDto.setSamt(String.valueOf(ll_samt));
//                    da037LotDto.setTamt(String.valueOf(ll_tamt));
//                    da037LotDto.setMamt(String.valueOf(ll_mamt));
//
//                    //출고상세 DATASET
//                    da037Dto.setDeldate(popDto.getFdeldate());
//                    da037Dto.setDelnum(ls_delnum);
//                    da037Dto.setDelseq(ls_delseq);
//                    da037Dto.setIndate(getToDate());
//                    da037Dto.setQty(da037LotDto.getCoilqty());
//                    da037Dto.setInperid(popDto.getPerid());
//                    da037Dto.setPcode(popDto.getFdelpcode());
//                    da037Dto.setUamt(popDto.getUamt());
//                    da037Dto.setSamt(da037LotDto.getSamt());
//                    da037Dto.setTamt(da037LotDto.getTamt());
//                    da037Dto.setMamt(da037LotDto.getMamt());
//                    queryResult = authService.InsertDa037(da037Dto);
//                    if(queryResult < 1) {
//                        queryResult = 0;
//                        return "InsertDa037 ERROR";
//                    }else{
//                        da037HDto.setCustcd(da037Dto.getCustcd());
//                        da037HDto.setSpjangcd(da037Dto.getSpjangcd());
//                        da037HDto.setDeldate(da037Dto.getDeldate());
//                        da037HDto.setDelnum(da037Dto.getDelnum());
//                        da037HDto.setSeq(ls_delseq);
//                        da037HDto.setStore("P01");
//                        da037HDto.setPcode(da037Dto.getPcode());
//                        da037HDto.setLotno(item);
//                        da037HDto.setQty(da037LotDto.getCoilqty());
//                        da037HDto.setUamt(popDto.getUamt());
//                        ls_delseq = Integer.toString(Integer.parseInt(ls_delseq) + 1) ;
//                        if(ls_delseq.length() == 1){
//                            ls_delseq = "00" + ls_delseq;
//                        }else if(ls_delseq.length() == 2){
//                            ls_delseq = "0" + ls_delseq;
//                        }
//
//                        queryResult = authService.InsertDa037H(da037HDto);
//                        if(queryResult < 1){
//                            queryResult = 0;
//                            return "InsertDa037H ERROR";
//                        }
//                        popDto.setLotno(da037HDto.getLotno());
//                        queryResult = authService.UpdateDA006PANNEL(popDto);
//                        if(queryResult < 1){
//                            queryResult = 0;
////                        return "UpdateDA006PANNEL ERROR";
//                        }
//
//                        queryResult = authService.UpdateDA006WINGBADY(popDto);
//                        if(queryResult < 1){
//                            queryResult = 0;
////                        return "UpdateDA006WINGBADY ERROR";
//                        }
////                        da037HDto.setSeq(ls_delseq);
//                    }
//                }
//                queryResult = authService.UpdateTBda035(popDto);
//                if(queryResult < 1){
//                    queryResult = 0;
//                    return "UpdateTBda035 ERROR";
//                }
//                return "SUCCESS";
//
//        }
    //----------------------------------------------------------------------------//
    //----------------------------------------------------------------------------//

    }



    //  //출고현황 LOT
    @RequestMapping(value="/list03",method = RequestMethod.POST,
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
                case "todate":
                    popDto.setTodate(values.toString());
                default:
                    break;
            }
        });
        list01Dto = authService.GetTBDA035ChulList(popDto);
        return list01Dto;
    }

    //  //출고현황
/*    @RequestMapping(value="/list04",method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object Appcom04_index(@RequestParam Map<String, String> param
            , Model model
            , HttpServletRequest request) throws Exception{
        param.forEach((key, values) -> {
            switch (key){
                case "dbnm":
                    popDto.setDbnm(values.toString());
                    break;
                case "todate":
                    popDto.setTodate(values.toString());
                default:
                    break;
            }
        });
        list01Dto = authService.GetTBDA037ChulList(popDto);
        return list01Dto;
    }*/



    //  //출고현황
    @RequestMapping(value="/list03del",method = RequestMethod.POST,
            headers = ("content-type=multipart/*"),
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object Appcom03DEL_index(@RequestParam Map<String, String> param
            , Model model
            , HttpServletRequest request) throws Exception{
            param.forEach((key, values) -> {
                switch (key){
                    case "dbnm":
                        popDto.setDbnm(values.toString());
                        break;
                    case "barcode":
                        popDto.setLotno(values.toString());
                        break;
                    case "deldate":
                        popDto.setDeldate(values.toString());
                        break;
                    case "delnum":
                        popDto.setDelnum(values.toString());
                        break;
                    case "delseq":
                        popDto.setDelseq(values.toString());
                        break;
                    case "perid":
                        popDto.setPerid(values.toString());
                        break;
                    default:
                        break;
                }
            });
        int queryResult = 0;
        queryResult = authService.DeleteDA006PAN(popDto);
        if(queryResult < 1){
//            queryResult = 0;
//            return "DeleteDA006PAN ERROR";
        }

        queryResult = authService.DeleteDA006WIN(popDto);
        if(queryResult < 1){
//            queryResult = 0;
//            return "DeleteDA006WIN ERROR";
        }
            return "SUCCESS";

//        int queryResult = authService.DeleteDA037H(popDto);
//        if(queryResult < 1){
//            queryResult = 0;
//            return "DeleteDA037H ERROR";
//        }
//
//         queryResult = authService.DeleteDA037(popDto);
//        if(queryResult < 1){
//            queryResult = 0;
//            return "DeleteDA037 ERROR";
//        }
//
//
//        queryResult = authService.DeleteDA036(popDto);
//        if(queryResult < 1){
////            queryResult = 0;
////            return "DeleteDA036 ERROR";
//        }
//
//
//      /*  queryResult = authService.DeleteDA035(popDto);
//        if(queryResult < 1){
////            queryResult = 0;
////            return "DeleteDA035 ERROR";
//        }*/
//
//        queryResult = authService.DeleteDA006PAN(popDto);
//        if(queryResult < 1){
////            queryResult = 0;
////            return "DeleteDA006PAN ERROR";
//        }
//
//        queryResult = authService.DeleteDA006WIN(popDto);
//        if(queryResult < 1){
////            queryResult = 0;
////            return "DeleteDA006WIN ERROR";
//        }
//
//        return "SUCCESS";
    }



    private String getToDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date      = new Date(System.currentTimeMillis());

        return formatter.format(date);
    }



}
