import java.util.Stack;


public class MiniDc {
	private Stack<Double> runningStack;
	MiniDc(){
		runningStack = new Stack<Double>();
	}

	boolean parseInput(String input){
		try{
			double temp = Double.parseDouble(input);
			runningStack.push(temp);
		} catch(NumberFormatException e){
			System.out.println("You entered invalid input, input junked.");
			return false;
		}
		return true;
	}
	
	double peekFromStack(){
		return runningStack.peek();
	}
	
	boolean isStackEmpty(){
		return runningStack.isEmpty();
	}
}
