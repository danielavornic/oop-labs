package stacks;

import java.util.Vector;
import static stacks.StackConstants.*;

public class VectorStack<E> implements Stack<E> {
  private Vector<E> vector = new Vector<>();
  private int capacity;

  public VectorStack() {
    this(DEFAULT_CAPACITY);
  }

  public VectorStack(int capacity) {
    this.capacity = capacity;
  }

  public void push(E item) {
    if (!isFull()) {
      vector.add(item);
    } else {
      throw new IllegalStateException("Stack is full");
    }
  }

  public E pop() {
    if (isEmpty()) {
      throw new RuntimeException("Stack underflow");
    }
    return vector.remove(vector.size() - 1);
  }

  public E peek() {
    return vector.lastElement();
  }

  public boolean isEmpty() {
    return vector.isEmpty();
  }

  public boolean isFull() {
    return vector.size() == capacity;
  }

  public int size() {
    return vector.size();
  }
}
