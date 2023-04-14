package com.actas.ems.Repository;

import com.actas.cmob.Entity.Tbxa012;
import com.actas.cmob.Repository.Tbxa012Repository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class Tbxa012RepositoryTest {

    @Autowired
    Tbxa012Repository tbxa012Repository;

    @Test
    @DisplayName("사업장정보저장테스트")
    public void createTbxa012Test(){
        Tbxa012 tbxa012 = new Tbxa012();
        tbxa012.setSpjangcd("FF");
        tbxa012.setSpjangnm("ACTAS COMPANY");
        Tbxa012 savedTbxa012 = tbxa012Repository.save(tbxa012);
        System.out.println(savedTbxa012.toString());

    }

}