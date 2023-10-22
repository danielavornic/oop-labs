import java.io.File;
import java.util.Hashtable;

import file.ImgFile;
import file.ProgramFile;
import file.SystemFile;
import file.TextFile;

public class DirectoryWatcher {
  private static final String DIRECTORY_PATH = "/home/vornic/Uni/sem_3/oop/labs/lab2/workspace/";
  private final Hashtable<String, SystemFile> filesSnapshot;

  public DirectoryWatcher() {
    this.filesSnapshot = new Hashtable<>();
    this.initializeSnapshot();
  }

  private void initializeSnapshot() {
    File directory = new File(DIRECTORY_PATH);
    if (directory.exists() && directory.isDirectory()) {
      File[] files = directory.listFiles();
      if (files != null) {
        for (File file : files) {
          String fileName = file.getName();
          SystemFile fileObj = createFileObject(DIRECTORY_PATH, fileName);
          filesSnapshot.put(fileName, fileObj);
        }
      }
    }
  }

  private SystemFile createFileObject(String path, String fileName) {
    String extension = getFileExtension(fileName);
    switch (extension) {
      case "txt":
        return new TextFile(path, fileName);
      case "png":
      case "jpg":
        return new ImgFile(path, fileName);
      case "py":
      case "java":
        return new ProgramFile(path, fileName);
      default:
        return new SystemFile(path, fileName);
    }
  }

  private String getFileExtension(String fileName) {
    int lastIndex = fileName.lastIndexOf(".");
    if (lastIndex > 0 && lastIndex < fileName.length() - 1) {
      return fileName.substring(lastIndex + 1).toLowerCase();
    }
    return "";
  }

  public void commit() {
    filesSnapshot.clear();
    initializeSnapshot();
    System.out.println("Snapshot updated.");
  }

  public void info(String filename) {
    if (filesSnapshot.containsKey(filename)) {
      filesSnapshot.get(filename).printInfo();
    } else {
      System.out.println("File not found in snapshot. Fetching info...");
      File file = new File(DIRECTORY_PATH + filename);
      if (file.exists()) {
        SystemFile fileObj = createFileObject(DIRECTORY_PATH, filename);
        fileObj.printInfo();
      } else {
        System.out.println("File not found.");
      }
    }
  }
}
