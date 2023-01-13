package com.easybytes.easyschool.service;

import com.easybytes.easyschool.constants.EazySchoolConstants;
import com.easybytes.easyschool.model.Contact;
import com.easybytes.easyschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public ContactService() {
        System.out.println("Contact Service Bean initialized");
    }

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = false;

        contact.setStatus(EazySchoolConstants.OPEN);

        Contact savedContact = contactRepository.save(contact);
        if(savedContact != null && savedContact.getContactId() > 0) {
            isSaved = true;
        }
        return isSaved;
    }

    public List<Contact> findMsgsWithOpenStatus() {
        List<Contact> contactMsgs = contactRepository.findByStatus(EazySchoolConstants.OPEN);
        return contactMsgs;
    }

    public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending());
        Page<Contact> msgPage = contactRepository.findByStatus(
                EazySchoolConstants.OPEN, pageable);
        return msgPage;
    }

    public boolean updateMsgStatus(int contactId) {
        boolean isUpdated = false;

        //Standard update without the custom update query
        /* Optional<Contact> contact = contactRepository.findById(contactId); //may return null
        contact.ifPresent(contact1 -> {
            contact1.setStatus(EazySchoolConstants.CLOSE);
        });

        Contact updatedContact = contactRepository.save(contact.get());
        if(null != updatedContact && updatedContact.getUpdatedBy() != null) {
            isUpdated = true;
        } */

        //Using custom update query example
        int rows = contactRepository.updateStatusById(EazySchoolConstants.CLOSE, contactId);
        if(rows > 0) {
            isUpdated = true;
        }
        return isUpdated;
    }
}
