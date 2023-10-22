package file;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImgFile extends SystemFile {
  private int width;
  private int height;

  public ImgFile(String directoryPath, String filename) {
    super(directoryPath, filename);
    fetchImageDimensions();
  }

  private void fetchImageDimensions() {
    File file = new File(directoryPath + File.separator + filename);
    try {
      BufferedImage img = ImageIO.read(file);
      width = img.getWidth();
      height = img.getHeight();
    } catch (IOException e) {
      e.printStackTrace();
      width = -1;
      height = -1;
    }
  }

  @Override
  public void printInfo() {
    super.printInfo();
    System.out.println("Image size: " + width + "x" + height);
  }
}
