package file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class SystemFile {
  protected String directoryPath;
  protected String filename;
  protected String extension;
  protected long lastModifiedTime;
  protected long createdTime;

  public SystemFile(String directoryPath, String filename) {
    this.filename = filename;
    this.extension = filename.substring(filename.lastIndexOf('.') + 1);
    this.directoryPath = directoryPath;
    setFileTimes();
  }

  public void setFileTimes() {
    Path filePath = Paths.get(directoryPath, filename);
    try {
      BasicFileAttributes attrs = Files.readAttributes(filePath, BasicFileAttributes.class);
      this.lastModifiedTime = attrs.lastModifiedTime().toMillis();
      // TODO: Ask about created time vs last access time
      this.createdTime = attrs.lastAccessTime().toMillis();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String printTime(long time) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return sdf.format(new Date(time));
  }

  public void printInfo() {
    String fileName = filename.substring(0, filename.lastIndexOf('.'));
    System.out.println("File Name: " + fileName);
    System.out.println("Extension: " + extension);
    System.out.println("Created time: " + printTime(this.createdTime));
    System.out.println("Last modified time: " + printTime(this.lastModifiedTime));
  }
}
