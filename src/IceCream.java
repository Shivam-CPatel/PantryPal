import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class IceCream extends FoodItem {
	
	protected String companyName;
	
	/**
	 * @author Owner : this the default constructor for IceCream class
	 */
	public IceCream() {
		super();
		companyName = null;
	}
	
	/**
	 * @return : this toString method returns formated string with ice cream company name
	 */
	@Override
	public String toString() {
		String output;
		output = String.format("Item: %2d %2s %2d, price: $%2.2f cost: %2.2f, company name: %2s %n", itemCode, itemName, itemQuantityInStock, itemPrice, itemCost, companyName);
		return output;
	}
	
	/**
	 * 
	 * @param keys : this method accept scanner class object and also implement addItem method from super class
	 * @return : if everything goes well then this method returns true else false.
	 * @param fromFile : accept true or false
	 */
	@Override
	public boolean addItem (Scanner keys, boolean fromFile) {
		super.addItem(keys, fromFile);
		try {
			keys.nextLine();
			System.out.print("Enter the name of the ice-cream company: ");
			companyName = keys.nextLine();
		}catch (InputMismatchException e) {
			System.out.println("Invalid input");
			keys.nextInt();
		}
		return false;
	}
	
	/**
	 * 
	 * @param writer : accept formatter class object for formating text.
	 */
	public void outputItem(Formatter writer) {
		writer.format("i\n");
		super.outputItem(writer);
		writer.format("\n%s\n", companyName);
	}
	
}
