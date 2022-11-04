package socketTCP;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class ChatServer {
  public final static int SERVER_PORT = 66;////192.168.100.106

  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = null;

    try {
      System.out.println("Binding to port " + SERVER_PORT + ", please wait  ...");
      serverSocket = new ServerSocket(SERVER_PORT);
      System.out.println("Server started: " + serverSocket);
      System.out.println("Waiting for a client ...");
        try {
          Socket socket = serverSocket.accept();
          System.out.println("Client accepted: " + socket);
          OutputStream os = socket.getOutputStream();
          InputStream is = socket.getInputStream();
          BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
          PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os)));
          String line = "";
          while (true) {
            line = br.readLine(); // Receive data from client
            System.out.println("From " + socket.getInetAddress().getHostAddress() + ">" + line);
            StringTokenizer st = new StringTokenizer(line);
            List<Integer> array = new ArrayList<>();
            int tong = 0;
            while (st.hasMoreTokens()) {
              array.add(Integer.valueOf(st.nextToken()));
            }
            for (Integer a : array) {
              tong += a;
            }
            System.out.println("tong: "+ tong);
            Collections.sort(array);
            System.out.println(array);

            if (line == null || line.equalsIgnoreCase("quit")) break;
            out.println("Response from K61 server:>>" + line);
          }
          socket.close();
        } catch (IOException e) {
          System.err.println(" Connection Error: " + e);
        }
    } catch (IOException e1) {
      e1.printStackTrace();
    } finally {
      if (serverSocket != null) serverSocket.close();
    }
  }
}
