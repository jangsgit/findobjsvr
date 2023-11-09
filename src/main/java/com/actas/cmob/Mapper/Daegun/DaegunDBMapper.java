package com.actas.cmob.Mapper.Daegun;

import com.actas.cmob.DTO.UserFormDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
@Mapper
public interface DaegunDBMapper {

    public UserFormDto GetUserInfo(UserFormDto userinfo);




}
