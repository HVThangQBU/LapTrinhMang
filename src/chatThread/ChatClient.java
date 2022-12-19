package chatThread;

import java.io.*;
import java.net.Socket;

public class ChatClient {

    public final static String SERVER_IP = "localhost";
    public final static int SERVER_POST = 100;
    public static void main(String[] args) throws IOException, InterruptedException {
      Socket socket = null;
      String chat = "";
      String chatoServer = "";
      try {
        socket = new Socket(SERVER_IP, SERVER_POST);
        System.out.println("Connected: " + socket);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        while (true) {
          System.out.print("Nhap vao day so: ");
          BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
          //Lấy chuỗi ký tự nhập từ bàn phím
          chat = inFromUser.readLine();
          //Tạo OutputStream nối với Socket
          DataOutputStream outToServer = new DataOutputStream(os);
          DataInputStream intoServer = new DataInputStream(is);
          outToServer.writeBytes( chat + '\n');
          chatoServer = intoServer.readUTF();
          System.out.println("Day so sap xep tang dan: "+ chatoServer);
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
