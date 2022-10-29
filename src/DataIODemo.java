import java.io.*;

public class DataIODemo {
  public static void main (String[] args) throws IOException {
    readInfo();
  }
  private static void writeInfo() throws IOException {
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
  private static void readInfo() throws IOException {
    String pathOut = "src/invoice1.txt";
    DataInputStream inputStream = new DataInputStream(new FileInputStream(pathOut));
    double price, total = 0.0;
    int unit;
    StringBuffer descs;
    String linesSepString = System.getProperty("line.separator");
    char lineSep = linesSepString.charAt(linesSepString.length() - 1 );
    try {
      while (true) {
        price = inputStream.readDouble();
        inputStream.readChar();
        unit = inputStream.readInt();
        inputStream.readChar();
        char chr;
        descs = new StringBuffer(20);
        while ((chr = inputStream.readChar()) != lineSep)
          descs.append(chr);
        System.out.println("You've ordered" + unit + "units of " + descs + "at $ "+ price);
        total = total + unit + price;

      }
    } catch (EOFException e) {

    }
    System.out.println("For a TOTAL of : $ " + total);
    inputStream.close();
  }
}
