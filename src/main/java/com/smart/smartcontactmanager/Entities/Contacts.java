package com.smart.smartcontactmanager.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "contacts")
public class Contacts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pcId;
    @ManyToOne(fetch = FetchType.LAZY)
    private ContactCard cId;

    private String contact;

    public ContactCard getcId() {
        return this.cId;
    }

    public void setcId(ContactCard cId) {
        this.cId = cId;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Contacts() {
    }
}
