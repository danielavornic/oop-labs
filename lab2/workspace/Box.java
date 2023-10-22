package lab2.workspace;

public class Box {
  private double width;
  private double height;
  private double depth;

  public Box(double width, double height, double depth) {
    this.width = width;
    this.height = height;
    this.depth = depth;
  }

  public Box(double side) {
    this(side, side, side);
  }

  public Box() {
    this(1);
  }

  public double getWidth() {
    return width;
  }

  public double getHeight() {
    return height;
  }

  public void printSize() {
    System.out.println("Width: " + width + ", Height: " + height + ", Depth: " + depth);
  }
}