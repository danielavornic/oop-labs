package queues;

import java.util.LinkedList;

public class LinkedQueue<E> implements Queue<E> {
  private LinkedList<E> list = new LinkedList<>();

  public void enqueue(E item) {
    list.addLast(item);
  }

  public E dequeue() {
    return list.removeFirst();
  }

  public E peek() {
    return list.peekFirst();
  }

  public boolean isEmpty() {
    return list.isEmpty();
  }

  public int size() {
    return list.size();
  }
}
