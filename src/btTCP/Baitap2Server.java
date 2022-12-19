package btTCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Baitap2Server {
  public final static int SERVER_PORT = 6868;

  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = null;
    try {
      System.out.println("Binding to port " + SERVER_PORT + ", please wait ....");
      serverSocket = new ServerSocket(SERVER_PORT);
      System.out.println("Server started: " + serverSocket);
      System.out.println("Waiting for a client ...");
      while (true) {
        try {
          Socket socket = serverSocket.accept();
          System.out.println("Client accepted : " + socket);
          Baitap2Thread baitap2Thread = new Baitap2Thread(socket);
          baitap2Thread.start();
        } catch (IOException e) {
          System.out.println("Connection Error: " + e);
        }
      }
    } catch (IOException e1) {
      e1.printStackTrace();
    } finally {
      if (serverSocket != null)
        serverSocket.close();
    }
  }
}
