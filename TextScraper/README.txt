Shopping.com Results Text Scraper
-----------------------------------------------------------------------------

A text scraper that connects to www.shopping.com website and submits a
search query for given set of keywords and returns results as shown in
the webpage.

by Renu Srinivasan



LIBRARIES USED
-----------------------------------------------------------------------------
jsoup 1.7.2 - HTML parsing library (http://jsoup.org)



SYSTEM REQUIREMENTS
-----------------------------------------------------------------------------
JRE 6 or above.



INSTALLATION
-----------------------------------------------------------------------------
Download the jar to your computer.

To execute the application, enter a query in this format, 
	java -jar textscraper.jar <arguments i | arguments i and ii>



INPUT
-----------------------------------------------------------------------------
Arguments:

i. 	A set of keywords for which you wish to search on Shopping.com website.
	- Should be in string format enclosed within "".
	 
ii. A page number which denotes a particular page number (among the search 
results) that you wish to view results for.
	- Should be in number format (using digits).


Query 1: 
	Display total number of results for keywords.
	Provide argument i as mentioned above.
	Example query: java -jar textscraper.jar "caselogic bags for laptops"


Query 2:
	Display all items/products in a particular page of search results.
	Provide arguments i and ii as mentioned above.
	Example query: java -jar textscraper.jar "caselogic bags for laptops" 5



OUTPUT
-----------------------------------------------------------------------------
Query 1:
	Displays total number of results returned by Shopping.com for given 
	keywords.
	
Query 2:
	Displays details of all items/products in a particular page number of 
	search results, as returned by Shopping for the given keywords.
	Details of items are formatted as,
		Title/Product Name: <product name>
		Price: <price>
		Shipping Price: <shipping price>
		Vendor: <3rd party vendor or number of stores selling the item>



JAVA DOCS
-----------------------------------------------------------------------------
Documentation is present under doc folder.


