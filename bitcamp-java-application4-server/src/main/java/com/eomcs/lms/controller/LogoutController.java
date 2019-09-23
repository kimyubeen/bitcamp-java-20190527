package com.eomcs.lms.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component("/auth/logout")
public class LogoutController implements PageController {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {

    request.getSession().invalidate();
    
    return "redirect:login";
    
  }
}
