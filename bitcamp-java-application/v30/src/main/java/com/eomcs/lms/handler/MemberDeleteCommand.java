package com.eomcs.lms.handler;

import java.util.List;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Input;

public class MemberDeleteCommand implements Command {
  
  private List<Member> list;
  private Input input;
  
  public MemberDeleteCommand(Input input, List<Member> list) {
    this.input = input;
    this.list = list;
  }

  @Override
  public void execute() {
    int no = input.getIntValue("번호? ");
    
    for (int i = 0; i < list.size(); i++) {
      Member temp = list.get(i);
      if (temp.getNo() == no) {
        list.remove(i);
        System.out.println("회원을 삭제했습니다.");
        return;
      }
    }
    
    System.out.println("해당 회원을 찾을 수 없습니다.");
    
  }
  
}
