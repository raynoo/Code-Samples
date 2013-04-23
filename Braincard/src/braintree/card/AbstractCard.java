package braintree.card;

/**
 * A basic abstract class meant to represent all kinds of payment cards.
 * @author renus
 *
 */
public abstract class AbstractCard {

	protected String name, number;
	protected int limit, balance;
	protected boolean isValid;
	
	protected AbstractCard(String name, String number, int limit) {
		this.name = name;
		this.number = number;
		this.limit = limit;
		this.balance = 0;
	}

	/**
	 * Charges an amount to the card balance.
	 * @return success
	 */
	public abstract boolean chargeAmount(int amount);
	
	/**
	 * Adds a credit amount to the card balance.
	 * @return success
	 */
	public abstract boolean creditAmount(int amount);
	
	/**
	 * Determines if a card is valid using a luhn 10 check.
	 * @return validity of card
	 */
	protected abstract boolean isValid();
	
	/**
	 * Prints a report for a card with card-holder's name and card-balance.
	 * @return report string
	 */
	public abstract String getReport();
	
	protected String getCardNumber() {
		return number;
	}

	protected String getName() {
		return name;
	}

	protected int getLimit() {
		return limit;
	}

	protected int getBalance() {
		return balance;
	}

	protected void setBalance(int balance) {
		this.balance = balance;
	}
	
}
