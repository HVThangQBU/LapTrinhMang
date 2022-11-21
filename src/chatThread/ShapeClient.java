package chatThread;

import java.io.*;
import java.net.Socket;

public class ShapeClient {


  public final static String SERVER_IP = "192.168.61.106";
  public final static int SERVER_POST = 999;
  public static void main(String[] args) throws IOException, InterruptedException {
    Socket socket = null;
    String chat = null;
    String chatoServer = null;
    try {
      socket = new Socket(SERVER_IP, SERVER_POST);
      System.out.println("Connected: " + socket);
      InputStream is = socket.getInputStream();
      OutputStream os = socket.getOutputStream();
      while (true) {
        System.out.print("Input from client: ");
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        //Lấy chuỗi ký tự nhập từ bàn phím
        chat = inFromUser.readLine();
        //Tạo OutputStream nối với Socket
        DataOutputStream outToServer = new DataOutputStream(os);
        DataInputStream intoServer = new DataInputStream(is);
        outToServer.writeBytes( chat + '\n');

        //   BufferedReader inFromServer = new BufferedReader(new InputStreamReader(is));
        chatoServer = intoServer.readUTF();
        System.out.println("asdaa"+ chatoServer);


      }
    } catch (IOException ie) {
      System.out.println("khong ket noi duoc");
    }
    finally {
      if (socket != null) {
        socket.close();
      }
    }
  }

}
