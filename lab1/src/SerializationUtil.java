package lab1.src;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationUtil {
  public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
    try (FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis)) {
      Object obj = ois.readObject();
      return obj;
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("File not found");
    } catch (IOException e) {
      throw new IOException("IO Exception");
    } catch (ClassNotFoundException e) {
      throw new ClassNotFoundException("Class not found");
    }
  }

  public static void serialize(Object obj, String fileName) throws IOException {
    FileOutputStream fos = new FileOutputStream(fileName);
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    oos.writeObject(obj);
    fos.close();
  }
}
