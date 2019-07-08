package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class BoardHandler {
  
  static Board[] boards = new Board[100];
  static int boardsSize = 0;
  static Scanner keyScan;

  static void addBoard() {
    Board board = new Board();
    
    board.no = Input.getIntValue("번호? ");
    board.contents = Input.getStringValue("내용? ");
    board.createDate = new Date(System.currentTimeMillis());
    board.viewCount = Input.getIntValue("조회수? ");
    
    boards[boardsSize++] = board;
    
    System.out.println("저장하였습니다.");
  }
  
  static void listBoard() {
    for (int i = 0; i < boardsSize; i++) {
      Board board = boards[i]; //위의 두 줄을 한번에
      System.out.printf("%d, %-20s , %-10s, %d\n", board.no, board.contents, board.createDate, board.viewCount);
    }
  }

}
