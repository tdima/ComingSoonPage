package com.tumash.usefy.controllers;

import com.tumash.usefy.ctrl.Control;
import com.tumash.usefy.emailsend.EMailException;
import com.tumash.usefy.json.Answer;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Logger;


@Controller
@RequestMapping("/")
public class MainController {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static Answer answer = new Answer();
    @RequestMapping(method = RequestMethod.GET)
    public static String mainPage()  {
        System.out.println("MainPageController");
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public static String getEMails(ModelMap model)   {
        model.addAttribute("listEMails", Control.getEMails());
        return "list";
    }
    @RequestMapping(method = RequestMethod.GET, value = "/adding", params = "email")
    @ResponseBody
    public static String addEMail(HttpServletRequest request)   {
        String email = request.getParameter("email");
        if(email != null)   {
            answer = Control.addEMail(email);
            try {
                return objectMapper.writeValueAsString(answer);
            } catch (IOException e) {
                return null;
            }
        }
        return null;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/deleting", params = "email")
    @ResponseBody
    public static String deleteEMail(HttpServletRequest request)   {
        String email = request.getParameter("email");
        if(email != null)   {
            answer = Control.deleteEMail(email);
            try {
                return objectMapper.writeValueAsString(answer);
            } catch (IOException e) {
                return null;
            }
        }
        return null;
    }
    @RequestMapping("/mailing")
    public static void mailing()    {
        try {
            Control.mailing();
        } catch (EMailException e) {

        }
    }
}
