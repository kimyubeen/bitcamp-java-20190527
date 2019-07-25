package com.eomcs.lms.handler;

import java.util.List;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;

public class BoardListCommand implements Command {
  private List<Board> list;
  private Input input;
  
  public BoardListCommand(Input input, List<Board> list) {
    this.input = input;
    this.list = list;
  }
  
  @Override
  public void execute() {
//    // 1.
//    Board[] boards = boardList.toArray(new Board[] {});
    
   //  2.
    Board[] boards = new Board[list.size()];
    list.toArray(boards);
    
    
    for (Board board : boards) {
      System.out.printf("%d, %-20s , %-10s, %d\n", board.getNo(), board.getContents(),
          board.getCreateDate(), board.getViewCount());
    }
  }

}
