package stacks;

import java.util.LinkedList;
import static stacks.StackConstants.*;

public class LinkedStack<E> implements Stack<E> {
  private LinkedList<E> list = new LinkedList<>();
  private int capacity;

  public LinkedStack() {
    this(DEFAULT_CAPACITY);
  }

  public LinkedStack(int capacity) {
    this.capacity = capacity;
  }

  public void push(E item) {
    if (!isFull()) {
      list.push(item);
    } else {
      throw new IllegalStateException("Stack is full");
    }
  }

  public E pop() {
    if (isEmpty()) {
      throw new RuntimeException("Stack underflow");
    }
    return list.pop();
  }

  public E peek() {
    return list.peek();
  }

  public boolean isEmpty() {
    return list.isEmpty();
  }

  public boolean isFull() {
    return list.size() == capacity;
  }

  public int size() {
    return list.size();
  }
}
