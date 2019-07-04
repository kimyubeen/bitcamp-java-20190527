package com.eomcs.lms;

import java.util.Scanner;

public class App3 {
  public static void main(String[] args) {
    java.io.InputStream keyboard= System.in;
    Scanner keyScan = new Scanner(keyboard);
    java.util.Date today = new java.util.Date();
    
    System.out.print("번호? ");
    String no = keyScan.nextLine();
    
    System.out.print("내용? ");
    String content = keyScan.nextLine();
    
    System.out.print("조회수? ");
    String hits = keyScan.nextLine();
    
    System.out.println();
    
    System.out.println("번호: " + no);
    System.out.println("내용: " + content);
    System.out.printf("작성일: %1$ty-%1$tm-%1$td\n", today);
    System.out.println("조회수: " + hits);
    
  }

}
