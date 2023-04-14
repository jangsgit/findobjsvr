package com.actas.cmob.Repository;

import com.actas.cmob.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//CRUD 함수를 JPARepository 가 들고있음
//@Repository라는 어노테이션이 없어도 Ioc됨. 이유는 jparepository를 상속했기때문
public interface UserRepository extends JpaRepository<User, String> {
    //select * from tb_Xusers where username=?
    public User findByUsername(String username);
    public User findByPernm(String pernm);

}
