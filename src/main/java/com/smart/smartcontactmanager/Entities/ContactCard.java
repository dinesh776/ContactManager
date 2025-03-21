package com.smart.smartcontactmanager.Entities;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "contact_card")
public class ContactCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cId;

    private String nickName;
    @Column(length = 5000)
    private String about;

    @Transient
    private transient MultipartFile imageFile;

    private String imageUrl;
    @Column(unique = true)
    private String email;
    private String designation;
    private String workExp;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    private String contact_number;

    @ManyToOne(fetch = FetchType.LAZY)
    private USER user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "name_id",referencedColumnName = "nId")
    private NAME name;

    private boolean fav=false;

//    getter &setters,constructors,toString methods

//    toString

    @Override
    public String toString() {
        return "ContactCard{" +
                ", user=" + this.user.getU_id() +
                "cId=" + cId +
                ", name=" + name +
                ", nickName='" + nickName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", email='" + email + '\'' +
                ", contact_number='" + contact_number + '\'' +
                ", dob=" + dob +
                ", designation='" + designation + '\'' +
                ", workExp='" + workExp + '\'' +
                ", about='" + about + '\'' +
                ", fav='" + fav + '\'' +
                '}';
    }


//    setters

    public void setFav(boolean fav) {
        this.fav = fav;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setWorkExp(String workExp) {
        this.workExp = workExp;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public void setUser(USER user) {
        this.user = user;
    }

    public void setName(NAME name) {
        this.name = name;
    }


//    getters

    public boolean isFav() {
        return this.fav;
    }

    public int getcId() {
        return this.cId;
    }

    public String getNickName() {
        return this.nickName;
    }

    public String getAbout() {
        return this.about;
    }

    public MultipartFile getImageFile() {
        return this.imageFile;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getEmail() {
        return this.email;
    }

    public String getDesignation() {
        return this.designation;
    }

    public String getWorkExp() {
        return this.workExp;
    }

    public Date getDob() {
        return this.dob;
    }

    public String getContact_number() {
        return this.contact_number;
    }

    public USER getUser() {
        return this.user;
    }

    public NAME getName() {
        return this.name;
    }


//    constructors

    public ContactCard() {
    }

    public ContactCard(int cId,boolean fav ,String nickName, String about, MultipartFile imageFile, String imageUrl, String email, String designation, String workExp, Date dob, String contact_number, USER user, NAME name) {
        this.cId = cId;
        this.fav=fav;
        this.nickName = nickName;
        this.about = about;
        this.imageFile = imageFile;
        this.imageUrl = imageUrl;
        this.email = email;
        this.designation = designation;
        this.workExp = workExp;
        this.dob = dob;
        this.contact_number = contact_number;
        this.user = user;
        this.name = name;
    }
}
