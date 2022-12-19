package btTCP;

import java.io.*;
import java.net.Socket;

public class Baitap2Client {
  public final static String SERVER_IP = "localhost";
  public final static int SERVER_PORT = 6868;

  public static void main(String[] args) throws IOException, InterruptedException {
    Socket socket = null;
    String a = null;
    String b = null;
    String chatToServer = null;
    String ketqua = null;
    try {
      socket = new Socket(SERVER_IP, SERVER_PORT);
      System.out.println("Connected: " + socket);
      InputStream is = socket.getInputStream();
      OutputStream os = socket.getOutputStream();
      while (true) {
        System.out.println("input from client a: ");
        //BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DataInputStream inputStream = new DataInputStream(System.in);
        a = inputStream.readLine();
        System.out.println("input from client b: ");
        b = inputStream.readLine();
        DataOutputStream out = new DataOutputStream(os);
        DataInputStream into = new DataInputStream(is);
        out.writeUTF(a + " " + b);
        chatToServer = into.readUTF();
        System.out.println("server tra ve a : " + chatToServer);
        while ((ketqua = into.readUTF()) != null) {
          System.out.println("server tra ve ket qua: " + ketqua);
        }
      }
    } catch (IOException ie) {
      System.out.println("khong ket noi duoc");
    } finally {
      if (socket != null) {
        socket.close();
      }
    }
  }
}
