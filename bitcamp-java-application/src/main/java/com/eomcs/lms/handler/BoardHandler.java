package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.util.Input;

public class BoardHandler {
  
  private Board[] boards = new Board[100];
  private int boardsSize = 0;
  public static Scanner keyScan;

  public void addBoard() {
    Board board = new Board();
    
    board.no = Input.getIntValue("번호? ");
    board.contents = Input.getStringValue("내용? ");
    board.createDate = new Date(System.currentTimeMillis());
    board.viewCount = Input.getIntValue("조회수? ");
    
    boards[boardsSize++] = board;
    
    System.out.println("저장하였습니다.");
  }
  
  public void listBoard() {
    for (int i = 0; i < boardsSize; i++) {
      Board board = boards[i]; //위의 두 줄을 한번에
      System.out.printf("%d, %-20s , %-10s, %d\n", board.no, board.contents, board.createDate, board.viewCount);
    }
  }

}
