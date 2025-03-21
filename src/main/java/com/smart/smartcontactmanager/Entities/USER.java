package com.smart.smartcontactmanager.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class USER{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int u_id;

    @Column(name = "user_name", unique = true)
    @NotBlank(message = "User Name is required")
    @Size(min = 3,max = 16)
    private String userName;

    @Transient
    private transient MultipartFile imageFile;

    private String imageUrl;
    @Column(length = 500)
    private String about;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    private String personalContact;
    @Email(regexp = "^[a-zA-Z0-9._+-]+@[a-zA-Z0-9.-]+$")
    @Column(unique = true)
    private String email;

    @NotNull(message = "Password cannot be Empty")
    @Size(min = 6,message = "Password must be between 6 and 10 characters")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z]).*$", message = "Password must contain at least one digit and one uppercase letter")
    private String password;
    private String role;
    private boolean active;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
    private List<ContactCard> contacts=new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "name_id",referencedColumnName = "nId")
    private NAME name;

//    getter &setters,constructors,toString methods

//    toString

    @Override
    public String toString() {
        return "USER{" +
                "u_id=" + u_id +
                ", userName='" + userName + '\'' +
                ", name=" + name +
                ", imageUrl='" + imageUrl + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", personalContact='" + personalContact + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                ", about='" + about + '\'' +
                ", active=" + active +
                ", contacts=" + contacts +
                '}';
    }


//    setters

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setPersonalContact(String personalContact) {
        this.personalContact = personalContact;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setContacts(List<ContactCard> contacts) {
        this.contacts = contacts;
    }

    public void setName(NAME name) {
        this.name = name;
    }

    //    getters
    public int getU_id() {
        return this.u_id;
    }

    public String getUserName() {
        return this.userName;
    }

    public MultipartFile getImageFile() {
        return this.imageFile;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getAbout() {
        return this.about;
    }

    public Date getDob() {
        return this.dob;
    }

    public String getPersonalContact() {
        return this.personalContact;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getRole() {
        return this.role;
    }

    public boolean isActive() {
        return this.active;
    }

    public List<ContactCard> getContacts() {
        return this.contacts;
    }

    public NAME getName() {
        return this.name;
    }

//    constructors

    public USER() {
    }

    public USER(String userName, MultipartFile imageFile, String imageUrl, String about, Date dob, String personalContact, String email, String password, String role, boolean active, List<ContactCard> contacts, NAME name) {
        this.userName = userName;
        this.imageFile = imageFile;
        this.imageUrl = imageUrl;
        this.about = about;
        this.dob = dob;
        this.personalContact = personalContact;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = active;
        this.contacts = contacts;
        this.name = name;
    }
}
