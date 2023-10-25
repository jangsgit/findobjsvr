package com.actas.cmob.Service.themoon;

import com.actas.cmob.DTO.Themoon.CA609Dto;
import com.actas.cmob.DTO.Themoon.CA609PopDto;
import com.actas.cmob.Mapper.themoon.CA609Mapper;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
//@MapperScan(value = {"com.actas.cmob.Mapper.CA609"})
@Service("CA609Service")
public class CA609Service {
    @Autowired
    CA609Mapper CA609Mapper;


    public List<CA609Dto> GetBarcodeInfo(CA609PopDto parm){
        String dbnm = parm.getDbnm();
        switch (dbnm){
            case "ERP_THEMOON":
                return CA609Mapper.GetBarcodeInfo(parm);
            default:
                return null;

        }

    }

    public List<CA609Dto> GetQtyInfo(CA609Dto parm){
        String dbnm = parm.getDbnm();
        switch (dbnm){
            case "ERP_THEMOON":
                return CA609Mapper.GetQtyInfo(parm);
            default:
                return null;

        }

    }

    public List<CA609Dto> GetBalInfoHead(CA609Dto parm){
        String dbnm = parm.getDbnm();
        switch (dbnm){
            case "ERP_THEMOON":
                return CA609Mapper.GetBalInfoHead(parm);
            default:
                return null;

        }

    }
    public int UpdateBalInfoHead(CA609Dto parm){
        String dbnm = parm.getDbnm();
        switch (dbnm){
            case "ERP_THEMOON":
                return CA609Mapper.UpdateBalInfoHead(parm);
            default:
                return 0;

        }

    }

    public int UpdateBal(CA609Dto parm){
        String dbnm = parm.getDbnm();
        switch (dbnm){
            case "ERP_THEMOON":
                return CA609Mapper.UpdateBal(parm);
            default:
                return 0;

        }

    }

    public int DeleteBalHead(CA609Dto parm){
        String dbnm = parm.getDbnm();
        switch (dbnm){
            case "ERP_THEMOON":
                return CA609Mapper.DeleteBalHead(parm);
            default:
                return 0;

        }

    }

    public int DeleteBalBody(CA609Dto parm){
        String dbnm = parm.getDbnm();
        switch (dbnm){
            case "ERP_THEMOON":
                return CA609Mapper.DeleteBalBody(parm);
            default:
                return 0;

        }

    }

    public int InsertLog(CA609Dto parm){
        String dbnm = parm.getDbnm();
        switch (dbnm){
            case "ERP_THEMOON":
                return CA609Mapper.InsertLog(parm);
            default:
                return 0;

        }

    }


    public List<CA609Dto> GetNowList(CA609Dto parm) {
        String dbnm = parm.getDbnm();
        switch (dbnm) {
            case "ERP_THEMOON":
                return CA609Mapper.GetNowList(parm);
            default:
                return null;
        }
    }

    public CA609Dto GetCntData(CA609Dto parm) {
        String dbnm = parm.getDbnm();
        switch (dbnm) {
            case "ERP_THEMOON":
                return CA609Mapper.GetCntData(parm);
            default:
                return null;
        }
    }

    public CA609Dto GetQcqty(CA609Dto parm) {
        String dbnm = parm.getDbnm();
        switch (dbnm) {
            case "ERP_THEMOON":
                return CA609Mapper.GetQcqty(parm);
            default:
                return null;
        }
    }

    public int UpdateXlogin(CA609Dto parm){
        String dbnm = parm.getDbnm();
        switch (dbnm){
            case "ERP_THEMOON":
                return CA609Mapper.UpdateXlogin(parm);
            default:
                return 0;

        }

    }

    public int InsertLogT(CA609Dto parm){
        String dbnm = parm.getDbnm();
        switch (dbnm){
            case "ERP_THEMOON":
                return CA609Mapper.InsertLogT(parm);
            default:
                return 0;

        }

    }

    public int UpdateQcflag(CA609Dto parm){
        String dbnm = parm.getDbnm();
        switch (dbnm){
            case "ERP_THEMOON":
                return CA609Mapper.UpdateQcflag(parm);
            default:
                return 0;

        }

    }


    public boolean Update_TB_CA608(CA609PopDto parm){

        int queryResult = 1;

        queryResult = CA609Mapper.Update_TB_CA608(parm);
        if(queryResult < 1){
            queryResult = 0;
        }

        return (queryResult > 0);
    }

    public String select_tb_CA623(CA609PopDto parm){

        return CA609Mapper.select_tb_CA623(parm);

    }



    public String select_DF_DANGA(CA609PopDto parm){

        return CA609Mapper.select_DF_DANGA(parm);

    }

    public String Maxseq(String parm){

        return CA609Mapper.Maxseq(parm);
    }


    public boolean Insert_TB_CA624(CA609PopDto parm){

        int queryResult = 1;

        queryResult = CA609Mapper.Insert_TB_CA624(parm);
        if(queryResult < 1){
            queryResult = 0;

        }

        return (queryResult > 0);

    }

    public boolean Update_TB_CA609(CA609PopDto parm){

        int queryResult = 1;

        queryResult = CA609Mapper.Update_TB_CA609(parm);
        if(queryResult < 1){
            queryResult = 0;

        }

        return (queryResult > 0);

    }





    public List<CA609Dto> select_tb_ca608_head(CA609PopDto parm){

        return CA609Mapper.select_tb_ca608_head(parm);
    }

    public boolean Insert_TB_CA623(CA609PopDto parm){

        int queryResult = 1;

        queryResult = CA609Mapper.Insert_TB_CA623(parm);
        if(queryResult < 1){
            queryResult = 0;

        }

        return (queryResult > 0);

    }

    public String select_tb_CA624(CA609PopDto parm) {

        return CA609Mapper.select_tb_CA624(parm);
    }

    public String GetQcnum(CA609PopDto parm) {
        return CA609Mapper.GetQcnum(parm);
    }
}
