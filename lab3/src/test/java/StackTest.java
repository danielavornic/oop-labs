import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import stacks.*;

public class StackTest {

  private Stack<Integer> arrayStack;

  @BeforeEach
  public void setUp() {
    arrayStack = new ArrayStack<>();
  }

  @Test
  public void testPushAndPop() {
    assertPushAndPop(arrayStack);
  }

  @Test
  public void testPeek() {
    assertPeek(arrayStack);
  }

  @Test
  public void testIsEmpty() {
    assertTrue(arrayStack.isEmpty());

    arrayStack.push(1);

    assertFalse(arrayStack.isEmpty());
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
