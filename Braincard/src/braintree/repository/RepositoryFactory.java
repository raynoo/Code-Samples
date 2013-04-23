package braintree.repository;

/**
 * Factory class that supplies an instance of a repository.
 * (Repository is of type HashMap for now. Can support other 
 * types in future, such as a database, flat file etc.)
 * 
 * @author renus
 *
 */
public class RepositoryFactory {
	
	public enum RepoType {
		HASH, DB, FILE;
	}
	
	public static IRepository getRepository(RepoType type) {
		if(type.equals(RepoType.HASH))
			return HashRepository.getInstance();
		
		return null;
	}
}
