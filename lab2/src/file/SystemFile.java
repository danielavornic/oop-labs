package file;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;

import utils.*;

public class SystemFile {
  protected String directoryPath;
  protected String filename;
  protected String extension;
  protected long lastModifiedTime;
  protected long createdTime;

  public SystemFile(String directoryPath, String filename, long lastModifiedTime) {
    this.filename = filename;
    this.extension = filename.substring(filename.lastIndexOf('.') + 1);
    this.directoryPath = directoryPath;
    this.lastModifiedTime = lastModifiedTime;
    if (this.lastModifiedTime != 0)
      setFileTimes();
  }

  public long getLastModified() {
    return lastModifiedTime;
  }

  public void setFileTimes() {
    Path filePath = Paths.get(directoryPath, filename);

    if (!Files.exists(filePath)) {
      System.err.println("File does not exist: " + filePath);
      return;
    }

    try {
      BasicFileAttributes attrs = Files.readAttributes(filePath, BasicFileAttributes.class);
      // lastAccessTime as createdTime
      this.createdTime = attrs.lastAccessTime().toMillis();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void printInfo() {
    String fileName = filename.substring(0, filename.lastIndexOf('.'));
    System.out.println("File Name: " + fileName);
    System.out.println("Extension: " + extension);
    System.out.println("Created time: " + TimeUtils.formatTime(this.createdTime));
    System.out.println("Last modified time: " + TimeUtils.formatTime(this.lastModifiedTime));
  }

  public static SystemFile createFileObject(String path, String fileName, long lastModifiedTime) {
    String extension = getFileExtension(fileName);
    switch (extension) {
      case "txt":
        return new TextFile(path, fileName, lastModifiedTime);
      case "png":
      case "jpg":
        return new ImgFile(path, fileName, lastModifiedTime);
      case "py":
      case "java":
        return new ProgramFile(path, fileName, lastModifiedTime);
      default:
        return new SystemFile(path, fileName, lastModifiedTime);
    }
  }

  public static String getFileExtension(String fileName) {
    int lastIndex = fileName.lastIndexOf(".");
    if (lastIndex > 0 && lastIndex < fileName.length() - 1) {
      return fileName.substring(lastIndex + 1).toLowerCase();
    }
    return "";
  }
}
