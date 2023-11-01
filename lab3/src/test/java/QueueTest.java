import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import queues.*;

public class QueueTest {

  private Queue<Integer> arrayQueue;
  private Queue<Integer> vectorQueue;

  @BeforeEach
  public void setUp() {
    arrayQueue = new ArrayQueue<>();
    vectorQueue = new VectorQueue<>();
  }

  @Test
  public void testEnqueueAndDequeue() {
    assertEnqueueAndDequeue(arrayQueue);
    assertEnqueueAndDequeue(vectorQueue);
  }

  @Test
  public void testPeek() {
    assertPeek(arrayQueue);
    assertPeek(vectorQueue);
  }

  @Test
  public void testIsEmpty() {
    assertTrue(arrayQueue.isEmpty());
    assertTrue(vectorQueue.isEmpty());

    arrayQueue.enqueue(1);
    vectorQueue.enqueue(1);

    assertFalse(arrayQueue.isEmpty());
    assertFalse(vectorQueue.isEmpty());
  }

  private void assertEnqueueAndDequeue(Queue<Integer> queue) {
    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);

    assertEquals(1, queue.dequeue());
    assertEquals(2, queue.dequeue());
    assertEquals(3, queue.dequeue());
  }

  private void assertPeek(Queue<Integer> queue) {
    queue.enqueue(1);
    assertEquals(1, queue.peek());

    queue.enqueue(2);
    assertEquals(1, queue.peek());

    queue.enqueue(3);
    assertEquals(1, queue.peek());
  }
}
