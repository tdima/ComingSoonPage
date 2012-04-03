package com.tumash.usefy.objwork;

import com.googlecode.objectify.annotation.Entity;

import javax.persistence.Id;

@Entity
public class EMailAddress {

    @Id
    private String email;
    private EMailAddress()  {

    }
    public EMailAddress(String email)  {
        this.email = email;

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
