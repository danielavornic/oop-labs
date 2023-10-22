package tracking;

import java.io.File;
import java.util.Hashtable;

import file.SystemFile;
import utils.TimeUtils;

public class DirectoryWatcher {
  private static final String LAB_PATH = "/home/vornic/Uni/sem_3/oop/labs/lab2/";
  private static final String DIRECTORY_PATH = LAB_PATH + "files/";
  private static final String SNAPSHOT_FILE = LAB_PATH + ".snapshot.txt";

  private final SnapshotSystem snapshotSystem;

  public DirectoryWatcher() {
    this.snapshotSystem = new SnapshotSystem(DIRECTORY_PATH, SNAPSHOT_FILE);
  }

  public SnapshotSystem getSnapshotSystem() {
    return snapshotSystem;
  }

  public void commit() {
    snapshotSystem.setPreviousSnapshot(new Hashtable<>(snapshotSystem.getCurrentSnapshot()));
    snapshotSystem.setLastSnapshotTime(System.currentTimeMillis());
    snapshotSystem.saveSnapshot();
    System.out.println("Snapshot updated.");
  }

  public void status() {
    snapshotSystem.loadSnapshot();
    System.out.println("Last snapshot time: " + TimeUtils.formatTime(snapshotSystem.getLastSnapshotTime()));

    Hashtable<String, SystemFile> currentSnapshot = snapshotSystem.getCurrentSnapshot();
    Hashtable<String, SystemFile> previousSnapshot = snapshotSystem.getPreviousSnapshot();

    for (String fileName : currentSnapshot.keySet()) {
      if (!previousSnapshot.containsKey(fileName)) {
        System.out.println(fileName + " - New File");
      } else if (currentSnapshot.get(fileName).getLastModified() != previousSnapshot.get(fileName).getLastModified()) {
        System.out.println(fileName + " - Changed");
      } else {
        System.out.println(fileName + " - No Change");
      }
    }

    for (String fileName : previousSnapshot.keySet()) {
      if (!currentSnapshot.containsKey(fileName)) {
        System.out.println(fileName + " - Deleted");
      }
    }
  }

  public void info(String filename) {
    if (snapshotSystem.getCurrentSnapshot().containsKey(filename)) {
      snapshotSystem.getCurrentSnapshot().get(filename).printInfo();
    } else {
      System.out.println("File not found in snapshot. Fetching info...");
      File file = new File(DIRECTORY_PATH + filename);
      if (file.exists()) {
        SystemFile fileObj = SystemFile.createFileObject(DIRECTORY_PATH, filename, file.lastModified());
        fileObj.printInfo();
      } else {
        System.out.println("File not found.");
      }
    }
  }
}