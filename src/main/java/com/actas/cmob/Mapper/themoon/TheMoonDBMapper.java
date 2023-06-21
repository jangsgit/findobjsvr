package com.actas.cmob.Mapper.themoon;

import com.actas.cmob.DTO.Kosep.*;
import com.actas.cmob.DTO.Themoon.LoginLogDto;
import com.actas.cmob.DTO.Themoon.PopDto;
import com.actas.cmob.DTO.Themoon.ThemoonListDto;
import com.actas.cmob.DTO.Themoon.ThemoonListDto2;
import com.actas.cmob.DTO.UserFormDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
@Mapper
public interface TheMoonDBMapper {

    public UserFormDto GetUserInfo(UserFormDto userinfo);
    public List<KosepList01Dto> GetTBDA035List(KosepPopDto parm) ;
    public List<KosepList01Dto> GetTBDA035ChulList(KosepPopDto parm) ;
   // public List<KosepList01Dto> GetTBDA037ChulList(KosepPopDto parm) ;

    public KosepDa037Dto GetLotNoData(HashMap<String,String> parm) ;
    public KosepDa037Dto GetLotNoList(KosepPopDto parm) ;

    public String getDa036MaxSeq(KosepPopDto parm) ;
    public int InsertDA036(KosepDa036Dto parm) ;
    public int InsertDa037(KosepDa037Dto parm) ;
    public int InsertDa037H(KosepDa037HDto parm) ;
    public int UpdateTBda035(KosepPopDto parm) ;
    public int UpdateDA006PANNEL(KosepPopDto parm) ;
    public int UpdateDA006WINGBADY(KosepPopDto parm) ;


    public int DeleteDA036(KosepPopDto parm) ;
    public int DeleteDA037(KosepPopDto parm) ;
    public int DeleteDA037H(KosepPopDto parm) ;


   // public int DeleteDA035(KosepPopDto parm) ;

    public int DeleteDA006PAN(KosepPopDto parm) ;
    public int DeleteDA006WIN(KosepPopDto parm) ;


    public int LoginCount(LoginLogDto parm);

    public int InsertLog(LoginLogDto parm);

    public int UpdateLog(LoginLogDto parm);


    public List<ThemoonListDto> TB_CA501list(PopDto parm);


    public List<ThemoonListDto2> Store_Info(PopDto parm);

    public int Update_TB_FPLAN(PopDto parm);

    public int Insert_TB_FPLAN_WORK(PopDto parm);

    public int Insert_login_h(LoginLogDto parm);


    public int Insert_TB_FPLAN_W030(PopDto parm);


    public int Insert_tb_fplan_owork(PopDto parm);

    public int INSERT_TB_FPLAN_WPERID(PopDto parm);


    public int INSERT_TB_FPLAN_WTIME(PopDto parm);


    public List<ThemoonListDto2> Select_List(PopDto parm);

    public int Update_tb_fplan(PopDto parm);

    public int delete_tb_fplan_work(PopDto parm);

    public int delete_tb_fplan_owork(PopDto parm);

    public int delete_tb_fplan_wperid(PopDto parm);

    public int delete_tb_fplan_w030(PopDto parm);


    public int delete_tb_fplan_wtime(PopDto parm);

    public List<ThemoonListDto2> select_tb_ca630(PopDto parm);


    public List<ThemoonListDto2> select_jaeqty(PopDto parm);

    public int insert_tb_ca630(PopDto parm);



    public List<ThemoonListDto2> insert_check(PopDto parm);

    public int Update_tb_ca630Int(PopDto parm);

    public String select_time(PopDto parm);
























}
