package Model;

public class Receipt {
	private int quantity; 
	private String name;
	private double totalPrice;
	
	public Receipt(Item i, int quantity) {
		this.quantity = quantity;
		name = i.getName();
		totalPrice = i.getDiscountedPrice() * quantity;
	}
	public int getQuantity() { return quantity; }
	public String getName() { return name; }
	public double getTotalPrice() { return totalPrice; }
}
