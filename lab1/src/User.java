// Challenge 1

package lab1.src;

public class User {
  String firstName;
  String lastName;

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void abbreviateName() {
    if (firstName == null || lastName == null) {
      System.out.println("Please set the first name and last name");
      return;
    }

    System.out.println(firstName.charAt(0) + ". " + lastName.charAt(0) + ".");
  }

  // public static void main(String[] args) {
  //   User user1 = new User();

  //   user1.setFirstName("George");
  //   user1.setLastName("Bush");

  //   user1.abbreviateName();
  // }
}