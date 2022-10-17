package com.easybytes.easyschool.service;

import com.easybytes.easyschool.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ContactService {

    /**
     * Save Contact Details into DB
     * @param contact
     * @return boolean
     */

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = true;
        //TODO: Need to persist the data into DB table
        log.info(contact.toString());
        return isSaved;
    }
}
