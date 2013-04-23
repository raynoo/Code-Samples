package braintree.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import braintree.braincard.Braincard;

/**
 * The entry-point class that parses input from file and calls the Braincard module.
 * @author renus
 *
 */
public class CCInputProcessor {

	public static void main(String[] args) {
		new CCInputProcessor().start("creditcard_input.txt");
	}

	public void start(String inputFilename) {
		Braincard bc = new Braincard();
		
		String action, cardHolderName = "", cardNumber = "", transactionAmount = "", limit = "";
		
		Scanner in = null;
		try {
			in = new Scanner(new File(inputFilename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while(in.hasNextLine()) {
			String[] input = in.nextLine().split("\\s+");
			
			action = input[0];
			cardHolderName = input[1];
			if(input[2].charAt(0) == '$')
				transactionAmount = input[2];
			else {
				cardNumber = input[2];
				limit = input[3];
			}
			
			bc.processInput(action, cardHolderName, cardNumber, transactionAmount, limit);
		}
		bc.generateReport();
	}
}
