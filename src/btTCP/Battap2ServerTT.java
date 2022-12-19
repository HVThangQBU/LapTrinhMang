package btTCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Battap2ServerTT {
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
          OutputStream os = socket.getOutputStream();
          InputStream is = socket.getInputStream();
          PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os)));
          DataInputStream inputStream = new DataInputStream(is);
          String line = "";
          while (true) {
            line = inputStream.readUTF();
            System.out.println("From " + socket.getInetAddress().getHostAddress() + ">" + line);
            DataOutputStream outputStream = new DataOutputStream(os);
            StringTokenizer st = new StringTokenizer(line);
            // list stnt
            List<Integer> array = new ArrayList<>();
            // list tinh tong
            List<Integer> arrayT = new ArrayList<>();
            String[] abc = line.split(" ");
//            for (int i = Integer.parseInt(abc[0]); i < Integer.parseInt(abc[1]); i++) {
              //tinh tong
              int tong = tinhTong(Integer.parseInt(line));
              System.out.println("tong+ " + tong);
//              if (tong == 15) {
//                arrayT.add(i);
//              }
              //tinh snt
//              if (isPrimeNumber(i)) {
//                System.out.println("snt" + i);
//                array.add(i);
//              }
//              //
//            }
            // xuat ket qua tinh snt
            outputStream.writeUTF("so: " + tong);
            // xuat ket qua tinh tong
            //outputStream.writeUTF("so: " + arrayT);
            if (line == null || line.equalsIgnoreCase("quit")) break;
            out.println("Response from Thang server:>> " + line);
          }
          socket.close();
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

  // check SNT
  public static boolean isPrimeNumber(int n) {
    // so nguyen n < 2 khong phai la so nguyen to
    if (n < 2) {
      return false;
    }
    // check so nguyen to khi n >= 2
    int squareRoot = (int) Math.sqrt(n);
    for (int i = 2; i <= squareRoot; i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static int tinhTong(int n) {
    int tong = 0, t, tmp = n;
    while (tmp > 0) {
      t = tmp % 10;
      tong += t;
      tmp /= 10;
    }
    return tong;
  }
}

