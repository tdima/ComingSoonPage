package com.tumash.usefy.objwork;

import com.google.appengine.api.datastore.QueryResultIterable;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

import java.util.ArrayList;
import java.util.List;


public class DSWork {
    private  Objectify ofy;
    public DSWork() {
        ObjectifyService.register(EMailAddress.class);//register(EMailAddress.class);
    }
    /*public void begin() {
        ofy = ObjectifyService.beginTransaction();
    }*/
    public  EMailAddress addEMail(String email) /*throws NotFoundException*/ {

        ofy = ObjectifyService.begin();
        EMailAddress e = ofy.find(EMailAddress.class, email);
        //if e-mail is exists.
        if (e == null) {
            e = new EMailAddress(email);
            ofy.put(e);
            return e;

        }
        return null;
    }

    public EMailAddress deleteEMail(String email)   {

        ofy = ObjectifyService.begin();
        final EMailAddress e = ofy.find(EMailAddress.class, email);
        //if e-mail is exists.
        if(e != null)   {
            //delete entity
            ofy.delete(EMailAddress.class, email);
            return e;
        }
        return null;
    }
    public List<EMailAddress> getEMails() {
        ofy = ObjectifyService.begin();
        List<EMailAddress> list = ofy.query(EMailAddress.class).list();//fetch();

        return list;

    }


}