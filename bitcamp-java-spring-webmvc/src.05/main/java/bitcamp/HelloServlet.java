package bitcamp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// 서블릿 컨테이너가 직접 실행한다.

@SuppressWarnings("serial")
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
  @Override
  protected void doGet(
      HttpServletRequest request, 
      HttpServletResponse response)
      throws ServletException, IOException {
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<!DOCTYPE html>");
    out.println("<html><head><title>hello</title></head>");
    out.println("<body><h1>Hello</h1></body></html>");
  }
}







