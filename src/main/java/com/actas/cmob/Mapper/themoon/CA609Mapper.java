package com.actas.cmob.Mapper.themoon;

import com.actas.cmob.DTO.Themoon.CA609Dto;
import com.actas.cmob.DTO.Themoon.CA609PopDto;
import com.actas.cmob.DTO.UserFormDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CA609Mapper {

    public UserFormDto GetUserInfo(UserFormDto userinfo);
    public List<CA609Dto> GetBarcodeInfo(CA609PopDto parm);
    public List<CA609Dto> GetQtyInfo(CA609Dto parm);
    public List<CA609Dto> GetBalInfoHead(CA609Dto parm);

    public int UpdateBalInfoHead(CA609Dto parm);
    public int UpdateBal(CA609Dto parm);
    public int DeleteBalHead(CA609Dto parm);
    public int DeleteBalBody(CA609Dto parm);
    public int InsertLog(CA609Dto parm);

    public List<CA609Dto> GetNowList(CA609Dto parm);

    public CA609Dto GetCntData(CA609Dto parm);

    public CA609Dto GetQcqty(CA609Dto parm);

    public int UpdateQcflag(CA609Dto parm);


    public List<CA609Dto> select_tb_ca608_head(CA609PopDto parm);



    public String select_tb_CA623(CA609PopDto parm);

    public String select_DF_DANGA(CA609PopDto parm);

    public String Maxseq(String parm);
    public int Update_TB_CA608(CA609PopDto parm);

    public int Insert_TB_CA623(CA609PopDto parm);

    public int Insert_TB_CA624(CA609PopDto parm);

    public int Update_TB_CA609(CA609PopDto parm);

    public String select_tb_CA624(CA609PopDto parm);

    public String GetQcnum(CA609PopDto parm);

    public int UpdateXlogin(CA609Dto parm);

    public int InsertLogT(CA609Dto parm);
}
