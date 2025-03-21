package com.smart.smartcontactmanager.EntityWrappers;

import com.smart.smartcontactmanager.Entities.NAME;
import com.smart.smartcontactmanager.Entities.USER;

public class UserNameWrapper {
    private USER user;
    private NAME name;

//    to string
    @Override
    public String toString() {
        return "UserNameWrapper{" +
                "user=" + user +
                ", name=" + name +
                '}';
    }
//getters
    public USER getUser() {
        return this.user;
    }
    public NAME getName() {
        return this.name;
    }
//    setters
    public void setUser(USER user) {
        this.user = user;
    }

    public void setName(NAME name) {
        this.name = name;
    }

//    constructors

    public UserNameWrapper() {
    }

    public UserNameWrapper(USER user, NAME name) {
        this.user = user;
        this.name = name;
    }
}
