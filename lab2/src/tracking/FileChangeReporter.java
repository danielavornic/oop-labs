package tracking;

import file.SystemFile;
import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;

public class FileChangeReporter {

  private boolean isFirstRun = true;

  public List<String> reportChanges(Hashtable<String, SystemFile> currentSnapshot,
      Hashtable<String, SystemFile> referenceSnapshot) {
    List<String> changes = new ArrayList<>();

    if (isFirstRun) {
      isFirstRun = false;
      return changes;
    }

    for (String fileName : currentSnapshot.keySet()) {
      if (!referenceSnapshot.containsKey(fileName)) {
        changes.add(fileName + " - New File");
      } else if (currentSnapshot.get(fileName).getLastModified() != referenceSnapshot.get(fileName).getLastModified()) {
        changes.add(fileName + " - Changed");
      }
    }

    for (String fileName : referenceSnapshot.keySet()) {
      if (!currentSnapshot.containsKey(fileName)) {
        changes.add(fileName + " - Deleted");
      }
    }

    return changes;
  }
}
