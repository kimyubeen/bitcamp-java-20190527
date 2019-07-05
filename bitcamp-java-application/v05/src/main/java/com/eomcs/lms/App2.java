package com.eomcs.lms;

import java.util.Scanner;
import java.sql.Date;

public class App2 {
  static Scanner keyScan;
  
  public static void main(String[] args) {
    java.io.InputStream keyboard = System.in;
    keyScan = new Scanner(keyboard);
    
    int no = getIntValue("번호? ");
    String name = getStringValue("이름? ");
    String email = getStringValue("이메일? ");
    String pw = getStringValue("암호? ");
    String picture = getStringValue("사진? ");
    String phoneNumber = getStringValue("전화? ");
    java.sql.Date registeredDate = getDateValue("가입일? ");
    
    keyScan.close();
    
    System.out.println();
    
    System.out.printf("번호: %d\n", no);
    System.out.printf("이름: %s\n", name);
    System.out.printf("이메일: %s\n", email);
    System.out.printf("암호: %s\n", pw);
    System.out.printf("사진: %s\n", picture);
    System.out.printf("전화: %s\n", phoneNumber);
    System.out.printf("가입일: %s\n", registeredDate);
    
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
  
  public static java.sql.Date getDateValue(String message){
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
