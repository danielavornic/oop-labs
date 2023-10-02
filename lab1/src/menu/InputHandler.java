package lab1.src.menu;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputHandler {
  static Scanner scanner = new Scanner(System.in);

  public static int getInputInt(String prompt) {
    while (true) {
      try {
        System.out.print(prompt);
        String input = scanner.nextLine();
        return Integer.parseInt(input);
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid integer.");
      }
    }

  }

  public static String getStringInput(String prompt) {
    System.out.print(prompt);
    return scanner.nextLine();
  }

  public static LocalDate getDateInput(String prompt) {
    while (true) {
      try {
        System.out.print(prompt);
        String input = scanner.nextLine();
        return LocalDate.parse(input);
      } catch (DateTimeParseException e) {
        System.out.println("Invalid input. Please enter a valid date in the format yyyy-mm-dd.");
      }
    }
  }
}
