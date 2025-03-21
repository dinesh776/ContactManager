package com.smart.smartcontactmanager.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "names") // Specify table name
public class NAME {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nId;

    private String first_name;
    private String middle_name;
    private String last_name;

    //    getter &setters,constructors,toString methods

//    toString

    @Override
    public String toString() {
        return "NAME{" +
                "nId=" + nId +
                ", first_name='" + first_name + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }


//    getters

    public int getnId() {
        return this.nId;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public String getMiddle_name() {
        return this.middle_name;
    }

    public String getLast_name() {
        return this.last_name;
    }


//    setters

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    //    constructors
    public NAME() {
    }

    public NAME(int nId, String first_name, String middle_name, String last_name) {
        this.nId = nId;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
    }
}
