package braintree.card;

/**
 * A credit card class.
 * @author renus
 *
 */
public class CreditCard extends AbstractCard {

	public CreditCard(String name, String number, int limit) {
		super(name, number, limit);
		this.isValid = isValid();
	}
	
	/**
	 * Luhn checker for determining card number's validity
	 * Source: http://code.google.com/p/gnuc-credit-card-checker/source/
	 * 	browse/trunk/CCCheckerPro/src/com/gnuc/java/ccc/Luhn.java
	 * 
	 * @return validity
	 */
	public boolean isValid() {
		//length check
		boolean length = this.getCardNumber().length() == 14 || this.getCardNumber().length() == 15 
				|| this.getCardNumber().length() == 16 || this.getCardNumber().length() == 19;
		
		//luhn check
		int sum = 0;
		boolean alternate = false;
		
		for(int i = getCardNumber().length() - 1; i >= 0; i--) {
			int n = Integer.parseInt(getCardNumber().substring(i, i + 1));
			if (alternate) {
				n *= 2;
				if (n > 9)
					n = (n % 10) + 1;
			}
			sum += n;
			alternate = !alternate;
		}
		return (sum % 10 == 0) && length;
	}
	
	public boolean chargeAmount(int amount) {
		if(this.isValid && ((getBalance() + amount) <= getLimit())) {
			setBalance(getBalance() + amount);
			System.out.println("Charged amount to " + this.getName());
			return true;
		}
		System.out.println("Unable to credit amount to " + this.getName());
		return false;
	}
	
	public boolean creditAmount(int amount) {
		if(this.isValid) {
			setBalance(getBalance() - amount);
			System.out.println("Credited amount to " + this.getName());
			return true;
		}
		System.out.println("Unable to credit amount to " + this.getName());
		return false;
	}
	
	
	public String getReport() {
		if(this.isValid)
			return this.getName() + ": $" + this.getBalance();
		return this.getName() + ": error";
		
	}

}