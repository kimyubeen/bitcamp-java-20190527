package com.eomcs.lms.handler;

import java.util.List;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.Input;

public class LessonListCommand implements Command {

  private LessonDao lessonDao;

  public LessonListCommand(Input input,  LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void execute() {
    try {
      List<Lesson> lessons = lessonDao.findAll();
      for (Lesson lesson : lessons) {
        // 그 인스턴스 주소로 찾아가서 인스턴스의 각 변수 값을 꺼내 출력한다.
        System.out.printf("%d, %-20s, %s ~ %s, %s\n", lesson.getNo(), lesson.getTitle(), 
            lesson.getStartDate(), lesson.getEndDate(), lesson.getTotalHours());
      }
    } catch (Exception e) {
      System.out.println("데이터 목록 조회에 실패했습니다.");
      System.out.println(e.getMessage());
    }
  }

}
