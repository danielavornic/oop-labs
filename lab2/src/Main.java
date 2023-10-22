import java.util.Scanner;

public class Main {
  private static DirectoryWatcher directoryWatcher = new DirectoryWatcher();

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("\033[H\033[2J");

    while (true) {
      System.out.print("> ");
      String command = scanner.nextLine().trim();

      switch (command) {
        case "c":
          directoryWatcher.commit();
          break;

        case "s":
          directoryWatcher.status();
          break;

        case "q":
          System.out.println("Exiting...");
          scanner.close();
          return;

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
            System.out.println("Invalid command.");
          }
      }
    }
  }
}
