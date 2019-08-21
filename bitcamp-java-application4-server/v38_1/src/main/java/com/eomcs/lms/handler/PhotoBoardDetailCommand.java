package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.util.Input;

public class PhotoBoardDetailCommand implements Command {
  
  private PhotoBoardDao PhotoBoardDao;
  
  public PhotoBoardDetailCommand(PhotoBoardDao PhotoBoardDao) {
    this.PhotoBoardDao = PhotoBoardDao;
  }
  
  @Override
  public void execute(BufferedReader in, PrintStream out) {

    try {
      // 클라이언트에게 번호를 요구하여 받는다.
      int no = Input.getIntValue(in, out, "번호? ");
      
      // 사용자가 입력한 번호를 가지고 목록에서 그 번호에 해당하는 PhotoBoard 객체를 찾는다.
      PhotoBoard PhotoBoard = PhotoBoardDao.findBy(no);
      
      if (PhotoBoard == null) {
        out.println("해당 번호의 데이터가 없습니다!");
        return;
      }
      
      out.printf("제목: %s\n", PhotoBoard.getTitle());
      out.printf("작성일: %s\n", PhotoBoard.getCreatedDate());
      out.printf("조회수: %s\n", PhotoBoard.getViewCount());
      out.printf("수업: %s\n", PhotoBoard.getLessonNo());
      
    } catch (Exception e) {
      out.println("데이터 조회에 실패했습니다!");
      System.out.println(e.getMessage());
    }
  }

}
