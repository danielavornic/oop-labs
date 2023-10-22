import java.io.*;
import java.nio.file.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.List;

import file.*;

public class DirectoryWatcher {
  private static final String LAB_PATH = "/home/vornic/Uni/sem_3/oop/labs/lab2/";
  private static final String DIRECTORY_PATH = LAB_PATH + "files/";
  private static final String SNAPSHOT_FILE = LAB_PATH + ".snapshot.txt";

  private final Hashtable<String, SystemFile> currentSnapshot;
  private Hashtable<String, SystemFile> previousSnapshot;
  private long lastSnapshotTime;

  public DirectoryWatcher() {
    this.currentSnapshot = new Hashtable<>();
    this.previousSnapshot = new Hashtable<>();
  }

  private SystemFile createFileObject(String path, String fileName, long lastModifiedTime) {
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

  private String getFileExtension(String fileName) {
    int lastIndex = fileName.lastIndexOf(".");
    if (lastIndex > 0 && lastIndex < fileName.length() - 1) {
      return fileName.substring(lastIndex + 1).toLowerCase();
    }
    return "";
  }

  private void saveSnapshot() {
    try (PrintWriter writer = new PrintWriter(SNAPSHOT_FILE)) {
      writer.println(lastSnapshotTime);
      for (String fileName : previousSnapshot.keySet()) {
        File file = new File(DIRECTORY_PATH + fileName);
        writer.println(fileName + "," + file.lastModified());
      }
      System.out.println("Snapshot saved to .snapshot.txt.");
    } catch (IOException e) {
      System.out.println("Failed to save snapshot: " + e.getMessage());
    }
  }

  private void loadSnapshot() {
    try {
      List<String> lines = Files.readAllLines(Paths.get(SNAPSHOT_FILE));
      lastSnapshotTime = Long.parseLong(lines.get(0));

      previousSnapshot.clear();
      for (int i = 1; i < lines.size(); i++) {
        String[] parts = lines.get(i).split(",");
        String fileName = parts[0];
        File file = new File(DIRECTORY_PATH + fileName);
        long lastModified = file.exists() ? Long.parseLong(parts[1]) : 0;
        SystemFile fileObj = createFileObject(DIRECTORY_PATH, fileName, lastModified);
        previousSnapshot.put(fileName, fileObj);
      }

      currentSnapshot.clear();
      File directory = new File(DIRECTORY_PATH);
      if (directory.exists() && directory.isDirectory()) {
        File[] files = directory.listFiles();
        if (files != null) {
          for (File file : files) {
            String fileName = file.getName();
            SystemFile fileObj = createFileObject(DIRECTORY_PATH, fileName, file.lastModified());
            currentSnapshot.put(fileName, fileObj);
          }
        }
      }
    } catch (IOException e) {
      System.out.println("Failed to load snapshot or snapshot doesn't exist");
    }
  }

  public void commit() {
    previousSnapshot = new Hashtable<>(currentSnapshot);
    lastSnapshotTime = System.currentTimeMillis();
    saveSnapshot();
    System.out.println("Snapshot updated.");
  }

  public void status() {
    loadSnapshot();

    System.out.println("Last snapshot time: " + printTime(lastSnapshotTime));

    for (String fileName : currentSnapshot.keySet()) {
      if (!previousSnapshot.containsKey(fileName)) {
        System.out.println(fileName + " - New File");
      } else if (currentSnapshot.get(fileName).getLastModified() != previousSnapshot.get(fileName).getLastModified()) {
        System.out.println(fileName + " - Changed");
      } else if (currentSnapshot.get(fileName).getLastModified() == previousSnapshot.get(fileName).getLastModified()) {
        System.out.println(fileName + " - No Change");
      }
    }

    for (String fileName : previousSnapshot.keySet()) {
      if (!currentSnapshot.containsKey(fileName)) {
        System.out.println(fileName + " - Deleted");
      }
    }

  }

  public String printTime(long time) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return sdf.format(new Date(time));
  }

  public void info(String filename) {
    if (currentSnapshot.containsKey(filename)) {
      currentSnapshot.get(filename).printInfo();
    } else {
      System.out.println("File not found in snapshot. Fetching info...");
      File file = new File(DIRECTORY_PATH + filename);
      if (file.exists()) {

        SystemFile fileObj = createFileObject(DIRECTORY_PATH, filename, file.lastModified());
        fileObj.printInfo();
      } else {
        System.out.println("File not found.");
      }
    }
  }
}
