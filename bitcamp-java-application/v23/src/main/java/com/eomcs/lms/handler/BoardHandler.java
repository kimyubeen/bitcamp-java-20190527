package com.eomcs.lms.handler;

import java.sql.Date;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;
import com.eomcs.util.List;

public class BoardHandler {
  private List<Board> boardList;
  private Input input;
  
  public BoardHandler(Input input, List<Board> list) {
    this.input = input;
    this.boardList = list;
  }
  
  public void addBoard() {
    
    Board board = new Board();
    
    
    board.setNo(input.getIntValue("번호? "));
    board.setContents(input.getStringValue("내용? "));
    board.setCreateDate(new Date(System.currentTimeMillis()));
    board.setViewCount(0);
    
    boardList.add(board);
    
    System.out.println("저장하였습니다.");
  }
  
  public void listBoard() {
//    // 1.
//    Board[] boards = boardList.toArray(new Board[] {});
    
   //  2.
    Board[] boards = new Board[boardList.size()];
    boardList.toArray(boards);
    
    
    for (Board board : boards) {
      System.out.printf("%d, %-20s , %-10s, %d\n", board.getNo(), board.getContents(),
          board.getCreateDate(), board.getViewCount());
    }
  }

  public void detailBoard() {
    int no = input.getIntValue("번호? ");
    
    Board board = null;
    for (int i = 0; i < boardList.size(); i++) {
      Board temp = boardList.get(i);
      if (temp.getNo() == no) {
        board = temp;
        break;
      }
    }
    
    if (board == null) {
      System.out.println("해당 게시글을 찾을 수 없습니다.");
      return;
    }
    
    System.out.println("내용: " + board.getContents());
    System.out.println("작성일: " + board.getCreateDate());
    
  }

  public void updateBoard() {
    int no = input.getIntValue("번호? ");
    
    Board board = null;
    for (int i = 0; i < boardList.size(); i++) {
      Board temp = boardList.get(i);
      if (temp.getNo() == no) {
        board = temp;
        break;
      }
    }
    
    if (board == null) {
      System.out.println("해당 게시글을 찾을 수 없습니다.");
      return; // 메소드 종료
    }
    
    String str = input.getStringValue("내용? ");
    if (str.length() > 0) {
      board.setContents(str);
    }
    
    System.out.println("게시글을 변경했습니다.");
    
  }

  public void deleteBoard() {
    int no = input.getIntValue("번호? ");
    
    for (int i = 0; i < boardList.size(); i++) {
      Board temp = boardList.get(i);
      if (temp.getNo() == no) {
        boardList.remove(i);
        System.out.println("게시글을 삭제했습니다.");
        return;
      }
    }
    
    System.out.println("해당 게시글을 찾을 수 없습니다.");
    
  }
  
}
