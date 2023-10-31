package stacks;

public class ArrayStack<E> implements Stack<E> {
  private Object[] array;
  private int top;
  private static final int DEFAULT_CAPACITY = 10;

  public ArrayStack() {
    array = new Object[DEFAULT_CAPACITY];
    top = -1;
  }

  public void push(E item) {
    if (top == array.length - 1) {
      resize(2 * array.length);
    }
    array[++top] = (Object) item;
  }

  @SuppressWarnings("unchecked")
  public E pop() {
    if (isEmpty()) {
      throw new RuntimeException("Stack underflow");
    }
    E item = (E) array[top];
    array[top--] = null;
    if (top > 0 && top == array.length / 4) {
      resize(array.length / 2);
    }
    return item;
  }

  @SuppressWarnings("unchecked")
  public E peek() {
    if (isEmpty()) {
      throw new RuntimeException("Stack is empty");
    }
    return (E) array[top];
  }

  public boolean isEmpty() {
    return top == -1;
  }

  public int size() {
    return top + 1;
  }

  private void resize(int capacity) {
    Object[] temp = new Object[capacity];
    System.arraycopy(array, 0, temp, 0, top + 1);
    array = temp;
  }
}
