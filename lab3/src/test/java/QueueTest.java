import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import queues.*;

public class QueueTest {

  private Queue<Integer> arrayQueue;

  @BeforeEach
  public void setUp() {
    arrayQueue = new ArrayQueue<>();
  }

  @Test
  public void testEnqueueAndDequeue() {
    assertEnqueueAndDequeue(arrayQueue);
  }

  @Test
  public void testPeek() {
    assertPeek(arrayQueue);
  }

  @Test
  public void testIsEmpty() {
    assertTrue(arrayQueue.isEmpty());

    arrayQueue.enqueue(1);

    assertFalse(arrayQueue.isEmpty());
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
