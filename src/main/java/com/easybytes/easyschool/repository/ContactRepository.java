package com.easybytes.easyschool.repository;

import com.easybytes.easyschool.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact,Integer> {

    //Derived Query method in Spring Data JPA.
    //Abstract method that Spring JPA will have implementation at runtime.
    List<Contact> findByStatus(String status);

}
