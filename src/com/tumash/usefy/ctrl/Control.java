package com.tumash.usefy.ctrl;

import com.google.appengine.api.datastore.QueryResultIterable;
import com.tumash.usefy.json.Answer;
import com.tumash.usefy.objwork.DSWork;
import com.tumash.usefy.objwork.EMailAddress;
import com.tumash.usefy.emailsend.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Control {
    private final static Pattern pattern = Pattern.compile("([a-zA-Z0-9_\\.\\-\\+])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z]{2,4})");
    private final static DSWork dsw = new DSWork();
    private final static Answer answer = new Answer();
    public Control() {
    }
    public static Answer addEMail(String email)  {
        if(validateEMail(email))    {
            //try adding email at DS.
            if(dsw.addEMail(email) != null) {//address successfully added
                //try send letter to email.
                try {
                    SendEMail.send("Your e-mail is successfully added!", email, email);
                    return formAnswer(false, "e-mail is successfully added!");
                } catch (EMailException exc) {
                    return formAnswer(true, "e-mail is added, but mistake when sending letter to e-mail.");
                }
            } else {
                return  formAnswer(true, "the e-mail already exists!");
            }
        } else {
            return formAnswer(true, "the e-mail is incorrectly entered!");
        }
    }
    public static Answer deleteEMail(String email)   {
        //try delete e-mail.
        if(dsw.deleteEMail(email) != null)  {//e-mail is deleted
            return formAnswer(false, "e-mail is deleted.");
        } else {
            return formAnswer(true, "e-mail not found.");
        }
    }
    public static List<EMailAddress> getEMails() {
        return dsw.getEMails();
    }
    private static boolean validateEMail(String email) {
        final Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static void mailing() throws EMailException {
        //get all address.
        List<EMailAddress> emails = getEMails();
        for (EMailAddress e: emails) {
            String email = e.getEmail();
            SendEMail.send("evereday emailing", email, email);
        }
    }
    private static Answer formAnswer(boolean err, String mess)  {
        answer.setErr(err);
        answer.setMess(mess);
        return answer;

    }
}
