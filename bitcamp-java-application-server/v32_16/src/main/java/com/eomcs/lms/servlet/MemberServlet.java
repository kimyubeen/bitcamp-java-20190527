package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import com.eomcs.lms.Servlet;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

public class MemberServlet implements Servlet {
 
  // 회원 데이터 관리 DAO를 교체하기 쉽도록 인터페이스에 레퍼런스로 선언한다.
  MemberDao memberDao;

  ObjectInputStream in;
  ObjectOutputStream out;

  public MemberServlet(MemberDao memberDao, ObjectInputStream in, ObjectOutputStream out) throws Exception {
    this.in = in;
    this.out = out;
    
    // 서블릿이 사용할 DAO를 직접 만들지 않고 외부에서 주입 받아 사용한다.
    // 이렇게 의존하는 객체를 주입 받아 사용하는 방법을
    // "의존성 주입(Dependency Injection; DI)"이라 부른다.
    // => 그래야만 의존 객체를 교체하기 쉽다.
    //
    this.memberDao = memberDao;
  }
  
  @Override
  public void service(String command) throws Exception {
    switch (command) {
      case "/member/add":
        addMember();
        break;
      case "/member/list":
        listMember();
        break;
      case "/member/delete":
        deleteMember();
        break;
      case "/member/detail":
        detailMember();
        break;
      case "/member/update":
        updateMember();
        break;
      default:
        out.writeUTF("fail");
        out.writeUTF("지원하지 않는 명령입니다.");
    }

  }

//  private void updateMember0() throws Exception {
//    Member member = (Member)in.readObject();
//
//    for (Member m : memberList) {
//      if (m.getNo() == member.getNo()) {
//        // 클라이언트가 보낸 값으로 기존 객체의 값을 바꾼다.
//        m.setName(member.getName());
//        m.setEmail(member.getEmail());
//        m.setPassword(member.getPassword());
//        m.setPhoto(member.getPhoto());
//        m.setTel(member.getTel());
//        m.setRegisteredDate(member.getRegisteredDate());
//        out.writeUTF("ok");
//        return;
//      }
//    }
//
//    fail("해당 번호의 회원이 없습니다.");
//  }

  private void updateMember() throws Exception {
    Member member = (Member)in.readObject();
    member.setRegisteredDate(new Date(System.currentTimeMillis()));

    if (memberDao.update(member) == 0) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }
    out.writeUTF("ok");

    /*
    for (int i = 0; i < memberList.size(); i++) {
      Member m = memberList.get(i);
      if (m.getNo() == member.getNo()) {
        // 기존 객체를 클라이언트가 보낸 객체로 교체한다.
        memberList.set(i, member);
        out.writeUTF("ok");
        return;
      }
    }
    fail("해당 번호의 회원이 없습니다.");
     */
  }

  private void detailMember() throws Exception {
    int no = in.readInt();

    Member member = memberDao.findBy(no);
    
    if (member == null) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }
    out.writeUTF("ok");
    out.writeObject(member);

    /*
    for (Member m : memberList) {
      if (m.getNo() == no) {
        out.writeUTF("ok");
        out.writeObject(m);
        return;
      }
    }
    fail("해당 번호의 회원이 없습니다.");
     */
  }

  private void deleteMember() throws Exception {
    int no = in.readInt();

    if (memberDao.delete(no) == 0) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }
    out.writeUTF("ok");

    /*
    for (Member m : memberList) {
      if (m.getNo() == no) {
        memberList.remove(m);
        out.writeUTF("ok");
        return;
      }
    }
    fail("해당 번호의 회원이 없습니다.");
     */
  }

  private void addMember() throws Exception {
    Member member = (Member)in.readObject();
    member.setRegisteredDate(new Date(System.currentTimeMillis()));
    if (memberDao.insert(member) == 0) {
      fail("회원을 추가할 수 없습니다.");
      return;
    }
    out.writeUTF("ok");
  }

  private void listMember() throws Exception {
    out.writeUTF("ok");
    out.reset(); // 기존에 serialize 했던 객체의 상태를 무시하고 다시 serialize 한다.
    out.writeObject(memberDao.findAll());
  }

  private void fail(String cause) throws Exception {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }

}
