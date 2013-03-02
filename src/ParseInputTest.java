import static org.junit.Assert.*;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class ParseInputTest {

	  @Test
	  public void testErrorForInvalidInput() {
	    MiniDc tester = new MiniDc();
	    //Test invalid input
	    assertFalse(tester.parseInput("1adfvd"));
		assertEquals("Error Strings match", "You entered invalid input, input junked.", tester.peekError());
	    //Make sure stack is empty
	    assertTrue(tester.isStackEmpty());
	  }

	  @Test
	  public void testSimplePositiveNumbers() {
	    MiniDc tester = new MiniDc();
	    tester.parseInput(" 1 "); //parse input with space on both sides
	    assertEquals("Result", 1, tester.peekFromStack(), 0); //the top of the stack should be 1
	    tester.parseInput(" 2.75 "); //number with decimal
	    assertEquals("Result", 2.75, tester.peekFromStack(), 0);
	  }
	  
	  @Test
	  public void testSimpleNegativeNumbersPositiveCase(){
		  MiniDc tester = new MiniDc();
		  tester.parseInput(" _1");
		  assertEquals("Result", -1, tester.peekFromStack(), 0);
	  }
	  
	  @Test
	  public void testSimpleNegativeNumbersNegativeCase(){
		  MiniDc tester = new MiniDc();
		  //try to use minus operator as negative, should fail
		  tester.parseInput(" 2.75 "); //number with decimal
		  assertFalse(tester.parseInput(" -1 ")); 
		  assertEquals("Error Strings match", "Improper negative symbol, try _ instead of -", tester.peekError());
		  assertEquals("Result", 2.75, tester.peekFromStack(), 0);
		  tester.parseInput(" _");
		  assertEquals("Result", 2.75, tester.peekFromStack(), 0);
		  assertEquals("Error Strings match", "You entered invalid input, input junked.", tester.peekError());
	  }
	  
	  @Test
	  public void testPCommand(){
		  MiniDc tester = new MiniDc();
		  tester.parseInput("_1");
		  assertEquals("Result", -1, tester.peekFromStack(), 0);
		  assertTrue(tester.parseInput(" P "));
		  assertEquals("Latest item on print stack is expected", "-1", tester.peekPrintStack());
		  assertTrue(tester.parseInput(" P ")); //number is still on stack
		  assertEquals("Latest item on print stack is expected", "-1", tester.peekPrintStack());
		  assertTrue(tester.parseInput(" p ")); //works with lower case
		  assertEquals("Latest item on print stack is expected", "-1", tester.peekPrintStack());
		  tester.parseInput("112"); //push another number to the stack
		  assertTrue(tester.parseInput(" p ")); //works with lower case
		  assertEquals("Latest item on print stack is expected", "112", tester.peekPrintStack()); //Latest number
		  
	  }
	  
	  @Test
	  public void testPCommandEmptyStackAndNotProperFormat(){
		  MiniDc tester = new MiniDc();
		  assertFalse(tester.parseInput(" p ")); //Doesn't work with empty stack
		  assertTrue(tester.isPrintStackEmpty());
		  assertEquals("Error Strings match", "Stack Empty", tester.peekError()); //Error should be that the running stack is empty
		  tester.parseInput("_1");
		  assertFalse(tester.parseInput(" PRINT ")); //Doesn't work as part of larger string
		  assertThat("-1", not(equalTo(tester.peekPrintStack())));
		  assertEquals("Error Strings match", "To print please use p or P alone", tester.peekError()); //If contains p but not proper
	  }

}



 