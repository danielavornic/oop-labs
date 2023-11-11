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
    arrayQueue = new ArrayQueue<>(3);
    vectorQueue = new VectorQueue<>(3);
    linkedQueue = new LinkedQueue<>(3);
  }

  @Test
  public void testPushAndPop() {
    assertPushAndPop(arrayQueue);
    assertPushAndPop(vectorQueue);
    assertPushAndPop(linkedQueue);
  }

  @Test
  public void testPeek() {
    assertPeek(arrayQueue);
    assertPeek(vectorQueue);
    assertPeek(linkedQueue);
  }

  @Test
  public void testIsEmpty() {
    assertIsEmpty(arrayQueue);
    assertIsEmpty(vectorQueue);
    assertIsEmpty(linkedQueue);
  }

  @Test
  public void testIsFull() {
    assertIsFull(arrayQueue);
    assertIsFull(vectorQueue);
    assertIsFull(linkedQueue);
  }

  private void assertPushAndPop(Queue<Integer> queue) {
    queue.push(1);
    queue.push(2);
    queue.push(3);

    assertEquals(1, queue.pop());
    assertEquals(2, queue.pop());
    assertEquals(3, queue.pop());
  }

  private void assertPeek(Queue<Integer> queue) {
    queue.push(1);
    assertEquals(1, queue.peek());

    queue.push(2);
    assertEquals(1, queue.peek());

    queue.push(3);
    assertEquals(1, queue.peek());
  }

  private void assertIsEmpty(Queue<Integer> queue) {
    assertTrue(queue.isEmpty());

    queue.push(1);
    assertFalse(queue.isEmpty());

    queue.pop();
    assertTrue(queue.isEmpty());
  }

  private void assertIsFull(Queue<Integer> queue) {
    for (int i = 0; i < 2; i++) {
      queue.push(i);
      assertFalse(queue.isFull());
    }

    queue.push(3);
    assertTrue(queue.isFull());
  }
}
