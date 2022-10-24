import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataIODemo {
  public static void main (String[] args) throws IOException {
    DataOutputStream out = new DataOutputStream( new FileOutputStream("src/invoice1.txt"));
    double[] prices = {19.9, 9.99, 15.99 ,3.99, 4.99};
    int[] units = {12, 8, 13, 29, 50};
    String[] descs = {"JAVA T-shirst", "Java Mug", "Duke", "JAva Pin", "Key chain"};
    for (int i = 0; i < prices.length; i++) {
      out.writeDouble(prices[i]);
      out.writeChar('\t');
      out.writeInt(units[i]);
      out.writeChar('\t');
      out.writeChars(descs[i]);
      out.writeChar('\n');
    }
    out.close();
  }
}
