package lab0.src;

public class MainClass {
  public static void main(String[] args) {
    // Lab Task
    Person person1 = new Person();
    person1.setFirstName("Daniela");
    person1.setLastName("Vornic");
    person1.setAge(19);
    person1.printAge();
    person1.printIsMinor();

    // Challenge 1
    User user1 = new User();
    user1.setFirstName("George");
    user1.setLastName("Bush");
    user1.abbreviateName();
  }
}
