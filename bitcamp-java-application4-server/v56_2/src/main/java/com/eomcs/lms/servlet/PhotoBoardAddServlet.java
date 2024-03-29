package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;

@WebServlet("/photoboard/add")
public class PhotoBoardAddServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  // 이 클래스에서 로그를 출력할 일이 있다면 다음과 같이 로거를 만들어 사용하라!
  /*
  private static final Logger logger = 
      LogManager.getLogger(PhotoBoardCommand.class);
  */
  
  private PhotoBoardDao photoBoardDao;
  private PhotoFileDao photoFileDao;
  
  @Override
  public void init() throws ServletException {
    ApplicationContext appCtx = 
        (ApplicationContext) getServletContext().getAttribute("iocContainer");
    photoBoardDao = appCtx.getBean(PhotoBoardDao.class);
    photoFileDao = appCtx.getBean(PhotoFileDao.class);
  }
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html;charSet=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>사진게시물 등록폼</title></head>");
    out.println("<body><h1>사진게시물 등록폼</h1>");
    out.println("<form action='/photoboard/add' method='post'>");
    out.println("제목: <input type='text' name='title'><br>");
    out.println("수업: <input type='text' name='lessonNo'><br>");
    out.println("사진1: <input type='text' name='filePath1'><br>");
    out.println("사진2: <input type='text' name='filePath2'><br>");
    out.println("사진3: <input type='text' name='filePath3'><br>");
    out.println("사진4: <input type='text' name='filePath4'><br>");
    out.println("사진5: <input type='text' name='filePath5'><br>");
    out.println("사진6: <input type='text' name='filePath6'><br>");
    out.println("<button>등록</button>");
    out.println("</form>");
    out.println("</body></html>");
  }
  
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html;charSet=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>사진게시물 등록</title>"
        + "<meta http-equiv='Refresh' content='1;url=/photoboard/list'>"
        + "</head>");
    out.println("<body><h1>사진게시물 등록</h1>");
    try {
      PhotoBoard photoBoard = new PhotoBoard();
      photoBoard.setTitle(request.getParameter("title"));
      photoBoard.setLessonNo(Integer.parseInt(request.getParameter("lessonNo")));
      
      photoBoardDao.insert(photoBoard);
      
      int count = 0;
      for (int i = 1; i <= 6; i++) {
        String filepath = request.getParameter("filePath" + i);
        if (filepath.length() == 0) {
          continue;
        }
        PhotoFile photoFile = new PhotoFile();
        photoFile.setFilePath(filepath);
        photoFile.setBoardNo(photoBoard.getNo());
        photoFileDao.insert(photoFile);
        count++;
      }
      
      if (count == 0) {
        throw new Exception("사진 파일 없음!");
      }
      
      out.println("<p>저장하였습니다.</p>");
      
    } catch (Exception e) {
      out.println("<p>데이터 저장에 실패했습니다!</p>");
      throw new RuntimeException(e);
      
    } finally {
      out.println("</body></html>");
    }
  }
}
