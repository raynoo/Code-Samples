package main;

import java.util.List;

import objects.ResultObject;

import scraper.Scraper;

/**
 * Class with main entry point that makes calls to Scraper class 
 * to parse through search results in www.shopping.com website.
 * 
 * @author Renu Srinivasan
 *
 */
public class Main {
	
	/**
	 * Reads a search keyword from standard input and makes a request to 
	 * www.shopping.com to search for the keyword. 
	 * 
	 * @param args String[]
	 */
	public static void main(String[] args) {
		Scraper scraper = new Scraper();
		
		if(args != null && (args.length == 1)) {
			String number = scraper.getNumberOfSearchResults(args[0]);
			
			if(number == null || number.isEmpty()) {
				System.err.println("No search results found. Try again with a different search.");
				return;
			}
			
			System.out.println("Total number of search results: " + number);
		}
		
		else if(args != null && args.length == 2) {
			try {
				Integer.parseInt(args[1]);
				
			} catch (NumberFormatException ne) {
				System.err.println("Please enter a number for page number.");
				return;
			}
			
			List<ResultObject> results = scraper.getAllSearchResults(args[0], args[1]);
			
			if(results == null || results.isEmpty()) {
				System.err.println("No search results found. Try again with a different search.");
				return;
			}
			
			System.out.println("All products listed in page " + args[1] + ":\n");
			
			for(ResultObject r : results) {
				System.out.println(r);
				System.out.println("\n----------------------\n");
			}
		}
		
		else {
			System.err.println("Please enter 1 or 2 arguments. Try \"caselogic bags for laptops\" or \"caselogic bags for laptops\" \"5\".");
		}
	}
	
}