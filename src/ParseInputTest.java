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
	public void testNumberOutOfRange(){
		MiniDc tester = new MiniDc();
		tester.parseInput("1.80769313486231570E308"); //Out of Double range, too big
		assertTrue(tester.isStackEmpty()); //Nothing was added to the stack
		assertEquals("Error Strings match", "Out of range.", tester.peekError());
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
		assertTrue(tester.isPrintStackEmpty()); //make sure the printstack is empty still
		assertEquals("Error Strings match", "To print please use p or P alone", tester.peekError()); //If contains p but not proper
	}

	@Test
	public void testNCommandPositiveCase(){
		MiniDc tester = new MiniDc();
		assertTrue(tester.parseInput("_1"));
		assertTrue(tester.parseInput("188.81"));
		assertTrue(tester.parseInput(" n "));
		assertEquals("Latest item on print stack is expected", "188.81", tester.peekPrintStack()); //Latest number
		assertEquals("Latest item on print stack is expected", -1, tester.peekFromStack(), 0); //Latest number
		assertEquals("Result", -1, tester.peekFromStack(), 0); //should have popped 188.81
		assertTrue(tester.parseInput("28.11"));
		assertTrue(tester.parseInput(" N ")); //works with capital
		assertEquals("Latest item on print stack is expected", "28.11", tester.peekPrintStack()); //Latest number
		assertEquals("Latest item on print stack is expected", -1, tester.peekFromStack(), 0); //Latest number
		assertEquals("Result", -1, tester.peekFromStack(), 0); //should have popped 28.11
		assertTrue(tester.parseInput("   n")); //works with capital
		assertEquals("Latest item on print stack is expected", "-1", tester.peekPrintStack()); //Latest number
		assertTrue(tester.isStackEmpty()); //stack should be empty
	}

	@Test
	public void testNCommandEmptyStackAndNotProperFormat(){
		MiniDc tester = new MiniDc();
		assertFalse(tester.parseInput(" n ")); //Doesn't work with empty stack
		assertTrue(tester.isPrintStackEmpty());
		assertEquals("Error Strings match", "Stack Empty", tester.peekError()); //Error should be that the running stack is empty
		tester.parseInput("_1");
		assertFalse(tester.parseInput(" Nicaragua ")); //Doesn't work as part of larger string
		assertEquals("Result", -1, tester.peekFromStack(), 0); //-1 still on stack
		assertTrue(tester.isPrintStackEmpty()); //make sure the printstack is empty still
		assertEquals("Error Strings match", "To print please use n or N alone, if you want to Pop", tester.peekError()); 
		//If contains n but not properly formatted
	}
	
	@Test
	public void testFCommandPositiveCase(){
		MiniDc tester = new MiniDc();
		tester.parseInput("1");
		tester.parseInput("2");
		tester.parseInput("3");
		tester.parseInput("6.4");
		tester.parseInput("_100");
		assertTrue(tester.parseInput(" f "));
		assertEquals("Result: ", "-100", tester.popFromPrintStack());
		assertEquals("Result: ", "6.4", tester.popFromPrintStack());
		assertEquals("Result: ", "3", tester.popFromPrintStack());
		assertEquals("Result: ", "2", tester.popFromPrintStack());
		assertEquals("Result: ", "1", tester.popFromPrintStack());
		assertTrue(tester.isPrintStackEmpty()); //contains all the inputed numbers in print stack (gets printed in main loop)
		assertTrue(tester.parseInput(" n ")); //pop -100 off the stack
		assertEquals("Result: ", "-100", tester.popFromPrintStack());//because of n
		assertTrue(tester.isPrintStackEmpty()); //print stack empty
		assertTrue(tester.parseInput(" f ")); //f no longer has -100
		assertEquals("Result: ", "6.4", tester.popFromPrintStack());
		assertEquals("Result: ", "3", tester.popFromPrintStack());
		assertEquals("Result: ", "2", tester.popFromPrintStack());
		assertEquals("Result: ", "1", tester.popFromPrintStack());
		assertTrue(tester.isPrintStackEmpty());
	}
	
	@Test
	public void testFCommandNegativeCaseEmptyStack(){
		MiniDc tester = new MiniDc();
		assertFalse(tester.parseInput(" f "));
		assertEquals("Error Strings match", "Stack Empty", tester.peekError()); //Error should be that the running stack is empty
	}
	
	@Test
	public void testAddPositiveTestCase(){
		MiniDc tester = new MiniDc();
		tester.parseInput("1");
		tester.parseInput("2");
		assertTrue(tester.parseInput(" + "));
		assertEquals("Result", 3, tester.peekFromStack(), 0); //Result pushed to stack
		assertTrue(tester.isPrintStackEmpty()); //print stack should be empty
		tester.parseInput("n"); //pop result of stack
		assertTrue(tester.isStackEmpty()); //stack should be empty, the original numbers should not be there
	}
	
	@Test
	public void testAddNegativeCaseEmptyStack(){
		MiniDc tester = new MiniDc();
		assertFalse(tester.parseInput(" + "));
		assertEquals("Error Strings match", "Stack Empty", tester.peekError()); //Error should be that the running stack is empty
		tester.parseInput("2");
		assertFalse(tester.parseInput(" + "));
		assertEquals("Error Strings match", "Only one number on stack, can't do operation", tester.peekError()); 
		assertFalse(tester.parseInput(" +2"));
		assertEquals("Error Strings match", "If you want to add 2 numbers, push them to the stack first then send + alone", tester.peekError()); 
	}

}



