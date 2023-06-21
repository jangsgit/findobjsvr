package com.actas.cmob.Service.themoon;


import com.actas.cmob.DTO.Kosep.KosepList01Dto;
import com.actas.cmob.DTO.Kosep.KosepPopDto;
import com.actas.cmob.DTO.Themoon.LoginLogDto;
import com.actas.cmob.DTO.Themoon.PopDto;
import com.actas.cmob.DTO.Themoon.ThemoonListDto;
import com.actas.cmob.DTO.Themoon.ThemoonListDto2;
import com.actas.cmob.Mapper.themoon.TheMoonDBMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("ThemoonAppService")
public class ThemoonAppService {

    @Autowired
    TheMoonDBMapper TheMoonDBMapper;



    public String TimeCheck(PopDto parm){
        return TheMoonDBMapper.select_time(parm);
    }

    public int LoginCount(LoginLogDto parm){
        int queryResult = 1;
        queryResult = TheMoonDBMapper.LoginCount(parm);
        return queryResult;
    }


    public boolean InsertLog(LoginLogDto parm){
        int queryResult = 1;

        queryResult = TheMoonDBMapper.InsertLog(parm);
        if(queryResult < 1){
            queryResult = 0;
        }
        return (queryResult > 0);
    }

    public boolean UpdateLog(LoginLogDto parm){

        int queryResult = 1;

        queryResult = TheMoonDBMapper.UpdateLog(parm);
        if(queryResult < 1){
            queryResult = 0;
        }
        return (queryResult > 0);
    }


    public List<ThemoonListDto> TB_CA501list(PopDto parm){
        String dbnm = parm.getDbnm();
        switch (dbnm){
            case "ERP_THEMOON":
                return TheMoonDBMapper.TB_CA501list(parm);
            default:
                return null;

        }

    }

    public List<ThemoonListDto2> Store_Info(PopDto parm){
        String dbnm = parm.getDbnm();
        switch (dbnm){
            case "ERP_THEMOON":
                return TheMoonDBMapper.Store_Info(parm);
            default:
                return null;
        }
    }


    public boolean Update_TB_FPLAN(PopDto parm){

        int queryResult = 1;

        queryResult = TheMoonDBMapper.Update_TB_FPLAN(parm);
        if(queryResult < 1){
            queryResult = 0;
        }
        return (queryResult > 0);
    }


    public boolean Insert_TB_FPLAN_WORK(PopDto parm){

        int queryResult = 1;

        queryResult = TheMoonDBMapper.Insert_TB_FPLAN_WORK(parm);
        if(queryResult < 1){
            queryResult = 0;

        }
        return (queryResult > 0);
    }


    public boolean Insert_login_h(LoginLogDto parm){
        int queryResult = 1;

        queryResult = TheMoonDBMapper.Insert_login_h(parm);
        if(queryResult < 1){
            queryResult = 0;
        }

        return (queryResult > 0);
    }


    public boolean Insert_TB_FPLAN_W030(PopDto parm){

        int queryResult = 1;

        queryResult = TheMoonDBMapper.Insert_TB_FPLAN_W030(parm);
        if(queryResult < 1){
            queryResult = 0;
        }

        return (queryResult > 0);

    }


    public boolean Insert_tb_fplan_owork(PopDto parm){

        int queryResult = 1;

        queryResult = TheMoonDBMapper.Insert_tb_fplan_owork(parm);
        if(queryResult < 1){
            queryResult = 0;
        }

        return (queryResult > 0);

    }


    public boolean INSERT_TB_FPLAN_WPERID(PopDto parm){

        int queryResult = 1;

        queryResult = TheMoonDBMapper.INSERT_TB_FPLAN_WPERID(parm);
        if(queryResult < 1){
            queryResult = 0;
        }

        return (queryResult > 0);

    }

    public boolean INSERT_TB_FPLAN_WTIME(PopDto parm){

        int queryResult = 1;

        queryResult = TheMoonDBMapper.INSERT_TB_FPLAN_WTIME(parm);
        if(queryResult < 1){
            queryResult = 0;
        }

        return (queryResult > 0);
    }



    public List<ThemoonListDto2> Select_List(PopDto parm){
        String dbnm = parm.getDbnm();
        switch (dbnm){
            case "ERP_THEMOON":
                return TheMoonDBMapper.Select_List(parm);
            default:
                return null;
        }
    }

    /**입고취소**/

    public boolean Update_tb_fplan(PopDto parm){

        int queryResult = 1;

        queryResult = TheMoonDBMapper.Update_tb_fplan(parm);
        if(queryResult < 1){
            queryResult = 0;
        }

        return (queryResult > 0);
    }


    public boolean delete_tb_fplan_work(PopDto parm){

        int queryResult = 1;

        queryResult = TheMoonDBMapper.delete_tb_fplan_work(parm);
        if(queryResult < 1){
            queryResult = 0;
        }

        return (queryResult > 0);

    }

    public boolean delete_tb_fplan_owork(PopDto parm){

        int queryResult = 1;

        queryResult = TheMoonDBMapper.delete_tb_fplan_owork(parm);
        if(queryResult < 1){
            queryResult = 0;

        }

        return (queryResult > 0);
    }

    public boolean delete_tb_fplan_wperid(PopDto parm){

        int queryResult = 1;

        queryResult  = TheMoonDBMapper.delete_tb_fplan_wperid(parm);
        if(queryResult < 1){
            queryResult = 0;
        }
        return (queryResult > 0);
    }

    public boolean delete_tb_fplan_w030(PopDto parm){

        int queryResult = 1;

        queryResult = TheMoonDBMapper.delete_tb_fplan_w030(parm);
        if(queryResult < 1){
            queryResult = 0;
        }
        return (queryResult > 0);

    }

    public boolean delete_tb_fplan_wtime(PopDto parm){

        int queryResult = 1;

        queryResult = TheMoonDBMapper.delete_tb_fplan_wtime(parm);
        if(queryResult < 1){
            queryResult = 0;
        }

        return (queryResult > 0);
    }


    public List<ThemoonListDto2>  select_tb_ca630(PopDto parm){

        String dbnm = parm.getDbnm();

        switch (dbnm){
            case "ERP_THEMOON":
                return TheMoonDBMapper.select_tb_ca630(parm);
            default:
                return null;
        }

    }

    public List<ThemoonListDto2> select_jaeqty(PopDto parm){

        String dbnm = parm.getDbnm();

        switch (dbnm){
            case "ERP_THEMOON":
                return TheMoonDBMapper.select_jaeqty(parm);
            default:
                return null;

        }
    }




    public boolean insert_tb_ca630(PopDto parm){

        int queryResult = 1;

        queryResult  = TheMoonDBMapper.insert_tb_ca630(parm);
        if(queryResult < 1){
            queryResult = 0;
        }
        return (queryResult > 0);
    }


    public boolean Update_tb_ca630Int(PopDto parm){

        int queryResult = 1;

        queryResult  = TheMoonDBMapper.Update_tb_ca630Int(parm);
        if(queryResult < 1){
            queryResult = 0;
        }
        return (queryResult > 0);
    }



    public List<ThemoonListDto2> insert_check(PopDto parm){


                return TheMoonDBMapper.insert_check(parm);


    }
}
