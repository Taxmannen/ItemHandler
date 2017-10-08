package Model;

public class Item {
	private String name;
	private int price;
	private int discount;
	private double discountedPrice;
	
	public Item(String name, int price, int discount) {
		this.name = name;
		this.price = price;
		this.discount = discount;
		discountedPrice = price - price  * (discount * 0.01);
	}
	public String getName() { return name; }
	public int getPrice() { return price; }
	public int getDiscount() { return discount; }
	public double getDiscountedPrice( ) { return discountedPrice; }
}
