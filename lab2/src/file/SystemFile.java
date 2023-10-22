package file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class SystemFile {
  protected String directoryPath;
  protected String filename;
  protected String extension;
  protected long lastModifiedTime;
  protected long createdTime;

  public SystemFile(String directoryPath, String filename, String extension) {
    this.filename = filename;
    this.extension = extension;
    this.directoryPath = directoryPath;
    this.lastModifiedTime = fetchLastModifiedTime();
    this.createdTime = fetchCreatedTime();
  }

  public String getFilename() {
    return filename;
  }

  public String getExtension() {
    return extension;
  }

  public long getLastModifiedTime() {
    return lastModifiedTime;
  }

  public long getCreatedTime() {
    return createdTime;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public void setExtension(String extension) {
    this.extension = extension;
  }

  public void setLastModifiedTime(long lastModifiedTime) {
    this.lastModifiedTime = lastModifiedTime;
  }

  public void setCreatedTime(long createdTime) {
    this.createdTime = createdTime;
  }

  public long fetchLastModifiedTime() {
    File file = new File(directoryPath + File.separator + filename + "." + extension);
    if (file.exists()) {
      return file.lastModified();
    }
    return -1;
  }

  public long fetchCreatedTime() {
    Path filePath = Paths.get(directoryPath, filename + "." + extension);
    try {
      BasicFileAttributes attrs = Files.readAttributes(filePath, BasicFileAttributes.class);
      return attrs.creationTime().toMillis();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return -1;
  }

  public void printInfo() {
    System.out.println("File: " + filename + "." + extension);
    System.out.println("Created time: " + createdTime);
    System.out.println("Last modified time: " + lastModifiedTime);
  }
}
