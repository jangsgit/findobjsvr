package com.actas.cmob.Service.daegun;

import com.actas.cmob.DTO.Daegun.DaegunItemList;
import com.actas.cmob.DTO.Daegun.DaegunXusers;
import com.actas.cmob.DTO.Kosep.*;
import com.actas.cmob.DTO.Themoon.PopDto;
import com.actas.cmob.DTO.Themoon.ThemoonListDto;
import com.actas.cmob.Mapper.Daegun.DaegunDBMapper;
import com.actas.cmob.Mapper.Kosep.KosepDBMapper;
import com.actas.cmob.Mapper.themoon.TheMoonDBMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service("DaegunAppService")
public class DaegunAppService {
    @Autowired
    DaegunDBMapper daegunMapper;


    public int InsertXusers(DaegunXusers parm) {
        int queryResult = 1;


        queryResult = daegunMapper.InsertXusers(parm);
        if (queryResult < 1) {
            queryResult = 0;
        }
        return queryResult;
    }


    public int InsertItem(DaegunItemList parm) {
        int queryResult = 1;


        queryResult = daegunMapper.InsertItem(parm);
        if (queryResult < 1) {
            queryResult = 0;
        }
        return queryResult;
    }


    public int UpdateItem(DaegunItemList parm) {
        int queryResult = 1;


        queryResult = daegunMapper.UpdateItem(parm);
        if (queryResult < 1) {
            queryResult = 0;
        }
        return queryResult;
    }
    public int CompleteItem(DaegunItemList parm) {
        int queryResult = 1;


        queryResult = daegunMapper.CompleteItem(parm);
        if (queryResult < 1) {
            queryResult = 0;
        }
        return queryResult;
    }


    public int DeleteItem(DaegunItemList parm) {
        int queryResult = 1;


        queryResult = daegunMapper.DeleteItem(parm);
        if (queryResult < 1) {
            queryResult = 0;
        }
        return queryResult;
    }


    public List<DaegunItemList> GetItemList(DaegunItemList parm){
                return daegunMapper.GetItemList(parm);
    }
    public List<DaegunItemList> GetItemListEnd(DaegunItemList parm){
        return daegunMapper.GetItemListEnd(parm);
    }


}
