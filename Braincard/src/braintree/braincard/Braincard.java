package braintree.braincard;

import braintree.manager.CardManager;
import braintree.repository.RepositoryFactory.RepoType;
import braintree.validator.InputValidator;

/**
 * The main application module that interacts and delegates requests 
 * to the card manager.
 * @author renus
 *
 */
public class Braincard {

	CardManager cardManager;
	
	public enum Action {
		ADD, CHARGE, CREDIT;
		
		public String getValue() {
			return this.name().toLowerCase();
		}
	};
	
	/**
	 * Initialize a card manager with repository type of HashMap.
	 */
	public Braincard() {
		this.cardManager = new CardManager(RepoType.HASH);
	}
	
	/**
	 * Validates input values and calls correct method according to action.
	 * @param action
	 * @param cardHolderName
	 * @param cardNumber
	 * @param transactionAmount
	 * @param limit
	 * @return success
	 */
	public boolean processInput(String action, String cardHolderName, 
			String cardNumber, String transactionAmount, String limit) {
		
		if(InputValidator.validate(action, cardHolderName, cardNumber, transactionAmount, limit)) {
			if(action.toLowerCase().equals(Action.ADD.getValue())) {
				return addNewCard(cardHolderName, cardNumber, limit);
			}
			else if(action.toLowerCase().equals(Action.CHARGE.getValue())) {
				return chargeAmountToCard(cardHolderName, transactionAmount);
			}
			else if(action.toLowerCase().equals(Action.CREDIT.getValue())) {
				return creditAmountToCard(cardHolderName, transactionAmount);
			}
			else
				return false;
		}
		else
			return false;
	}
	
	/**
	 * Delegates add-card function to card manager.
	 * @param name
	 * @param number
	 * @param limit
	 * @return success
	 */
	private boolean addNewCard(String name, String number, String limit) {
		return this.cardManager.addNewCard(name, number, limit);
	}
	
	/**
	 * Delegates charge-card function to card manager.
	 * @param name
	 * @param amount
	 * @return success
	 */
	private boolean chargeAmountToCard(String name, String amount) {
		return this.cardManager.chargeAmountToCard(name, amount);
	}
	
	/**
	 * Delegates credit-card function to card manager.
	 * @param name
	 * @param amount
	 * @return success
	 */
	private boolean creditAmountToCard(String name, String amount) {
		return this.cardManager.creditAmountToCard(name, amount);
	}
	
	/**
	 * Delegates print-report function to card manager.
	 */
	public void generateReport() {
		this.cardManager.generateReport();
	}
}
