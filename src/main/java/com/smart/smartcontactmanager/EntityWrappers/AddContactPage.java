package com.smart.smartcontactmanager.EntityWrappers;

import com.smart.smartcontactmanager.Entities.ContactCard;
import com.smart.smartcontactmanager.Entities.NAME;


public class AddContactPage {
    private NAME name;
    private ContactCard contactCard;

    public void setContactName(){
        this.contactCard.setName(name);
    }

//    to String
    @Override
    public String toString() {
        return "AddContactPage{" +
                "contactCard=" + contactCard +
                '}';
    }
//getters
    public NAME getName() {
        return this.name;
    }
    public ContactCard getContactCard() {
        return this.contactCard;
    }
//    setters
    public void setName(NAME name) {
        this.name = name;
    }

    public void setContactCard(ContactCard contactCard) {
        this.contactCard = contactCard;
    }

//    constructors
    public AddContactPage() {
    }

    public AddContactPage(NAME name, ContactCard contactCard) {
        this.name = name;
        this.contactCard = contactCard;
    }
}
