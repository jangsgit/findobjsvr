package com.actas.cmob.auth;

import com.actas.cmob.Entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {

    private User user;

    public PrincipalDetails(User user){
        this.user = user;
    }
    //해당user의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;
    };

    public String getPassword(){
        return user.getPassword();
    };

    public String getUsername(){
        return user.getUsername();
    };

    public boolean isAccountNonExpired(){
        return true;
    };

    public boolean isAccountNonLocked(){
        return true;
    };

    public boolean isCredentialsNonExpired(){
        return true;
    };

    //1년동안 회원이 로그인을 안하면 휴면계정일경우
    public boolean isEnabled(){
        return true;
    };


}
