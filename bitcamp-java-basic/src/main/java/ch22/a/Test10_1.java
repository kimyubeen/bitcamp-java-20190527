// java.io.File 클래스 : 필터 사용하기
package ch22.a;

import java.io.File;
import java.io.FilenameFilter;

public class Test10_1 {

  // static nested class
  static class TextFileFilter implements FilenameFilter {
    @Override
    public boolean accept(File dir, String name) {
      return name.endsWith(".txt"); // 디렉토리도 같이 나오는 문제가 있다.
    }
  }
  
  public static void main(String[] args) throws Exception {
    
    File file = new File(".");
    
    String[] names = file.list(new TextFileFilter());
    
    for (String name : names) {
      System.out.println(name);
    }
    
  }    
}









