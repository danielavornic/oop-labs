package file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextFile extends SystemFile {
  private int lineCount;
  private int wordCount;
  private int charCount;

  public TextFile(String directoryPath, String filename, String extension) {
    super(directoryPath, filename, extension);
    computeTextFileAttributes();
  }

  private void computeTextFileAttributes() {
    try (BufferedReader reader = new BufferedReader(new FileReader(getFilename() + "." + getExtension()))) {
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

  public int getLineCount() {
    return lineCount;
  }

  public int getWordCount() {
    return wordCount;
  }

  public int getCharCount() {
    return charCount;
  }

  public void setLineCount(int lineCount) {
    this.lineCount = lineCount;
  }

  public void setWordCount(int wordCount) {
    this.wordCount = wordCount;
  }

  public void setCharCount(int charCount) {
    this.charCount = charCount;
  }
}
