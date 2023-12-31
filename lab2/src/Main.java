import java.util.Scanner;

import tracking.*;

public class Main {
  public static void main(String[] args) {
    final DirectoryWatcher directoryWatcher = new DirectoryWatcher();
    final DetectionScheduler detectionScheduler = new DetectionScheduler(directoryWatcher.getSnapshotSystem());

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
