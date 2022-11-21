package chatThread;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class Test {
  public static void main(String[] args) {
    int a, b;
//    System.out.println("nhap chieu rong");
//    Scanner scanner = new Scanner(System.in);
//    a = scanner.nextInt();
//    System.out.println("nhap do chieu dai");
//    b = scanner.nextInt();
    Test t = new Test();
//    t.vuong(a);
//    t.hcn(a,b);
    t.tron(7);
  //    t.DrawMeACircle(5);

  }

  public void vuong(int a) {
    int i, j;
    for (i = 0; i < a; i++) {
      for (j = 0; j < a; j++) {
        if (i == 0 || i == a - 1 || j == 0 || j == a - 1) {
          System.out.print("* ");
        } else {
          System.out.print("  ");
        }
      }
      System.out.print("\n");
    }
  }
  public void hcn(int a, int b) {
    int i,j;
    for (i = 0; i < a; i++) {
      for (j = 0; j < b; j++) {
        if (i == 0 || i == a - 1 || j == 0 || j == b - 1) {
          System.out.print("* ");
        } else {
          System.out.print("  ");
        }
      }
      System.out.print("\n");
    }
  }
  public void DrawMeACircle( int radius) {
    int posX = 5;
    int posY = 15;
    for (int i = 0;i <= posX + radius; i++) {
      for (int j = 1;j <=posY + radius; j++) {
        int xSquared = (i - posX)*(i - posX);
        int ySquared = (j - posY)*(j - posY);
        if (Math.abs(xSquared + ySquared - radius * radius) < radius) {
          System.out.print("#");
        } else {
          System.out.print(" ");
        }
      }
      System.out.println();
    }
  }
  private static int Path(int x, int y) {
    return (int) (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
  }

  public void tron(int a)   {
    int width = a;
    int length = (int) (width * 1.5);
    int y = width;
    while (y >= -width) {
      int x = -length;
      while (x <= length) {
        if (Path(x, y) == a) {
          System.out.println("*\t");
        } else {
          System.out.println(" \t");
        }
        x += 1;
      }
      System.out.println("\n");
      y -= 2;

    }

  }
}


