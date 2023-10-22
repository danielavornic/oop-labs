package file;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImgFile extends SystemFile {
  private int width;
  private int height;

  public ImgFile(String directoryPath, String filename, String extension) {
    super(directoryPath, filename, extension);
    fetchImageDimensions();
  }

  private void fetchImageDimensions() {
    File file = new File(directoryPath + File.separator + filename + "." + extension);
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
    System.out.println("Image dimensions: " + width + "x" + height);
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public void setHeight(int height) {
    this.height = height;
  }
}
