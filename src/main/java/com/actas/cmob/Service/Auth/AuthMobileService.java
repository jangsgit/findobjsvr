package com.actas.cmob.Service.Auth;

import com.actas.cmob.DTO.UserFormDto;
import com.actas.cmob.Mapper.Daegun.DaegunDBMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("AuthMobileService")
public class AuthMobileService {
    @Autowired
    DaegunDBMapper daegunMapper;

    public UserFormDto GetUserInfo(UserFormDto parm){
        return daegunMapper.GetUserInfo(parm);

    }


}
