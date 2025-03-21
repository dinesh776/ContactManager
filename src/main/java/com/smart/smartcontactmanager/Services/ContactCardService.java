package com.smart.smartcontactmanager.Services;

import com.smart.smartcontactmanager.Entities.ContactCard;
import com.smart.smartcontactmanager.dao.ContactCardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactCardService {
    @Autowired
    private ContactCardRepo contactCardRepo;

    public ContactCard saveContact(ContactCard contactCard){
        return contactCardRepo.save(contactCard);
    }

    public List<ContactCard> getAllContactCards(int userId){
        return contactCardRepo.findContactCardsByUser_U_id(userId);
    }

    public ContactCard getContactCard(int id){
        return contactCardRepo.findContactCardsByCId(id);
    }

    public ContactCard updateContact(ContactCard contactCard){
       return contactCardRepo.save(contactCard);
    }

    public void deleteContact(ContactCard contactCard){
        contactCardRepo.deleteByCId(contactCard.getcId());
    }
}
