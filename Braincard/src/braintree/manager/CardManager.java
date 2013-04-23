package braintree.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import braintree.card.AbstractCard;
import braintree.repository.IRepository;
import braintree.repository.RepositoryFactory;
import braintree.repository.RepositoryFactory.RepoType;

/**
 * A manager class to communicate with a repository and to manage functions of a card.
 * @author renus
 *
 */
public class CardManager {

	IRepository repo;
	RepoType type;

	/**
	 * Initializes a card manager and a repository that it manages. 
	 * (Repository is of type HashMap for now. Can support other types in future.)
	 * @param repoType
	 */
	public CardManager(RepoType repoType) {
		this.type = repoType;
		this.repo = RepositoryFactory.getRepository(type);
	}
	
	/**
	 * Adds a new card into repository.
	 * @param name, number, limit
	 * @return success
	 */
	public boolean addNewCard(String name, String number, String limit) {
		
		//remove '$' character
		int cLimit = Integer.parseInt(limit.substring(1));
		this.repo.addCard(name, number, cLimit);
		System.out.println("New card added for " + name);
		return true;
	}
	
	/**
	 * Charges amount to a card in repository.
	 * @param name, amount
	 * @return success
	 */
	public boolean chargeAmountToCard(String name, String amount) {
		List<AbstractCard> cards = this.repo.getCards(name);
		
		if(cards != null && cards.size() == 1) {
			AbstractCard firstCard = cards.get(0);
			//remove '$' character
			int cAmount = Integer.parseInt(amount.substring(1));
			return firstCard.chargeAmount(cAmount);
		}
		else
			return false;
	}
	
	/**
	 * Credits amount to a card in repository.
	 * @param name, amount
	 * @return success
	 */
	public boolean creditAmountToCard(String name, String amount) {
		List<AbstractCard> cards = this.repo.getCards(name);
		
		if(cards != null && cards.size() == 1) {
			AbstractCard firstCard = cards.get(0);
			//remove '$' character
			int cAmount = Integer.parseInt(amount.substring(1));
			return firstCard.creditAmount(cAmount);
		}
		else
			return false;
	}
	
	/**
	 * Generates report for all cards in repository.
	 */
	public void generateReport() {
		HashMap<String, List<AbstractCard>> cards = this.repo.getAllCards();
		
		List<String> names = new ArrayList<String>(cards.keySet());
		Collections.sort(names, new Comparator<String>(){

			@Override
			public int compare(String arg0, String arg1) {
				
				return 0;
			}
			
		});
		
		System.out.println("\nSummary Report");
		System.out.println("--------------");
		for(String name : names) {
			for(AbstractCard card : cards.get(name)) {
				System.out.println(card.getReport());
				
			}
		}
	}
}
