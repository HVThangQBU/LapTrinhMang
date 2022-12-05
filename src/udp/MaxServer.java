package udp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class MaxServer {


  public final static int SERVER_PORT = 7; // Cổng mặc định của Echo Server
  public final static byte[] BUFFER = new byte[4096]; // Vùng đệm chứa dữ liệu cho gói tin nhận

  public static void main(String[] args) {
    DatagramSocket ds = null;
    try {
      System.out.println("Binding to port " + SERVER_PORT + ", please wait  ...");
      ds = new DatagramSocket(SERVER_PORT); // Tạo Socket với cổng là 7
      System.out.println("Server started ");
      System.out.println("Waiting for messages from Client ... ");

      while (true) { // Tạo gói tin nhận
        DatagramPacket incoming = new DatagramPacket(BUFFER, BUFFER.length);
        ds.receive(incoming); // Chờ nhận gói tin gởi đến

        // Lấy dữ liệu khỏi gói tin nhận
        String message = new String(incoming.getData(), 0, incoming.getLength());
        System.out.println("Received: " + message);
        StringTokenizer st = new StringTokenizer(message);
        List<Integer> array = new ArrayList<>();
        int tong = 0;
        while (st.hasMoreTokens()) {
          int a = Integer.valueOf(st.nextToken());
          System.out.println("dodai: "+a);
          array.add(a);
        }
        String  max = String.valueOf(Collections.max(array));
        System.out.println(max);
        // Tạo gói tin gởi chứa dữ liệu vừa nhận được
        DatagramPacket outsending = new DatagramPacket(max.getBytes(), max.length(),
          incoming.getAddress(), incoming.getPort());
        ds.send(outsending);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (ds != null) {
        ds.close();
      }
    }
  }
}
