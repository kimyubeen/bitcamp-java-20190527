package ch23.c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  public static void main(String[] args) {
    
    try(ServerSocket serverSocket = new ServerSocket(8888);){
        System.out.println("서버 실행 중...");
      
      try (Socket socket = serverSocket.accept();
          PrintStream out = new PrintStream(socket.getOutputStream());
          BufferedReader in = new BufferedReader(
              new InputStreamReader(socket.getInputStream()))) {
        
        String[] input = in.readLine().split(" ");
        
        int n1 = Integer.parseInt(input[0]);
        String op = input[1];
        int n2 = Integer.parseInt(input[2]);
        
        out.printf("연산결과는 = %d", n1 + n2);
        
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
