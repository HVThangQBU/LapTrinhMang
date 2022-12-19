package chatThread;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class ChatServerTT {

  public final static int SERVER_PORT = 100;////192.168.100.106  10.10.12.236

  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = null;
    try {
      System.out.println("Binding to port " + SERVER_PORT + ", please wait  ...");
      serverSocket = new ServerSocket(SERVER_PORT);
      System.out.println("Server started: " + serverSocket);
      System.out.println("Waiting for a client ...");
      while (true) {
        try {
          Socket socket = serverSocket.accept();
          System.out.println("Client accepted: " + socket);
          OutputStream os = socket.getOutputStream();
          InputStream is = socket.getInputStream();
          BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
          PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os)));
          String line = "";
          while (true) {
            line = br.readLine(); // Receive data from client
            System.out.println("From " + socket.getInetAddress().getHostAddress() + ">" + line);
            StringTokenizer st = new StringTokenizer(line);
            List<Integer> array = new ArrayList<>();
            DataOutputStream outToClient = new DataOutputStream(os);
            while (st.hasMoreTokens()) {
              array.add(Integer.valueOf(st.nextToken()));
            }
            // tinh tong array
            int tong = 0;
            for (Integer a : array) {
              tong += a;
            }
            // sap xep array
            Collections.sort(array);
            System.out.println(array);
            // kiem max
            String  max = String.valueOf(Collections.max(array));
            System.out.println(max);
            ///
            outToClient.writeUTF( String.valueOf(array) + " MAX: " +max);
            if (line == null || line.equalsIgnoreCase("quit")) break;
            out.println("Response from K61 server:>>" + line);
          }
          socket.close();
        } catch (IOException e) {System.err.println(" Connection Error: " + e);}
      }
    } catch (IOException e1) {
      e1.printStackTrace();
    } finally {
      if (serverSocket != null) serverSocket.close();
    }
  }
}
