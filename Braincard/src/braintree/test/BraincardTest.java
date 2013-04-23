package braintree.test;

import org.junit.Test;
import org.junit.runner.JUnitCore;

import braintree.braincard.Braincard;
import braintree.card.CreditCard;
import junit.framework.TestCase;

/**
 * Test class to check parameters of input requests.
 * @author renus
 *
 */
public class BraincardTest extends TestCase {

	Braincard bc;
	
	public static void main(String[] args) {
		BraincardTest tester = new BraincardTest();
		tester.testBraincard();
		tester.testCardValid();
		
//		JUnitCore.main("BraincardTest");
	}

	/**
	 * tests add, charge and credit requests.
	 * tests validity of incorrect number of input parameters.
	 */
	
	@Test
	public void testBraincard() {
		bc = new Braincard();
		
		//successful addition
		assertTrue(bc.processInput("Add", "Renu", "3088000000000009", "", "100"));
		
		//successful charge
		assertTrue(bc.processInput("Charge", "Renu", "", "50", ""));

		//successful credit
		assertTrue(bc.processInput("Credit", "Renu", "", "10", ""));

		//wrong number of input arguments
		assertFalse(bc.processInput("Add", "Kevin", "201400000000009", "", ""));
		assertFalse(bc.processInput("Add", "Vaibhav", "", "", "1000"));
		assertFalse(bc.processInput("Add", "", "1122334455667788", "", "2000"));
		assertFalse(bc.processInput("Charge", "", "", "100", ""));
		assertFalse(bc.processInput("Credit", "Renu", "", "", ""));
		
		//excess charge
		assertFalse(bc.processInput("Charge", "Renu", "", "250", ""));
		
	}
	
	/**
	 * tests validity of sample card numbers.
	 */
	@Test
	public void testCardValid() {
		CreditCard c1 = new CreditCard("Renu", "3088000000000009", 100);
		CreditCard c2 = new CreditCard("Renu", "1122334455667788", 200);
		CreditCard c3 = new CreditCard("Renu", "201400000000009", 300);
		
		assertTrue(c1.isValid());
		assertFalse(c2.isValid());
		assertTrue(c3.isValid());
	}
	
}