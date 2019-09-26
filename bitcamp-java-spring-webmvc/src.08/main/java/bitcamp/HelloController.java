package bitcamp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
// 프론트 컨트롤러가 실행되야 실행된다.

// @Component가 올 수 있다.
// @Controller를 붙이면 pageController로 바라본다.
@Controller
public class HelloController {
  
  // @RequestMapping를 붙인 메서드는 클라이언트가 요청했을 때 호출되는 메서드로 인식된다.
  @RequestMapping("/hello")
  @ResponseBody
  public String hello() throws Exception {
    return "<html><body><h1>Hello!</h1></body></html>";
  }
}
