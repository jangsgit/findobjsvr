package com.actas.cmob.Mapper.Daegun;

import com.actas.cmob.DTO.Daegun.DaegunItemList;
import com.actas.cmob.DTO.Daegun.DaegunXusers;
import com.actas.cmob.DTO.Kosep.KosepCa635Dto;
import com.actas.cmob.DTO.Kosep.KosepList01Dto;
import com.actas.cmob.DTO.Kosep.KosepPopDto;
import com.actas.cmob.DTO.UserFormDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
@Mapper
public interface DaegunDBMapper {

    public UserFormDto GetUserInfo(UserFormDto userinfo);

    public int InsertXusers(DaegunXusers parm) ;
    public int InsertItem(DaegunItemList parm) ;
    public int UpdateItem(DaegunItemList parm) ;
    public int DeleteItem(DaegunItemList parm) ;

    public List<DaegunItemList> GetItemList(DaegunItemList parm) ;


}
