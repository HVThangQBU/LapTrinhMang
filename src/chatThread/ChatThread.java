package chatThread;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class ChatThread extends Thread{

  Socket socket;

  public ChatThread(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    try {
      OutputStream os = socket.getOutputStream();
      InputStream is = socket.getInputStream();
      BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os)));
      DataInputStream inputStream = new DataInputStream(is);
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
    } catch (IOException e) {
      System.out.println("Connection lost from: " + socket.getInetAddress().getHostAddress());
    }
  }
}
