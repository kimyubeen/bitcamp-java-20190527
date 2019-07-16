package com.eomcs.lms.handler;

import java.sql.Date;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.ArrayList;
import com.eomcs.util.Input;

public class MemberHandler {
  
  private ArrayList memberList = new ArrayList();
  private Input input;
  
  public MemberHandler(Input input) {
    this.input = input;
  }

  public void addMember() {
    Member member = new Member();
    
    member.setNo(input.getIntValue("번호?"));
    member.setName(input.getStringValue("이름? "));
    member.setEmail(input.getStringValue("이메일? "));
    member.setPassword(input.getStringValue("암호? "));
    member.setPhoto(input.getStringValue("사진? "));
    member.setTel(input.getStringValue("전화? "));
    member.setRegisterDate(new Date(System.currentTimeMillis()));
    
    memberList.add(member);
    
    System.out.println("저장하였습니다.");
  }
  
  public void listMember() {
    Object[] list = memberList.toArray();
    
    for (Object obj : list) {
      Member member = (Member) obj;
      System.out.printf("%d, %-5s , %-20s , %-15s , %s\n", member.getNo(), member.getName(), 
          member.getEmail(), member.getTel(), member.getRegisterDate());
    }
  }
  
  

}
