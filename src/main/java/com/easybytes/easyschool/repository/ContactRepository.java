package com.easybytes.easyschool.repository;

import com.easybytes.easyschool.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ContactRepository extends PagingAndSortingRepository<Contact, Integer>, CrudRepository<Contact, Integer> {

    //Derived Query method in Spring Data JPA.
    //Abstract method that Spring JPA will have implementation at runtime.
    List<Contact> findByStatus(String status);

    //JPL Query example
    //@Query("SELECT c FROM Contact c WHERE c.status = :status")
    //Native sql query example
    @Query(value = "SELECT * FROM contact_msg WHERE contact_msg.status = :status", nativeQuery = true)
    Page<Contact> findByStatus(String status, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Contact c SET c.status = ?1 WHERE c.contactId = ?2")
    int updateStatusById(String status, int id);

}
