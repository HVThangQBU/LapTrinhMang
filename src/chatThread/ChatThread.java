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
    //  BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os)));

      DataInputStream inputStream = new DataInputStream(is);


      String line = "";
      while (true) {
    //    line = br.readLine(); // Receive data from client

        line = inputStream.readUTF();
        System.out.println("From " + socket.getInetAddress().getHostAddress() + ">" + line);
        StringTokenizer st = new StringTokenizer(line);
        List<Integer> array = new ArrayList<>();
        DataOutputStream outToClient = new DataOutputStream(os);
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
        outToClient.writeUTF(String.valueOf(tong)+ " " + String.valueOf(array));
        if (line == null || line.equalsIgnoreCase("quit")) break;
        out.println("Response from K61 server:>>" + line);
      }
      socket.close();
    } catch (IOException e) {
      System.out.println("Connection lost from: " + socket.getInetAddress().getHostAddress());
    }
  }
}
