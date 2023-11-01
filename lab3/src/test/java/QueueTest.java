import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import queues.*;

public class QueueTest {

  private Queue<Integer> arrayQueue;
  private Queue<Integer> vectorQueue;
  private Queue<Integer> linkedQueue;

  @BeforeEach
  public void setUp() {
    arrayQueue = new ArrayQueue<>();
    vectorQueue = new VectorQueue<>();
    linkedQueue = new LinkedQueue<>();
  }

  @Test
  public void testEnqueueAndDequeue() {
    assertEnqueueAndDequeue(arrayQueue);
    assertEnqueueAndDequeue(vectorQueue);
    assertEnqueueAndDequeue(linkedQueue);
  }

  @Test
  public void testPeek() {
    assertPeek(arrayQueue);
    assertPeek(vectorQueue);
    assertPeek(linkedQueue);
  }

  @Test
  public void testIsEmpty() {
    assertTrue(arrayQueue.isEmpty());
    assertTrue(vectorQueue.isEmpty());
    assertTrue(linkedQueue.isEmpty());

    arrayQueue.enqueue(1);
    vectorQueue.enqueue(1);
    linkedQueue.enqueue(1);

    assertFalse(arrayQueue.isEmpty());
    assertFalse(vectorQueue.isEmpty());
    assertFalse(linkedQueue.isEmpty());
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
