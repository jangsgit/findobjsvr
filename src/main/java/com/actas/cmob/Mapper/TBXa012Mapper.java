package com.actas.cmob.Mapper;

import com.actas.cmob.DTO.TBXa012VO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TBXa012Mapper {
     public List<TBXa012VO> GetTBXa012List();           //list
     public TBXa012VO GetTBXa012Detail(TBXa012VO xa012Board);   //조회
     public void TBXa012Insert(TBXa012VO xa012Board);    //입력
     public void TBXa012Update(TBXa012VO xa012Board);    //수정
     public void TBXa012Delete(String spjangcd);         //삭제
     public TBXa012VO GetTBXa012Blank();                //blank data

}
