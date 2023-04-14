package com.actas.cmob.Repository;

import com.actas.cmob.Entity.Tbxa012;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// <엔티티 타입클래스, 기본키타입>
//repository 에는 기본적인crud 및 페이징처리 메소드가 정의되어있음.
public interface Tbxa012Repository extends JpaRepository<Tbxa012, String> {

    List<Tbxa012> findBySpjangcd(String spjangcd);
    List<Tbxa012> findBySpjangcdOrSpjangnm(String spjangcd, String spjangnm);
//    @Query("select A from TB_XA012 A where A.pernm like %:prenm% order by A.spjangcd ")
//    List<Tbxa012> FindBySpjangnm(@Param("prenm") String prenm);


}
