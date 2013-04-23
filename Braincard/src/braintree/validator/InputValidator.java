package braintree.validator;

import braintree.braincard.Braincard.Action;

/**
 * Validates input values against each possible action.
 * @author renus
 *
 */
public class InputValidator {

	/**
	 * Validates if any input values are null or empty.
	 * In case of Add function: person name, card number, card limit has to be present.
	 * In case of Charge function: person name, transaction amount has to be present.
	 * In case of Credit function: person name, transaction has to be present.
	 * 
	 * @param action
	 * @param cardHolderName
	 * @param cardNumber
	 * @param transactionAmount
	 * @param limit
	 * @return validity of input
	 */
	public static boolean validate(String action, String cardHolderName, 
			String cardNumber, String transactionAmount, String limit) {
		
		if(action.toLowerCase().equals(Action.ADD.getValue())) {
			if(cardHolderName == null || cardHolderName == "" 
					|| cardNumber == null || cardNumber == ""
					|| limit == null || limit == "")
				return false;
		}
		
		if(action.toLowerCase().equals(Action.CHARGE.getValue())) {
			if(cardHolderName == null || cardHolderName == "" 
					|| transactionAmount == null || transactionAmount == "")
				return false;
		}
		
		if(action.toLowerCase().equals(Action.CREDIT.getValue())) {
			if(cardHolderName == null || cardHolderName == "" 
					|| transactionAmount == null || transactionAmount == "")
				return false;
		}
		
		return true;
	}
	
}
