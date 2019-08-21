package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.util.Input;

public class PhotoBoardAddCommand implements Command {
  
  private PhotoBoardDao PhotoBoardDao;
  
  public PhotoBoardAddCommand(PhotoBoardDao PhotoBoardDao) {
    this.PhotoBoardDao = PhotoBoardDao;
  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {
    
    try {
      PhotoBoard PhotoBoard = new PhotoBoard();
      PhotoBoard.setTitle(Input.getStringValue(in, out, "제목? "));
      PhotoBoard.setLessonNo(Input.getIntValue(in, out, "수업? "));
      
      PhotoBoardDao.insert(PhotoBoard);
      out.println("사진을 저장했습니다.");
      
    } catch (Exception e) {
      out.println("데이터 저장에 실패했습니다!");
      System.out.println(e.getMessage());
    }
  }

}
