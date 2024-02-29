package com.example.server.domain.xml;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface XmlInfoRepository extends JpaRepository<XmlInfo, Long> {

    @Query(value = "select * from xml_info where id = ?1", nativeQuery = true)
    XmlInfo findById(long id);

    @Query(value = "select * from xml_info where id_nfe = :idNfe", nativeQuery = true)
    XmlInfo findByIdNfe(String idNfe);

}
