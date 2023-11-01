package stacks;

import java.util.LinkedList;

public class LinkedStack<E> implements Stack<E> {
  private LinkedList<E> list = new LinkedList<>();

  public void push(E item) {
    list.push(item);
  }

  public E pop() {
    return list.pop();
  }

  public E peek() {
    return list.peek();
  }

  public boolean isEmpty() {
    return list.isEmpty();
  }

  public int size() {
    return list.size();
  }
}
