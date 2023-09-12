package lab0.src;

public class Person {
  private String firstName;
  private String lastName;
  private int age;
  private boolean isMinor;

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setAge(int age) {
    if (age < 0) {
      System.out.println("Age cannot be negative");
      return;
    }

    this.age = age;

    if (age <= 17) {
      this.isMinor = true;
    } else {
      this.isMinor = false;
    }
  }

  public void printAge() {
    System.out.println(firstName + " " + lastName + " is " + age + " years old");
  }

  public void printIsMinor() {
    if (isMinor) {
      System.out.println(firstName + " " + lastName + " is a minor");
    } else {
      System.out.println(firstName + " " + lastName + " is not a minor");
    }
  }
}