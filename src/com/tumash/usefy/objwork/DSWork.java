package com.tumash.usefy.objwork;

import com.google.appengine.api.datastore.QueryResultIterable;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import com.tumash.usefy.tag.Tag;

import java.util.ArrayList;
import java.util.List;


public class DSWork {
    private  Objectify ofy;
    static {
        ObjectifyService.register(EMailAddress.class);//register(EMailAddress.class);
        ObjectifyService.register(Tag.class);
    }
    public DSWork() {

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
    public List<Tag> getTags()   {
        ofy = ObjectifyService.begin();
        List<Tag> list = ofy.query(Tag.class).list();
        return list;
    }
    public Tag getTagById(Long id)  {
        ofy = ObjectifyService.begin();
        return ofy.query(Tag.class).filter("id =", id).get();
    }
    public List<Tag> getParentsById(List<Long> listId)   {
        ofy = ObjectifyService.begin();
        return ofy.query(Tag.class).filter("id in", listId).list();
    }
    public List<Tag> getChildById(Long id)  {
        ofy = ObjectifyService.begin();
        List<Long> list = new ArrayList<Long>();
        list.add(id);
        return ofy.query(Tag.class).filter("parents in", list).list();
    }


}