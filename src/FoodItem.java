import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FoodItem {
	
	protected int itemCode;
	protected String itemName;
	protected float itemPrice = -1;
	protected int itemQuantityInStock = -1;
	protected float itemCost = -1;
	
	/**
	 * @author Owner : this is the default constructor foodItem class
	 */
	public FoodItem() {
		
	}
	
	/**
	 * @return this method returns formated string
	 */
	@Override
	public String toString() {
		String output;
		output = String.format("Item: %2d %2s %2d, price: $%2.2f cost: %2.2f %n", itemCode, itemName, itemQuantityInStock, itemPrice, itemCost);
		return output;
	}
	
	/**
	 * 
	 * @param amount: to access the method user need to specify how many items they wants to sell or buy
	 * @return : if the amount is greater than in stock quantity then it returns false else it returns true
	 */
	public boolean updateItem (int amount) {
		if (itemQuantityInStock >= (amount *-1)) {
			itemQuantityInStock += amount;
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param item: this method needs item class object to access
	 * @return : this method check the item code passed from parameter is equal to the item code is in an array
	 */
	public boolean isEqual (FoodItem item) {
		return this.itemCode == item.itemCode;
	}
	
	/**
	 * @param keys: this method accept scanner class object.
	 * @return : if everything goes well then this method returns true.
	 * @param fromFile : accept true or false.
	 */
	public boolean addItem (Scanner keys, boolean fromFile) {
		System.out.print("Enter the name for the item: ");
		itemName = keys.nextLine();
		
		do {			
			try {
				System.out.print("Enter the quantity for the item: ");
				itemQuantityInStock = keys.nextInt();
				if (itemQuantityInStock < 0) {
					System.out.println("Invalid Quantity. Qunatity amount cannot be negative.");
				}
			}catch (InputMismatchException e) {
				System.out.println("Invalid Code");
				keys.nextLine();
			}
		} while(itemQuantityInStock == -1 || itemQuantityInStock < -1);
		
		do {
			try {
				System.out.print("Enter the cost of the item: ");
				itemCost = keys.nextFloat();
				if (itemCost < 0) {
					System.out.println("Invalid cost. Cost cannot be negative");
				}
			}catch (InputMismatchException e) {
				System.out.println("Invalid Code");
				keys.nextLine();
			}
		}while(itemCost == -1 || itemCost < -1);
		
		do {
			try {
				System.out.print("Enter te sales price of the item: ");
				itemPrice = keys.nextFloat();
				if (itemPrice < 0) {
					System.out.println("Invalid Sales Price. Price cannot be negative");
				}
			}catch (InputMismatchException e) {
				System.out.println("Invalid Code");
				keys.nextLine();
			}
		}while(itemPrice == -1 || itemPrice < -1);
		return false;
	}
	
	/**
	 * 
	 * @param keys: this method has scanner class objects in parameter. So it help give user chance to enter the item as they want.
	 * @return : if the item code is valid it returns true else it ask user to enter again.
	 * @param fromFile : Accept true and false
	 */
	public boolean inputCode (Scanner keys, boolean fromFile) {
		boolean invalid = false;
		do {
			try {				
				System.out.print("Enter the code for the item: ");
				itemCode = keys.nextInt();
				keys.nextLine();
				invalid = true;
			}catch (InputMismatchException e) {
				System.out.println("Invalid Code");
				keys.nextLine();				
			}
		}while (!invalid); 
		return true;
	}
	
	/**
	 * 
	 * @return : return item code.
	 */
	public int getItemCode() {
		return itemCode;
	}
	
	/**
	 * 
	 * @param writer : accept formatter class object for formating text.
	 */
	public void outputItem(Formatter writer) {
		writer.format("%d\n%s\n%d\n%.2f\n%.2f", itemCode, itemName, itemQuantityInStock, itemCost, itemPrice);
	}
	
}
