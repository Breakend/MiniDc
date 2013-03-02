import java.util.Stack;


public class MiniDc {
	private Stack<Double> runningStack;
	private Stack<String> errorStack;
	
	MiniDc(){
		runningStack = new Stack<Double>();
		errorStack = new Stack<String>();
	}

	boolean parseInput(String input){
		boolean setToNeg = false;
		input = input.replaceAll("\\s",""); //remove whitespace and non characters
		if (input.charAt(0) == '_'){
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
	
	double peekFromStack(){
		return runningStack.peek();
	}
	
	String peekError(){
		return errorStack.peek();
	}
	
	boolean isStackEmpty(){
		return runningStack.isEmpty();
	}
}
