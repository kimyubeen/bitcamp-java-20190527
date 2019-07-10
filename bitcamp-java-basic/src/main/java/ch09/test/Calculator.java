package ch09.test;

// 계산 기능과 관련된 메서드를 별도의 블록으로 분리할 때 사용하는 문법이 "클래스"이다.
public class Calculator {
  
  // 개별적으로 관리되어야 할 값은 인스턴스 필드에 저장해야 한다.
  int result;
  
  void plus(int a) {
    this.result += a;
  }
  
  void minus(int a) {
    this.result -= a;
  }
  
  void multiple(int a) {
    this.result *= a;
  }
  
  void divide(int a) {
    this.result /= a;
  }
  

}









