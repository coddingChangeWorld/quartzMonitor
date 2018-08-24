package com.controller;

import com.common.base.dao.BaseDaoImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/login"})
public class loginController
{
  @Autowired
  private BaseDaoImpl baseDaoImpl;
  
  @RequestMapping({"/index"})
  public String teamList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
  {
    return "login";
  }
  
  @RequestMapping({"/handler"})
  public String handler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
  {
    String userName = request.getParameter("");
    String password = request.getParameter("");
    return "/index";
  }
}
