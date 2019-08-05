package com.eomcs.lms.handler;

import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Input;

public class MemberDetailCommand implements Command {

  private MemberDao memberDao;
  private Input input;

  public MemberDetailCommand(Input input, MemberDao memberDao) {
    this.input = input;
    this.memberDao = memberDao;
  }

  @Override
  public void execute() {
    int no = input.getIntValue("번호? ");

    Member member;
    try {
      member = memberDao.findBy(no);

      if (member == null) {
        System.out.println("해당 학생을 찾을 수 없습니다.");
        return;
      }

      System.out.println("이름: " + member.getName());
      System.out.println("이메일: " + member.getEmail());
      System.out.println("암호: " + member.getPassword());
      System.out.println("사진: " + member.getPhoto());
      System.out.println("전화: " + member.getTel());
      System.out.println("가입일: " + member.getRegisterDate());

    }catch (Exception e) {
      System.out.println("데이터 조회에 실패했습니다.");
      System.out.println(e.getMessage());
    }

  }

}
