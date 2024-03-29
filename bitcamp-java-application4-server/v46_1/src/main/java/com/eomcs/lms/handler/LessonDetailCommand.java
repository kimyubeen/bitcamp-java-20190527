package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.Input;

public class LessonDetailCommand implements Command {

  private LessonDao lessonDao;

  public LessonDetailCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  public String getCommandName() {
    return "/lesson/detail";
  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {
    try {
      int no = Input.getIntValue(in, out, "번호? ");
      
      Lesson lesson;
      
      lesson = lessonDao.findBy(no);

      if (lesson == null) {
        out.println("해당 번호의 데이터가 없습니다.");
        return;
      }

      out.printf("수업명: %s\n", lesson.getTitle());
      out.printf("수업내용: %s\n", lesson.getContents());
      out.printf("기간: %s ~ %s\n", lesson.getStartDate(), lesson.getEndDate());
      out.printf("총수업시간: %s\n", lesson.getTotalHours());
      out.printf("일수업시간: %s\n", lesson.getDayHours());

    } catch (Exception e) {
      out.println("데이터 조회에 실패했습니다.");
      System.out.println(e.getMessage());
    }
  }

}
