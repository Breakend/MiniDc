import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class ParseInputTest {

	  @BeforeClass
	  public static void testSetup() {
	  }

	  @AfterClass
	  public static void testCleanup() {
	    // Teardown for data used by the unit tests
	  }

	  @Test
	  public void testExceptionIsThrown() {
	    MiniDc tester = new MiniDc();
	    //Test invalid input
	    assertFalse(tester.parseInput("1adfvd"));
	    //Make sure stack is empty
	    assertTrue(tester.isStackEmpty());
	  }

	  @Test
	  public void testSimpleNumbers() {
	    MiniDc tester = new MiniDc();
	    tester.parseInput(" 1 "); //parse input with space on both sides
	    assertEquals("Result", 1, tester.peekFromStack(), 0); //the top of the stack should be 1
	    tester.parseInput(" 2.75 "); //number with decimal
	    assertEquals("Result", 2.75, tester.peekFromStack(), 0);
	  }

}



 