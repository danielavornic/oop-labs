package queues;

import java.util.Vector;

public class VectorQueue<E> implements Queue<E> {
  private Vector<E> vector = new Vector<>();

  public void enqueue(E item) {
    vector.add(item);
  }

  public E dequeue() {
    return vector.remove(0);
  }

  public E peek() {
    return vector.firstElement();
  }

  public boolean isEmpty() {
    return vector.isEmpty();
  }

  public int size() {
    return vector.size();
  }
}
