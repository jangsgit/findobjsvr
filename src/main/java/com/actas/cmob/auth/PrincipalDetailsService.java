package com.actas.cmob.auth;

import com.actas.cmob.Entity.User;
import com.actas.cmob.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public User saveMember(User user){
        validateDuplicateMember(user);
        return userRepository.save(user);

    }

    private void validateDuplicateMember(User user){
        User findMember = userRepository.findByUsername(user.getUsername());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 아이디입니다.");
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User UserEntity = userRepository.findByUsername(username);

        if(UserEntity != null){
            return new PrincipalDetails(UserEntity);
        }
        return null;
    }

}
