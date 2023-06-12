import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Preserve extends FoodItem{
	
	protected int jarSize;
	
	/**
	 * @author Owner : this the default constructor for Preserve class
	 */
	public Preserve() {
		super();
		jarSize = 0;
	}
	
	/**
	 * @return : this toString method returns formated string with jar size.
	 */
	@Override
	public String toString() {
		String output;
		output = String.format("Item: %2d %2s %2d, price: $%2.2f cost: %2.2f, size: %2d %n", itemCode, itemName, itemQuantityInStock, itemPrice, itemCost, jarSize);
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
		do {			
			try {			
				System.out.print("Enter the size of jar in milli-litres: ");
				jarSize = keys.nextInt();
				if (jarSize <= 0) {
					System.out.println("Invalid size. Size cannot be negative");
				}
			}catch (InputMismatchException e) {
				System.out.println("Invalid Code");
				keys.nextLine();
			}
		}while (jarSize == 0 || jarSize < 0);
		return false;
	}
	
	/**
	 * 
	 * @param writer : accept formatter class object for formating text.
	 */
	public void outputItem(Formatter writer) {
		writer.format("p\n");
		super.outputItem(writer);
		writer.format("\n%d\n", jarSize);
	}
	
}
