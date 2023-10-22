package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TextFile extends SystemFile {
  private int lineCount;
  private int wordCount;
  private int charCount;

  public TextFile(String directoryPath, String filename) {
    super(directoryPath, filename);
    computeTextFileAttributes();
  }

  private void computeTextFileAttributes() {
    File file = new File(directoryPath + File.separator + filename);
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = reader.readLine()) != null) {
        lineCount++;
        charCount += line.length();

        String[] words = line.split("\\s+");
        wordCount += words.length;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void printInfo() {
    super.printInfo();
    System.out.println("Line Count: " + lineCount);
    System.out.println("Word Count: " + wordCount);
    System.out.println("Character Count: " + charCount);
  }
}
