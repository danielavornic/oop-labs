package lab1.src;

public class Person {
  String firstName = "Daniela";
  String lastName = "Vornic";
  int age = 19;
  boolean isMinor = false;

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setAge(int age) {
    this.age = age;
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