package com.eomcs.lms.handler;

import java.util.List;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;

public class BoardListCommand implements Command {
  private BoardDao boardDao;

  public BoardListCommand(Input input, BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void execute() {
    //    // 1.
    //    Board[] boards = boardList.toArray(new Board[] {});

    //  2.
    try {
      List<Board> boards = boardDao.findAll();

      for (Board board : boards) {
        System.out.printf("%d, %-20s , %-10s, %d\n", board.getNo(), board.getContents(),
            board.getCreateDate(), board.getViewCount());
      }
    } catch (Exception e) {
      System.out.println("데이터 목록 조회에 실패했습니다.");
      System.out.println(e.getMessage());
    }
  }

}
