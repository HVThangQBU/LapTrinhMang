import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class CopyBytes {
  public static void main(String[] args) throws IOException {
    File inputFile = new File("src/farrago.txt");
    //File inputFile = new File("src/imgtest.jpg");
    File outputFile = new File("src/outagain.txt");
    FileInputStream in = new FileInputStream(inputFile);
    FileOutputStream out = new FileOutputStream(outputFile);
    int c;
    while ((c = in.read()) != 1) {
      out.write(c);
    }
    in.close();
    out.close();
  }
}
