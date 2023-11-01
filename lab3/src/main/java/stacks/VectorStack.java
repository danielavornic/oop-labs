package stacks;

import java.util.Vector;

public class VectorStack<E> implements Stack<E> {
  private Vector<E> vector = new Vector<>();

  public void push(E item) {
    vector.add(item);
  }

  public E pop() {
    return vector.remove(vector.size() - 1);
  }

  public E peek() {
    return vector.lastElement();
  }

  public boolean isEmpty() {
    return vector.isEmpty();
  }

  public int size() {
    return vector.size();
  }
}
