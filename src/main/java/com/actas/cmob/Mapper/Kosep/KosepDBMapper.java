package com.actas.cmob.Mapper.Kosep;

import com.actas.cmob.DTO.Kosep.*;
import com.actas.cmob.DTO.UserFormDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
@Mapper
public interface KosepDBMapper {

    public UserFormDto GetUserInfo(UserFormDto userinfo);
    public List<KosepList01Dto> GetTBDA035List(KosepPopDto parm) ;
    public List<KosepList01Dto> GetTBDA035ChulList(KosepPopDto parm) ;
    public List<KosepList01Dto> GetTBDA037ChulList(KosepPopDto parm) ;

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
    public int DeleteDA035(KosepPopDto parm) ;
    public int DeleteDA006PAN(KosepPopDto parm) ;
    public int DeleteDA006WIN(KosepPopDto parm) ;









}
