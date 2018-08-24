package com.controller;

import com.common.base.dao.BaseDaoImpl;
import com.common.tool.DataResponse;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/user"})
public class userController
{
  @Autowired
  private BaseDaoImpl baseDaoImpl;
  
  @RequestMapping({"/user_exist"})
  @ResponseBody
  public DataResponse teamList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
    throws Exception
  {
    String userName = request.getParameter("UserName");
    Map<String, Object> map = new HashMap();
    map.put("UserName", userName);
    String id = null;
    if (id == null) {
      return new DataResponse(200, "", "用户名不存在");
    }
    return new DataResponse(500, "", "用户名已存在");
  }
  
  @RequestMapping({"/register"})
  public String register(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
    throws Exception
  {
    return "register";
  }
  
  @RequestMapping({"/login"})
  public String login(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
    throws Exception
  {
    return "login";
  }
}
