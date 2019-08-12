package com.eomcs.lms.handler;

import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;

public class BoardDetailCommand implements Command {
  private BoardDao boardDao;
  private Input input;
  
  public BoardDetailCommand(Input input, BoardDao boardDao) {
    this.input = input;
    this.boardDao = boardDao;
  }

  @Override
  public void execute() {
    int no = input.getIntValue("번호? ");
    
    Board board;
    try {
      board = boardDao.findBy(no);
      
      if (board == null) {
        System.out.println("해당 게시글을 찾을 수 없습니다.");
        return;
      }
      
      System.out.printf("내용: %s\n", board.getContents());
      System.out.printf("작성일: %s\n", board.getCreateDate());
      
    } catch (Exception e) {
      System.out.println("데이터 조회에 실패했습니다.");
      System.out.println(e.getMessage());
    }
    
  }
  
}
