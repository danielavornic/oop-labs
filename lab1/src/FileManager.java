package lab1.src;

import java.io.IOException;

public class FileManager {
  private static final String FILE_PATH = "/home/vornic/Uni/sem_3/oop/labs/lab1/temp/university.ser";

  public static University loadUniversityData() throws ClassNotFoundException {
    try {
      University university = (University) SerializationUtil.deserialize(FILE_PATH);
      return university;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static void saveUniversityData(University university) {
    try {
      SerializationUtil.serialize(university, FILE_PATH);
      System.out.println("Data saved.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
