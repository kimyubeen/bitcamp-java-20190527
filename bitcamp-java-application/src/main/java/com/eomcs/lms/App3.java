package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App3 {
  static Scanner keyScan;
  
  public static void main(String[] args) {
    java.io.InputStream keyboard= System.in;
    keyScan = new Scanner(keyboard);
    
    int no = getIntValue("번호? ");
    String content = getStringValue("내용? ");
    java.sql.Date createdDate = getDateValue("작성일? ");
    int hits = getIntValue("조회수? ");
    
    keyScan.close();
    
    System.out.println();
    
    System.out.printf("번호: %d\n", no);
    System.out.printf("내용: %s\n", content);
    System.out.printf("작성일: %s\n", createdDate);
    System.out.printf("조회수: %d\n", hits);
    
  }

  private static int getIntValue(String message) {
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
  
  private static String getStringValue(String message) {
    System.out.printf(message);
    return keyScan.nextLine();
  }
  
  private static Date getDateValue(String message) {
    while (true) {
      try {
        System.out.print(message);
        return java.sql.Date.valueOf(keyScan.nextLine());
      } catch (IllegalArgumentException e) {
        System.out.println("2019-07-04 형태로 입력하세요.");
      }
    }
  }

}
