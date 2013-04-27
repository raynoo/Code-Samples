package objects;

/**
 * Class representing an item/product in a search results page.
 * 
 * @author Renu Srinivasan
 *
 */
public class ResultObject {
	
	String title, productPrice, shippingPrice, vendorName;
	
	/**
	 * Creates a result object with product name, product price,
	 * shipping price and vendor name as input.
	 * 
	 * @param title String
	 * @param productPrice String
	 * @param shippingPrice String
	 * @param vendorName String
	 */
	public ResultObject(String title, String productPrice,
			String shippingPrice, String vendorName) {
		this.title = title;
		this.productPrice = productPrice;
		this.shippingPrice = shippingPrice;
		this.vendorName = vendorName;
	}
	
	/**
	 * Returns product title.
	 * 
	 * @return product title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns product price.
	 * 
	 * @return product price
	 */
	public String getProductPrice() {
		return productPrice;
	}

	/**
	 * Returns product shipping price.
	 * 
	 * @return product shipping price
	 */
	public String getShippingPrice() {
		return shippingPrice;
	}

	/**
	 * Returns vendor name.
	 * 
	 * @return vendor name
	 */
	public String getVendor() {
		return vendorName;
	}
	
	/**
	 * String representation of ResultObject listing out
	 * the instance variables.
	 */
	@Override
	public String toString() {
		return "Title/Product Name: " + this.title + "\n" 
				+ "Price: " + this.productPrice + "\n" 
				+ "Shipping Price: " + this.shippingPrice + "\n" 
				+ "Vendor: " + this.vendorName;
	}
}