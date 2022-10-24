import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Copy {
  public static void main (String[] args) throws IOException {
    String path = "src/imgtest.jpg";
    File inputFile = new File(path);
    String pathOut = "src/outagain.txt";
    File outputFile = new File(pathOut);
    FileReader in = new FileReader(inputFile);
    FileWriter out = new FileWriter(outputFile);
    int c;
    while ((c = in.read()) != -1 ) {
      out.write(c);
    }
    System.out.println("Path: " + inputFile.getPath());
    in.close();
    out.close();
  }
}
