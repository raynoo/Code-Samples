package braintree.repository;

import java.util.HashMap;
import java.util.List;

import braintree.card.AbstractCard;

/**
 * Interface representing a repository for storing cards.
 * 
 * @author renus
 *
 */
public interface IRepository {
	
	/**
	 * Get card(s) associated with a card-holder's name.
	 * @param name
	 * @return list of card(s) belonging to name
	 */
	public List<AbstractCard> getCards(String name);
	
	/**
	 * Get all cards in the repository, with card-holder name as keys.
	 * @return hashmap of all cards
	 */
	public HashMap<String, List<AbstractCard>> getAllCards();
	
	/**
	 * Adds a card to the repository.
	 * @param name
	 * @param number
	 * @param limit
	 */
	public void addCard(String name, String number, int limit);
	
	/**
	 * Deletes entry of a card-holder from repository.
	 * @param name
	 */
	public void deleteCards(String name);
}
