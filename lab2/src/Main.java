import java.util.Scanner;

import tracking.DetectionScheduler;
import tracking.DirectoryWatcher;

public class Main {
  private static DirectoryWatcher directoryWatcher = new DirectoryWatcher();
  private static DetectionScheduler detectionScheduler = new DetectionScheduler(directoryWatcher.getSnapshotSystem());

  public static void main(String[] args) {
    detectionScheduler.start();

    System.out.print("\033[H\033[2J");

    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.print(">");

      String command = scanner.nextLine();
      if ("q".equalsIgnoreCase(command)) {
        detectionScheduler.shutdown();
        scanner.close();
        break;
      }

      switch (command) {
        case "c":
          directoryWatcher.commit();
          break;

        case "s":
          directoryWatcher.status();
          break;

        default:
          if (command.startsWith("i")) {
            String[] parts = command.split(" ");
            if (parts.length > 1) {
              String filename = parts[1];
              directoryWatcher.info(filename);
            } else {
              System.out.println("Filename required for info command.");
            }
          } else {
            System.out.println("Invalid command. Only c, s, i and q are allowed.");
          }
      }
    }
  }
}
