package btTCP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


public class Baitap2ServerUDP {

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
        // array tinh snt
        List<Integer> array = new ArrayList<>();
        // array tinh tong
        List<Integer> arrayT = new ArrayList<>();
        String[] abc = message.split(" ");
//        for (int i = Integer.parseInt(abc[0]); i < Integer.parseInt(abc[1]); i++) {
          //tinh tong
          String tong = String.valueOf(tinhTong(Integer.parseInt(message)));
          System.out.println("tong+ " + tong);
//          if (tong == 15) {
//            arrayT.add(i);
//          }
//          //tinh snt
//          if (isPrimeNumber(i)) {
//            System.out.println("snt" + i);
//            array.add(i);
//          }
//          //
//        }
//        String  max = String.valueOf(array);
//        System.out.println(arrayT);
        // Tạo gói tin gởi chứa dữ liệu vừa nhận được
        DatagramPacket outsending = new DatagramPacket(tong.getBytes(), tong.length(),
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
  public static boolean isPrimeNumber(int n) {
    // so nguyen n < 2 khong phai la so nguyen to
    if (n < 2) {
      return false;
    }
    // check so nguyen to khi n >= 2
    int squareRoot = (int) Math.sqrt(n);
    for (int i = 2; i <= squareRoot; i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static int tinhTong(int n) {
    int tong = 0, t, tmp = n;
    while (tmp > 0) {
      t = tmp % 10;
      tong += t;
      tmp /= 10;
    }
    return tong;
  }
}
