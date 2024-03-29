// hash code 응용 II - HashMap의 key와 hashCode()
package ch15;

import java.util.HashMap;


public class Test09_1 {
  static class MyKey {
    String contents;

    public MyKey(String contents) {
      this.contents = contents;
    }
    
    @Override
    public String toString() {
      return "MyKey [contents=" + contents + "]";
    }
  }

  public static void main(String[] args) {
    HashMap<MyKey,Student> map = new HashMap<>();

    MyKey k1 = new MyKey("ok");
    MyKey k2 = new MyKey("no");
    MyKey k3 = new MyKey("haha");
    MyKey k4 = new MyKey("ohora");
    MyKey k5 = new MyKey("hul");

    map.put(k1, new Student("홍길동", 20, false));
    map.put(k2, new Student("임꺽정", 30, true));
    map.put(k3, new Student("유관순", 17, true));
    map.put(k4, new Student("안중근", 24, true));
    map.put(k5, new Student("윤봉길", 22, false));
    
    // HashMap
    // => 값을 저장할 때 key 객체의 해시코드를 이용하여 저장할 위치(인덱스)를 계산한다.
    // => 따라서 해시코드가 같다면 같은 key로 간주한다.

    // 값을 저장할 때 사용한 key 객체로 값을 찾아 꺼낸다.
    System.out.println(map.get(k3));

    // key를 사용하여 값을 꺼내보자.
    MyKey k6 = new MyKey("haha");
    
    System.out.println(k3 == k6); // 인스턴스는 다르다.
    System.out.printf("k3(%s), k6(%s)\n", k3, k6);
    System.out.println(k3.hashCode()); // hash code는 다르다.
    System.out.println(k6.hashCode()); // hash code는 다르다.
    System.out.println(k3.equals(k6)); // equals()의 비교 결과도 다르다.

    // k3와 k6이 내용물이 같다 하더라도, (둘다 "haha"이다.)
    // hashCode()의 리턴 값이 다르고, equals() 비교 결과도 false 이기 때문에
    // HashMap 클래스에서는 서로 다른 key라고 간주한다.

    System.out.println(map.get(k6));
  }

}


