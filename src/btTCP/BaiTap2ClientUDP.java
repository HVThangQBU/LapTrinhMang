package btTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class BaiTap2ClientUDP {

  public final static String SERVER_IP = "localhost";
  public final static int SERVER_PORT = 7; // Cổng mặc định của Echo Server
  public final static byte[] BUFFER = new byte[4096]; // Vùng đệm chứa dữ liệu cho gói tin nhận

  public static void main(String[] args) {
    DatagramSocket ds = null;
    try {
      ds = new DatagramSocket(); // Tạo DatagramSocket
      System.out.println("Client started ");

      InetAddress server = InetAddress.getByName(SERVER_IP);
      int num1 = new Random().nextInt(1000);
      int num2 = new Random().nextInt(100000);
      String theString = String.valueOf(num1);
      Charset code = StandardCharsets.US_ASCII;
      byte[] data = theString.getBytes(code); // Đổi chuỗi ra mảng bytes
      //System.out.println(data.length);
      // Tạo gói tin gởi
      DatagramPacket dp = new DatagramPacket(data, data.length, server, SERVER_PORT);
      ds.send(dp); // Send gói tin sang Echo Server
      // Gói tin nhận
      DatagramPacket incoming = new DatagramPacket(BUFFER, BUFFER.length);
      ds.receive(incoming); // Chờ nhận dữ liệu từ EchoServer gởi về

      // Đổi dữ liệu nhận được dạng mảng bytes ra chuỗi và in ra màn hình
      System.out.println("Received: " + new String(incoming.getData(), 0, incoming.getLength()));
    } catch (IOException e) {
      System.err.println(e);
    } finally {
      if (ds != null) {
        ds.close();
      }
    }
  }
}
