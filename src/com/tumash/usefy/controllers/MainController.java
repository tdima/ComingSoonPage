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
    private final Control ctrl = new Control();
    private Answer answer = new Answer();
    @RequestMapping(method = RequestMethod.GET)
    public static String mainPage()  {
        System.out.println("MainPageController");
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public String getEMails(ModelMap model)   {
        model.addAttribute("listEMails", ctrl.getEMails());
        return "list";
    }
    @RequestMapping(method = RequestMethod.GET, value = "/adding", params = "email")
    @ResponseBody
    public String addEMail(HttpServletRequest request)   {
        String email = request.getParameter("email");
        if(email != null)   {
            answer = ctrl.addEMail(email);
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
    public String deleteEMail(HttpServletRequest request)   {
        String email = request.getParameter("email");
        if(email != null)   {
            answer = ctrl.deleteEMail(email);
            try {
                return objectMapper.writeValueAsString(answer);
            } catch (IOException e) {
                return null;
            }
        }
        return null;
    }
    @RequestMapping("/mailing")
    public void mailing()    {
        try {
            ctrl.mailing();
        } catch (EMailException e) {

        }
    }
}
