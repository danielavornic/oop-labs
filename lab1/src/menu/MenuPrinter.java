package lab1.src.menu;

public class MenuPrinter {
  public static void displayMenuOptions(String[] menuOptions, String title) {
    System.out.print("\033[H\033[2J");

    int maxOptionLength = 0;
    for (String option : menuOptions) {
      if (option.length() > maxOptionLength) {
        maxOptionLength = option.length();
      }
    }

    String border = "+" + "-".repeat(maxOptionLength + 2) + "+";

    System.out.println(border);
    boolean isTitleLengthEven = title.length() % 2 == 0;
    int leftPadding = (border.length() - title.length() - 2) / 2;
    int rightPadding = leftPadding;
    if (!isTitleLengthEven) {
      rightPadding++;
    }

    System.out.println("|" + " ".repeat(leftPadding) + title + " ".repeat(rightPadding) + "|");
    System.out.println(border);

    for (String option : menuOptions) {
      System.out.println("| " + option + " ".repeat(maxOptionLength - option.length()) + " |");
    }

    System.out.println(border);
    System.out.println();
  }
}
