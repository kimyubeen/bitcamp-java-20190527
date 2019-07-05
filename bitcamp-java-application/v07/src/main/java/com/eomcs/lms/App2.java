package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App2 {
  static Scanner keyScan;
  
  public static void main(String[] args) {
    java.io.InputStream keyboard = System.in;
    keyScan = new Scanner(keyboard);
    
    Member[] members = new Member[100];
    
    int i = 0;
    for(; i < members.length; i++) {
      Member member = new Member();
      
      member.no = getIntValue("번호?");
      member.name = getStringValue("이름? ");
      member.email = getStringValue("이메일? ");
      member.pw = getStringValue("암호? ");
      member.picture = getStringValue("사진? ");
      member.phoneNumber = getStringValue("전화? ");
      member.registerDate = new Date(System.currentTimeMillis());
      
      members[i] = member;
          
      System.out.println();
      System.out.printf("계속 입력하시겠습니까?(Y/n)");
      String response = keyScan.nextLine();
      System.out.println();
      
      if (response.equals("n"))
          break;
    }
    
    System.out.println();
    
    int i2 = 0;
    for(; i2 <= i; i2++) {
      Member member = members[i2];
      System.out.printf("%d, %-5s , %-20s , %-15s , %s\n", member.no, member.name, member.email,
          member.phoneNumber, member.registerDate);
    }
  }
  
  public static int getIntValue(String message) {
    int value = 0;
    while (true) {
      try {
        System.out.print(message);
        return Integer.parseInt(keyScan.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("숫자를 입력하세요.");
      }
    }
  }
  
  public static String getStringValue(String message) {
    System.out.printf(message);
    return keyScan.nextLine();
  }
  
//  public static java.sql.Date getDateValue(String message){
//    while (true) {
//      try {
//        System.out.print(message);
//        return java.sql.Date.valueOf(keyScan.nextLine());
//      } catch (IllegalArgumentException e) {
//        System.out.println("2019-07-04 형태로 입력하세요.");
//      }
//    }
//  }

}
