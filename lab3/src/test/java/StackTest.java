import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import stacks.*;

public class StackTest {

  private Stack<Integer> arrayStack;
  private Stack<Integer> vectorStack;
  private Stack<Integer> linkedStack;

  @BeforeEach
  public void setUp() {
    arrayStack = new ArrayStack<>(3);
    vectorStack = new VectorStack<>(3);
    linkedStack = new LinkedStack<>(3);
  }

  @Test
  public void testPushAndPop() {
    assertPushAndPop(arrayStack);
    assertPushAndPop(vectorStack);
    assertPushAndPop(linkedStack);
  }

  @Test
  public void testPeek() {
    assertPeek(arrayStack);
    assertPeek(vectorStack);
    assertPeek(linkedStack);
  }

  @Test
  public void testIsEmpty() {
    assertIsEmpty(arrayStack);
    assertIsEmpty(vectorStack);
    assertIsEmpty(linkedStack);
  }

  @Test
  public void testIsFull() {
    assertIsFull(arrayStack);
    assertIsFull(vectorStack);
    assertIsFull(linkedStack);
  }

  private void assertPushAndPop(Stack<Integer> stack) {
    stack.push(1);
    stack.push(2);
    stack.push(3);

    assertEquals(3, stack.pop());
    assertEquals(2, stack.pop());
    assertEquals(1, stack.pop());
  }

  private void assertPeek(Stack<Integer> stack) {
    stack.push(1);
    assertEquals(1, stack.peek());

    stack.push(2);
    assertEquals(2, stack.peek());

    stack.push(3);
    assertEquals(3, stack.peek());
  }

  private void assertIsEmpty(Stack<Integer> stack) {
    assertTrue(stack.isEmpty());
    stack.push(1);
    assertFalse(stack.isEmpty());
  }

  private void assertIsFull(Stack<Integer> stack) {
    stack.push(1);
    stack.push(2);
    assertFalse(stack.isFull());

    stack.push(3);
    assertTrue(stack.isFull());
  }
}
