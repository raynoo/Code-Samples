package braintree.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import braintree.card.AbstractCard;
import braintree.card.CreditCard;

/**
 * A repository implemented using a HashMap with card-holder name as key
 * and a list of card(s) as values.
 * 
 * @author renus
 *
 */
public class HashRepository implements IRepository {
	
	private static HashMap<String, List<AbstractCard>> cardRepository;
	private static HashRepository instance;
	
	private HashRepository() {
		cardRepository = new HashMap<String, List<AbstractCard>>();
	}
	
	/**
	 * Maintains a single instance of the repository.
	 * @return repository instance
	 */
	public static HashRepository getInstance() {
		if(instance == null)
			instance =  new HashRepository();
		
		return instance;
	}
	
	public HashMap<String, List<AbstractCard>> getAllCards() {
		return cardRepository;
	}
	
	@Override
	public List<AbstractCard> getCards(String name) {
		return cardRepository.get(name);
	}

	@Override
	public void addCard(String name, String number, int limit) {
		List<AbstractCard> cardList;
		if((cardList = cardRepository.get(name)) == null)
			cardList = new ArrayList<AbstractCard>();
	
		cardList.add(new CreditCard(name, number, limit));
		cardRepository.put(name, cardList);
	}

	@Override
	public void deleteCards(String name) {
		cardRepository.remove(name);
	}
	
}
