package com.controller.system;

import com.common.base.dao.BaseDaoImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/system"})
public class systemController
{
  @Autowired
  private BaseDaoImpl baseDaoImpl;
  
  @RequestMapping(value={"/settings"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String teamList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
    throws Exception
  {
    return "system/settings";
  }
}
