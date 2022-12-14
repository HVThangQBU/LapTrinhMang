package drawShape;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DrawThread extends Thread {
  Socket socket;

  public DrawThread(Socket socket) {
    this.socket = socket;
  }

  private static int Path(int x, int y) {
    return (int) (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
  }

  public static void drawCircle(StringBuilder Client, int r_num) {
    int width = r_num;
    int length = (int) (width * 1.5);
    int y = width;
    while (y >= -width) {
      int x = -length;
      while (x <= length) {
        if (Path(x, y) == r_num) {
          Client.append("*");
        } else {
          Client.append(" ");
        }
        x += 1;
      }
      Client.append("\n");
      y -= 2;
    }
    Client.append("\n");
    Client.append("q");
    Client.append("\n");
  }

  public void drawRectangle(StringBuilder Client, int width, int height) {
    for (int j = 1; j <= width; j++) {
      Client.append("* ");
    }
    Client.append("\n");
    for (int i = 1; i <= height - 2; i++) {
      Client.append("* ");
      for (int j = 1; j <= width - 2; j++) {
        Client.append("  ");
      }
      Client.append("*\n");
    }
    for (int j = 1; j <= width; j++) {
      Client.append("* ");
    }
    Client.append("\n");
    Client.append("q");
    Client.append("\n");
  }

  public void drawSquare(StringBuilder Client, int width) {
    for (int j = 1; j <= width; j++) {
      Client.append("* ");
    }
    Client.append("\n");
    for (int i = 1; i <= width - 2; i++) {
      Client.append("* ");
      for (int j = 1; j <= width - 2; j++) {
        Client.append("  ");
      }
      Client.append("*\n");
    }
    for (int j = 1; j <= width; j++) {
      Client.append("* ");
    }
    Client.append("\n");
    Client.append("q");
    Client.append("\n");
  }

  @Override
  public void run() {
    try {
      while (true) {
        DataInputStream is = new DataInputStream(socket.getInputStream());
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());
        String message = is.readUTF();
        System.out.println("Client: " + message);

        StringTokenizer stringTokenizer = new StringTokenizer(message, " ");
        ArrayList<String> arrayList = new ArrayList<>();
        while (stringTokenizer.hasMoreTokens()) {
          String token = stringTokenizer.nextToken();
          arrayList.add(token);
        }

        StringBuilder Client = new StringBuilder();
        if (arrayList.get(0).equalsIgnoreCase("CHUNHAT")) {
          drawRectangle(Client, Integer.parseInt(arrayList.get(1)), Integer.parseInt(arrayList.get(2)));
          os.flush();
        }

        if (arrayList.get(0).equalsIgnoreCase("VUONG")) {
          drawSquare(Client, Integer.parseInt(arrayList.get(1)));
          os.flush();
        }

        if (arrayList.get(0).equalsIgnoreCase("TRON")) {
          drawCircle(Client, Integer.parseInt(arrayList.get(1)));
          os.flush();
        }

        System.out.println(Client);
        os.writeUTF(String.valueOf(Client));
      }

    } catch (IOException e) {
      System.out.println("Connection lost from: " + socket.getInetAddress().getHostAddress());
    }
  }
}
