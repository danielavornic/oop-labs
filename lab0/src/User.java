// Challenge 1

package lab0.src;

public class User {
  private String firstName;
  private String lastName;

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
}