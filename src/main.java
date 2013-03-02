import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class main {

	public static void main(String[] args) throws IOException{
		System.out.println("Hello and welcome to the MiniDc calculator. You may begin calculations...");
		String input = "";
		boolean breakloop = false;
		MiniDc miniDc = new MiniDc();
		InputStreamReader converter = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(converter);
		while(!breakloop){
			input = in.readLine();
			miniDc.parseInput(input);
			miniDc.printErrorStack();
			miniDc.printPrintStack();
			if((input.charAt(0) == 'q') || (input.charAt(0) == 'Q') || input.contains("quit")) breakloop = true;
		}
	}
}
