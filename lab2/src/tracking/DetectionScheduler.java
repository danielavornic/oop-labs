package tracking;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Hashtable;

import file.SystemFile;

public class DetectionScheduler {
  private final SnapshotSystem snapshotSystem;
  private final FileChangeReporter changeReporter;
  private ScheduledExecutorService scheduler;

  public DetectionScheduler(SnapshotSystem snapshotSystem) {
    this.snapshotSystem = snapshotSystem;
    this.changeReporter = new FileChangeReporter();
    this.scheduler = Executors.newScheduledThreadPool(1);
  }

  public void start() {
    scheduler.scheduleAtFixedRate(() -> {
      detectChanges();
    }, 0, 5, TimeUnit.SECONDS);
  }

  public void detectChanges() {
    snapshotSystem.loadCurrentSnapshot();

    Hashtable<String, SystemFile> currentSnapshot = snapshotSystem.getCurrentSnapshot();
    Hashtable<String, SystemFile> lastKnownSnapshot = snapshotSystem.getLastKnownSnapshot();

    List<String> changes = changeReporter.reportChanges(currentSnapshot, lastKnownSnapshot);

    for (String change : changes) {
      System.out.println(change);
    }
    if (!changes.isEmpty()) {
      System.out.print("\n>");
    }

    snapshotSystem.setLastKnownSnapshot();
  }

  public void shutdown() {
    scheduler.shutdown();
    try {
      if (!scheduler.awaitTermination(800, TimeUnit.MILLISECONDS)) {
        scheduler.shutdownNow();
      }
    } catch (InterruptedException e) {
      scheduler.shutdownNow();
    }
  }
}
