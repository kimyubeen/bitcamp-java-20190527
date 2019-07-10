package ch09.test;

public class Test {
  public static void main(String[] args) {
    // 계산하기
    // 식1) 2 * 3 - 2 + 7 = ?
    // 식2) 6 / 2 + 4 = ?
    
    // Calculator2의 result 변수는 오직 한 개만 존재한다.
    // 따라서 다음과 같이 두 개의 계산식을 동시에 수행할 수 없다.
    // 동시에 계산식을 수행하는 방법은 없을까?
    // Calculator2 는 불가능하다. 
    // 오히려 Calculator1 은 가능한다. 왜? 계산 결과를 호출하는 쪽에서 관리하기 때문이다.
    
    Calculator carc1 = new Calculator();
    Calculator carc2 = new Calculator();
    
    carc1.plus(2);
    carc2.plus(6);
    
    carc1.multiple(3); // 6
    carc2.divide(2); // 3
    
    carc1.minus(2); // 4
    carc2.plus(4); // 7
    
    carc1.plus(7); // 11
    
    
    System.out.printf("계산식1의 결과 = %d\n", carc1.result);
    System.out.printf("계산식2의 결과 = %d\n", carc2.result);
  }

}
