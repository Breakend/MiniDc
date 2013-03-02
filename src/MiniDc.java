import java.util.Stack;


public class MiniDc {
	private Stack<Double> runningStack;
	private Stack<String> errorStack;
	private Stack<String> printStack;

	MiniDc(){
		runningStack = new Stack<Double>();
		errorStack = new Stack<String>();
		printStack = new Stack<String>();
	}

	boolean parseInput(String input){
		boolean setToNeg = false;
		input = input.replaceAll("\\s",""); //remove whitespace and non characters
		if ((input.charAt(0) == 'p' || input.charAt(0) == 'P') && input.length() == 1){
			if(!runningStack.isEmpty()){
				printStack.push(Double.toString(runningStack.peek()));
			} else{
				errorStack.push("The stack is empty, you can't print an empty stack");
				return false;
			}

		}
		if (input.charAt(0) == '_' && input.length() > 1){
			input = input.replaceAll("_","");
			setToNeg = true;
		}
		else if(input.charAt(0) == '-' && input.length() > 1){ //used - instead of _ for negative number
			errorStack.push("Improper negative symbol, try _ instead of -");
			return false;
		}
		try{
			double temp = Double.parseDouble(input);
			if(setToNeg == true) temp = temp*-1.0;
			runningStack.push(temp);
		} catch(NumberFormatException e){
			errorStack.push("You entered invalid input, input junked.");
			return false;
		}
		return true;
	}

	String peekPrintStack(){
		return printStack.peek();
	}

	double peekFromStack(){
		return runningStack.peek();
	}
	
	boolean isPrintStackEmpty(){
		return printStack.isEmpty();
	}

	String peekError(){
		return errorStack.peek();
	}

	boolean isStackEmpty(){
		return runningStack.isEmpty();
	}
}
