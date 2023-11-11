package queues;

import static queues.QueueConstants.*;

public class ArrayQueue<E> implements Queue<E> {
  private Object[] array;
  private int front, rear, size;
  private int capacity;

  public ArrayQueue() {
    this.capacity = DEFAULT_CAPACITY;
    array = new Object[DEFAULT_CAPACITY];
    front = 0;
    rear = 0;
    size = 0;
  }

  public ArrayQueue(int capacity) {
    array = new Object[capacity];
    front = 0;
    rear = 0;
    size = 0;
  }

  public void push(E item) {
    if (!isFull()) {
      array[rear] = item;
      rear = (rear + 1) % array.length;
      size++;
    } else {
      throw new IllegalStateException("Queue is full");
    }
  }

  @SuppressWarnings("unchecked")
  public E pop() {
    if (isEmpty()) {
      throw new RuntimeException("Queue underflow");
    }
    E item = (E) array[front];
    array[front] = null;
    front = (front + 1) % array.length;
    size--;
    if (size > 0 && size == array.length / 4) {
      resize(array.length / 2);
    }
    return item;
  }

  @SuppressWarnings("unchecked")
  public E peek() {
    if (isEmpty()) {
      throw new RuntimeException("Queue is empty");
    }
    return (E) array[front];
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean isFull() {
    return size == array.length;
  }

  public int size() {
    return size;
  }

  private void resize(int capacity) {
    Object[] temp = new Object[capacity];
    for (int i = 0; i < size; i++) {
      temp[i] = array[(front + i) % array.length];
    }
    array = temp;
    front = 0;
    rear = size;
  }
}
