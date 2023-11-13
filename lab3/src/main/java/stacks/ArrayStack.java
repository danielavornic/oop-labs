package stacks;

import static stacks.StackConstants.*;

public class ArrayStack<E> implements Stack<E> {
  private Object[] array;
  private int top;

  public ArrayStack() {
    array = new Object[DEFAULT_CAPACITY];
    top = -1;
  }

  public ArrayStack(int capacity) {
    array = new Object[capacity];
    top = -1;
  }

  public void push(E item) {
    if (isFull()) {
      throw new IllegalStateException("Stack is full");
    }
    array[++top] = item;

  }

  @SuppressWarnings("unchecked")
  public E pop() {
    if (isEmpty()) {
      throw new RuntimeException("Stack is empty");
    }
    return (E) array[top--];
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

  public boolean isFull() {
    return top == array.length - 1;
  }

  public int size() {
    return top + 1;
  }
}
