import java.io.*;
import java.util.*;

import static java.lang.System.out;

public class ReadFileArray  {

  public static void main(String[] args) throws IOException {
    int tong = 0;
    List<Integer> array = readFile();
    out.println(array);
    for (Integer a : array) {
      tong += a;
    }
    out.println(tong);
    Collections.sort(array);
    writeFile(array, tong);
    out.println(array);

  }

  public static List<Integer> readFile() throws IOException {
    List<Integer> array = new ArrayList<>();
    try {
      File inputFile = new File("src/dayso.txt");
      BufferedReader reader = new BufferedReader(new FileReader(inputFile));
      String line="";
      while (true) {
        line = reader.readLine();
        if (line == null) {
          break;
        }
        StringTokenizer st = new StringTokenizer(line);
        while (st.hasMoreTokens()) {
          array.add(Integer.valueOf(st.nextToken()));
        }
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    return array;
  }
  public static void writeFile (List<Integer> array, int tong) {
    try {
      File myObj = new File("src/ketquadayso.txt");
      if (myObj.createNewFile()) {
        out.println("File created: " + myObj.getName());
      } else {
        out.println("File already exists.");
      }
      FileWriter myWriter = new FileWriter(myObj);
      myWriter.write("sap xep day so tang dan");
      myWriter.write('\n');
      myWriter.write(array.toString());
      myWriter.write('\n');
      myWriter.write("tong: ");
      myWriter.write(String.valueOf(tong));
      out.println(tong);
      myWriter.close();

    } catch (IOException e) {
      out.println("An error occurred.");
      e.printStackTrace();
    }
  }


}
