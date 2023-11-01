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
    arrayStack = new ArrayStack<>();
    vectorStack = new VectorStack<>();
    linkedStack = new LinkedStack<>();
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
    assertTrue(arrayStack.isEmpty());
    assertTrue(vectorStack.isEmpty());
    assertTrue(linkedStack.isEmpty());

    arrayStack.push(1);
    vectorStack.push(1);
    linkedStack.push(1);

    assertFalse(arrayStack.isEmpty());
    assertFalse(vectorStack.isEmpty());
    assertFalse(linkedStack.isEmpty());
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
}
