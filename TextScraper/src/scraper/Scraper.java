package scraper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import objects.ResultObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * A text scraper that submits product search requests to www.shopping.com 
 * website and parses through the page to retrieve products.
 * 
 * @author Renu Srinivasan
 *
 */
public class Scraper {
	
	private static String websiteURL = "http://www.shopping.com/products";
	private static String pageTag = "~PG-";
	private static String keywordTag = "?KW=";
	
	/**
	 * Parses a search result page for a given keyword and retrieves 
	 * the total number of search results returned by the website.
	 * 
	 * @param searchKeywords String
	 * @return Total number of search results
	 */
	public String getNumberOfSearchResults(String searchKeywords) {
		String number = "No Search Results";
		Document document = getWebDocument(createSearchURL(searchKeywords));
		
		Elements numOfItemsElement = document.getElementsByAttributeValueContaining("name", "NumItemsReturned");
		
		if(numOfItemsElement != null && numOfItemsElement.attr("name") != null 
				&& numOfItemsElement.attr("name").length() !=0) {
			number = numOfItemsElement.attr("name").split(":")[1];
		}
		
		return number;
	}
	
	/**
	 * Parses a search result page for a given keyword and page number 
	 * and retrieves all items/products in it.
	 * 
	 * @param searchKeywords String
	 * @param pageNumber String
	 * @return All items/products in search page
	 */
	public List<ResultObject> getAllSearchResults(String searchKeywords, String pageNumber) {
		List<ResultObject> results = new ArrayList<ResultObject>();
		Document document = getWebDocument(createPageURL(searchKeywords, pageNumber));
		Elements items = document.getElementsByAttributeValueContaining("class", "gridItemBtm"), temp;
		
		for(Element e : items) {
			
			String title = "Not Available", shipping = "Not Available", 
					price = "Not Available", vendorname = "Not Available";
			
			//get product title
			if((temp = e.getElementsByAttributeValueContaining("class", "quickLookGridItemFullName")) != null
					&& !temp.isEmpty()) {
				title = temp.text();
			}

			//get product shipping price
			if((temp = e.getElementsByAttributeValueContaining("class", "freeShip")) != null
					&& !temp.isEmpty() && temp.text() != null && !temp.text().isEmpty()) {
				shipping = temp.text();
			}
			else if((temp = e.getElementsByAttributeValueContaining("class", "calc")) != null
					&& !temp.isEmpty() && temp.text() != null && !temp.text().isEmpty()) {
				shipping = temp.text().split("\\s")[1];
			}
			else if((temp = e.getElementsByAttributeValueContaining("class", "missCalc")) != null
					&& !temp.isEmpty() && temp.text() != null && !temp.text().isEmpty()) {
				shipping = temp.text();
			}
			
			//get product product price
			if((temp = e.getElementsByAttributeValueContaining("class", "productPrice")) != null
					&& !temp.isEmpty()) {
				price = temp.text();
			}
			
			//get product vendor name
			if((temp = e.getElementsByAttributeValueContaining("id", "DCTmerchLogo")) != null
					&& !temp.isEmpty()) {
				vendorname = temp.attr("title");
			} else if((temp = e.getElementsByAttributeValueContaining("id", "numStores")) != null
					&& !temp.isEmpty()) {
				vendorname = temp.text();
			}
			
			results.add(new ResultObject(title, price, shipping, vendorname));
		}
		return results;
	}
	
	/**
	 * Creates HTTP URL string with given search keywords.
	 * 
	 * @param keywords String
	 * @return url string
	 */
	private static String createSearchURL(String keywords) {
		return websiteURL + keywordTag + keywordsToString(keywords, "+");
	}
	
	/**
	 * Creates HTTP URL string with given keywords and a page number.
	 * 
	 * @param keywords String
	 * @param pageNum String
	 * @return url string
	 */
	private static String createPageURL(String keywords, String pageNum) {
		return websiteURL + pageTag + pageNum + keywordTag + keywordsToString(keywords, "+");
	}
	
	/**
	 * Encodes and joins given keywords with a delimiter.
	 * 
	 * @param keywords String
	 * @param delimiter String
	 * @return formatted keywords as string
	 */
	private static String keywordsToString(String keywords, String delimiter) {
		String key = "";
		
		try {
			//split into words and encode
			String[] keys = keywords.split("\\s");
			
			for(int i = 0; i < keys.length; i++)
				keys[i] = URLEncoder.encode(keys[i], "UTF-8");
			
			//join elements back as a string
			key = Arrays.asList(keys).toString().replaceAll("(^\\[|\\]$)", "").replace(", ", delimiter);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return key;
	}
	
	/**
	 * Places HTTP request to a given URL.
	 *  
	 * @param url String
	 * @return Jsoup Document object
	 */
	private static Document getWebDocument(String url) {
//		System.out.println(url);
		
		Document document = null;
		try {
			document = Jsoup.connect(url).get();
		} catch (IOException e) {
			System.err.println("Could not connect to www.shopping.com. Try again.");
		}
		return document;
	}
	
}