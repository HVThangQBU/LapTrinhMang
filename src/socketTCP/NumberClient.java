package socketTCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NumberClient {
  public final static String SERVER_IP = "192.168.100.106";
  public final static int SERVER_POST = 9;
  public static void main(String[] args) throws IOException, InterruptedException {
    Socket socket = null;
    try {
      socket = new Socket(SERVER_IP, SERVER_POST);
      System.out.println("Connected: " + socket);
      InputStream is = socket.getInputStream();
      OutputStream os = socket.getOutputStream();
      for (int i = 0; i <=9; i++) {
        System.out.println(i);
        os.write(i);
        int ch = is.read();
        System.out.println((char) ch + "");
          Thread.sleep(200);
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
