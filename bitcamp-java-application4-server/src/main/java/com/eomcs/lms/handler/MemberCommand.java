package com.eomcs.lms.handler;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.ServletRequest;
import com.eomcs.util.ServletResponse;

@Component
public class MemberCommand {
  private MemberDao memberDao;

  public MemberCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @RequestMapping("/member/form")
  public void form(ServletRequest request, ServletResponse response) {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>회원 등록 폼</title></head>");
    out.println("<body><h1>회원 등록</h1>");
    out.println("<form action='/member/add'>");
    out.println("이름 : <textarea name='title' rows='5' cols='50'></textarea><br>");
    out.println("이메일 : <textarea name='contents' rows='5' cols='50'></textarea><br>");
    out.println("비밀번호 : <textarea name='startDate' rows='5' cols='50'></textarea><br>");
    out.println("전화번호 : <textarea name='endDate' rows='5' cols='50'></textarea><br>");
    out.println("사진 : <textarea name='totalHours' rows='5' cols='50'></textarea><br>");
    out.println("등록일 : <textarea name='dayHours' rows='5' cols='50'></textarea><br>");
    out.println("<button>등록</button>");
    out.println("</form>");
    out.println("</body></html>");
  }

  @RequestMapping("/member/add") // 클라이언트 요청이 들어 왔을 때 이 메서드를 호출하라고 표시한다.
  public void add(ServletRequest request, ServletResponse response) {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>회원 등록</title>"
        + "<meta http-equiv='Refresh' content='1;url=/member/list'>"
        + "</head>");
    out.println("<body><h1>회원 등록</h1>");
    try {
      Member member = new Member();

      member.setName(request.getParameter("name"));
      member.setEmail(request.getParameter("email"));
      member.setPassword(request.getParameter("password"));
      member.setTel(request.getParameter("tel"));
      member.setPhoto(request.getParameter("photo"));

      memberDao.insert(member);
      out.println("<p>저장하였습니다.</p>");

    } catch (Exception e) {
      out.println("<p>데이터 저장에 실패했습니다!</p>");
      System.out.println(e.getMessage());
    }
    out.println("</body></html>");
  }

  @RequestMapping("/member/delete") // 클라이언트 요청이 들어 왔을 때 이 메서드를 호출하라고 표시한다.
  public void delete(ServletRequest request, ServletResponse response) {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>회원 삭제</title>"
        + "<meta http-equiv='Refresh' content='1;url=/member/list'>"
        + "</head>");
    out.println("<body><h1>회원 삭제</h1>");
    try {
      int no = Integer.parseInt(request.getParameter("no"));

      if (memberDao.delete(no) > 0) {
        out.println("<p>데이터를 삭제하였습니다.</p>");
      } else {
        out.println("<p>해당 데이터가 없습니다.</p>");
      }

    } catch (Exception e) {
      out.println("<p>데이터를 삭제에 실패했습니다.</p>");
      System.out.println(e.getMessage());
    }
    out.println("</body></html>");

  }

  @RequestMapping("/member/detail") // 클라이언트 요청이 들어 왔을 때 이 메서드를 호출하라고 표시한다.
  public void detail(ServletRequest request, ServletResponse response) {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>회원 상세</title></head>");
    out.println("<body><h1>회원 상세</h1>");
    try {
      int no = Integer.parseInt(request.getParameter("no"));

      Member member = memberDao.findBy(no);
      
      if (member == null) {
        out.println("<p>해당 번호의 데이터가 없습니다!</p");
        
      } else {

        out.println("<form action='/member/update'>");
        out.printf("번호 : <input type='text' name='no' value='%d' readonly><br>\n",
            member.getNo());
        out.printf("이름: <textarea name='name' rows='5' cols='50'>%s</textarea><br>\n",
            member.getName());
        out.printf("이메일: <textarea name='email' rows='5' cols='50'>%s</textarea><br>\n",
            member.getEmail());
        out.printf("암호: <textarea name='password' rows='5' cols='50'>%s</textarea><br>\n",
            member.getPassword());
        out.printf("사진: <textarea name='photo' rows='5' cols='50'>%s</textarea><br>\n",
            member.getPhoto());
        out.printf("전화: <textarea name='tel' rows='5' cols='50'>%s</textarea><br>\n",
            member.getTel());
        out.println("<button>변경</button>");
        out.printf("<a href='/member/delete?no=%d'>삭제</a>\n", member.getNo());
        out.println("</form>");
      }
    } catch (Exception e) {
      out.println("<p>데이터 조회에 실패했습니다!</p>");
      System.out.println(e.getMessage());
    }
    out.println("</body></html>");
  }

  @RequestMapping("/member/list") // 클라이언트 요청이 들어 왔을 때 이 메서드를 호출하라고 표시한다.
  public void list(ServletRequest request, ServletResponse response) {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>회원 목록</title>"
        + "<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' integrity='sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T' crossorigin='anonymous'>"
        + "</head>");
    out.println("<body><h1>회원 목록</h1>");
    out.println("<a href='member/form'>새 회원</a><br>");
    try {
      out.println("<table class='table table-hover'>");
      out.println("<tr><th>번호</th><th>이름</th><th>이메일</th>"
          + "<th>전화번호</th><th>가입일</th></tr>");

      List<Member> members = memberDao.findAll();
      for (Member member : members) {
        out.printf("<tr><td>%d</td>"
            + "<td><a href='/member/detail?no=%d'>%s</a></td>"
            + "<td>%s</td><td>%s</td><td>%s</td></tr>\n", 
            member.getNo(),
            member.getNo(),
            member.getName(),
            member.getEmail(), 
            member.getTel(),
            member.getRegisteredDate());
      }
      out.println("</table>");

    } catch (Exception e) {
      out.println("<p>데이터 목록 조회에 실패했습니다!</p>");
      System.out.println(e.getMessage());
    }
    out.println("</body></html>");
  }

  @RequestMapping("/member/search") // 클라이언트 요청이 들어 왔을 때 이 메서드를 호출하라고 표시한다.
  public void search(ServletRequest request, ServletResponse response) {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>회원 조회</title>"
        + "<body><h1>회원 조회</h1>");
    try {
      String keyword = request.getParameter("contents");

      List<Member> members = memberDao.findByKeyword(keyword);
      for (Member member : members) {
        out.printf("<tr><td>%s</td>"
            + "<td><a href='/member/detail?no=%d'>%s</a></td>"
            + "<td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>\n", 
            member.getNo(),
            member.getNo(), member.getName(), member.getEmail(), 
            member.getTel(), member.getRegisteredDate());
      }

    } catch (Exception e) {
      out.println("<p>데이터 변경에 실패했습니다.</p>");
      System.out.println(e.getMessage());
    }
    out.println("</body></html>");
  }

  @RequestMapping("/member/update") // 클라이언트 요청이 들어 왔을 때 이 메서드를 호출하라고 표시한다.
  public void update(ServletRequest request, ServletResponse response) {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>회원 변경</title>"
        + "<meta http-equiv='Refresh' content='1;url=/member/list'>"
        + "</head>");
    out.println("<body><h1>회원 변경</h1>");

    try {
      Member member = new Member();
      member.setNo(Integer.parseInt(request.getParameter("no").trim()));
      member.setName(request.getParameter("name"));
      member.setEmail(request.getParameter("email"));
      member.setPassword(request.getParameter("password"));
      member.setTel(request.getParameter("tel"));
      member.setPhoto(request.getParameter("photo"));
      member.setRegisteredDate(Date.valueOf(request.getParameter("registeredDate").trim()));

      memberDao.update(member);
      out.println("<p>변경 했습니다</p>");
      
    } catch (Exception e) {
      out.println("<p>데이터 변경에 실패했습니다.</p>");
      System.out.println(e.getMessage());
    }
    out.println("</body></html>");
  }
}
