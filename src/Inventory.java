import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Formatter;

public class Inventory {
	private ArrayList<FoodItem> invent =  new ArrayList<FoodItem>();
//	private FoodItem[] invent;
	private int numsItems;
	
	/**
	 * @param numsItems : when the program starts running then it assigns array size to 20.
	 */
	public Inventory(int numsItems) {
		invent =  new ArrayList<FoodItem>(numsItems);
	}
	
	/**
	 * @return this method print out the array
	 */
	@Override
	public String toString() {
		Comparator<FoodItem> comperator = new Compare ();
		Collections.sort(invent, comperator);
 		invent.forEach(System.out::println);
		return null;
	}
	
	private int updateHelp = 0;
	private int addHelp = 0;
	
	/**
	 * @param item : to access this method, object of FoodItem class is required.
	 * @return : Returns the index of a FoodItem in the inventory array with the same itemCode as the FoodItem object in the parameter list, else returns -1
	 */
	public int alreadyExists(FoodItem item) {
		int i = 0;
		try {			
			for (i = 0; i< invent.size(); i++) {
				
				if (updateHelp == 1) {
					if (item.isEqual(invent.get(i))) {
						return i;
					}
				}
			
				if (addHelp == 1) {
					if (item.isEqual(invent.get(i))) {
						if (i == numsItems) {
							return -1;
						}
						System.out.println("Item code already exist.");
						return i;
					}
				}
			}
		}catch (NullPointerException e) {
			System.out.println("Item code not found in Inventory...");
		}	
		return -1;
	}
	
	private String adding;
	private int firstTime = 0;
	
	/**
	 * @param keys : to access this method scanner is required 
	 * @return : returns true if item successfully added or return false it not
	 * @param fromFile : accept true or false.
	 */
	public boolean addItem(Scanner keys, boolean fromFile) {
		addHelp = 1;
		if (numsItems>-1&&numsItems<=(invent.size())){
			System.out.print("Do you wish to add a fruit(f), vegetable(v), preserve(p) or an ice cream(i) ");
			adding = keys.next();
			while (adding != null) {
				
				while (!adding.equals("f") && !adding.equals("v") && !adding.equals("p")
						&& !adding.equals("i")) {
					System.out.println("Invalid entry.");				
					System.out.print("Do you wish to add a fruit(f), vegetable(v), preserve(p) or an ice cream(i) ");
					adding = keys.next();					
				}
				if ("f".equalsIgnoreCase(adding)) {									// If statement for checking user entered string is correct or not.
					Fruit fru = new Fruit();								// If it's correct then call Fruit class using array reference
					fru.inputCode(keys, fromFile);								// This call method from FoodItem class to enter input code.
					while (firstTime != 0) {										// This while loop check if the user entered item code exits or not
						if (this.alreadyExists(fru) == -1) {			// If user enter item code don't exist then break the loop
							break;
						}
						fru.inputCode(keys, fromFile);
					}
					fru.addItem(keys, fromFile);								// Ask user for more information about the product.
					invent.add(fru);
				}else if ("v".equalsIgnoreCase(adding)) {							// If statement for checking user entered string is correct or not.
					Vegetable veg = new Vegetable();							// If it's correct then call Vegetable class using array reference
					veg.inputCode(keys, fromFile);								// This call method from FoodItem class to enter input code.
					while (firstTime != 0) {										// This while loop check if the user entered item code exits or not
						if (this.alreadyExists(veg) == -1 ) {			// If user enter item code don't exist then break the loop
							break;
						}
						veg.inputCode(keys, fromFile);
					}
					veg.addItem(keys, fromFile);								// Ask user for more information about the product.
					invent.add(veg);
				}else if ("p".equalsIgnoreCase(adding)) {
					Preserve pre = new Preserve();								// If it's correct then call Preserve class using array reference
					pre.inputCode(keys, fromFile);								// This call method from FoodItem class to enter input code.
					while (firstTime != 0) {										// This while loop check if the user entered item code exits or not
						if (this.alreadyExists(pre) == -1) {			// If user enter item code don't exist then break the loop
							break;
						}
						pre.inputCode(keys, fromFile);
					}
					pre.addItem(keys, fromFile);								// Ask user for more information about the product.
					invent.add(pre);
				}else if ("i".equalsIgnoreCase(adding)) {
					IceCream ice = new IceCream();								// If it's correct then call Ice cream class using array reference
					ice.inputCode(keys, fromFile);								// This call method from FoodItem class to enter input code.
					while (firstTime != 0) {										// This while loop check if the user entered item code exits or not
						if (this.alreadyExists(ice) == -1) {			// If user enter item code don't exist then break the loop
							break;
						}
						ice.inputCode(keys, fromFile);
					}
					ice.addItem(keys, fromFile);								// Ask user for more information about the product.
					invent.add(ice);
				}
				
				firstTime = 1;
				addHelp = 0;
				numsItems++;														// Increment array index number
				return true;
			}
		}
		return false;
	}
	
	private int indices = 0;														// Indices variable used in update stock methods.
	private int amount = 0;															// amount variable used in update stock methods.
	
	/**
	 * @param keys : accept scanner class
	 * @param quans	: takes FoodItem class object
	 * @return : return -1 if something went wrong, else if return 0.
	 */
	public int BuyMethod(Scanner keys, FoodItem quans) {
		if (numsItems == 0) {														// If the inventory is empty then give error message and break.
			System.out.println("ERROR... No Items to buy");
			return -1;
		}
		quans.inputCode(keys, false);														// If it's not empty then ask user for item code
		indices = this.alreadyExists(quans);										// Check the item code exist or not
		if (indices == -1) {														// If item code is not in existence then print an error and get user back to main menu
			System.out.println("ERROR... No Items to buy");
			return -1;
		}
		System.out.print("Enter valid qunatity to buy: ");							// If item code exist then ask user to enter quantity of the product
		if (keys.hasNextInt()) {													// If user enter the amount then check the amount is valid or not.
			amount = keys.nextInt();												// If it's not valid then print error message and get user back to main menu
			keys.nextLine();
			if(amount <= 0) {
				System.out.println("Invalid quantity...");
				System.out.println("ERROR... No Items to buy");
				return -1;
			}
			amount *= 1;
		}else {
			System.out.println("Invalid quantity...");
			System.out.println("ERROR... No Items to buy");
		}
		boolean updatedInventory = invent.get(indices).updateItem(amount);				// update the amount in an inventory 
		if (updatedInventory) {
			return 0; 
		}
		return -1;
	}
	
	/**
	 * @param keys : accept scanner class
	 * @param quans	: takes FoodItem class object
	 * @return : return -1 if something went wrong, else if return 0.
	 */
	public int SellMethod(Scanner keys, FoodItem quans) {
		if (numsItems == 0) {														// If the inventory is empty then give error message and break.
			System.out.println("ERROR... No Items to sell");
			return -1;
		}
		quans.inputCode(keys, false);														// If it's not empty then ask user for item code
		indices = this.alreadyExists(quans);										// Check the item code exist or not
		if (indices == -1) {														// If item code is not in existence then print an error and get user back to main menu
			System.out.println("ERROR... No Items to sell");
			return -1;
		}
		System.out.print("Enter valid qunatity to sell: ");							// If item code exist then ask user to enter quantity of the product
		if (keys.hasNextInt()) {													// If user enter the amount then check the amount is valid or not.
			amount = keys.nextInt();												// If it's not valid then print error message and get user back to main menu
			keys.nextLine();
			if(amount <= 0) {
				System.out.println("ERROR... No Items to sell");
				return -1;
			}
			amount = amount * -1;
		}else {
			System.out.println("ERROR... No Items to sell");
		}
		boolean updatedInventory = invent.get(indices).updateItem(amount);				// update the amount in an inventory 
		if (updatedInventory) {
			return 0;
		}
		System.out.println("Not enough Stocks is available in an inventory. SORRY");		// Print error message if the amount user entered is more than available
		return -1;
	}
	
	/**
	 * @param keys : scanner class object for allowing user to enter quantity to buy or sell items and to write items code as well
	 * @param buyOrSell : if this boolean is false that means user want to buy an item and if it's true then user wants to sell item
	 * @return : if user something went wrong the it return false or if all goes good then it returns true
	 */
	public boolean updateQuantity (Scanner keys, boolean buyOrSell) {
		FoodItem quans = new FoodItem();
		updateHelp = 1;
		if (!buyOrSell) {
			int works = this.BuyMethod(keys, quans);
			if (works == -1) {
				return false;
			}
		}else if (buyOrSell) {
			int works = this.SellMethod(keys, quans);
			if (works == -1) {
				return false;
			}
		}
		updateHelp = 0;
		return false;
	}
	
	/**
	 * 
	 * @param keys : accept scanner class object.
	 */
	public void searchForItem(Scanner keys) {
		int min = 0;
		int max = invent.size();
		
		try {			
			FoodItem search = new FoodItem();
			search.inputCode(keys, false);
			int x = search.getItemCode();
			while (min <=  max) {
				int mid = min + ((max - min)/2);
				if (invent.get(mid).getItemCode() == x) {
					System.out.println(invent.get(mid));
					System.out.println("");
					break;
				}else if (invent.get(mid).getItemCode() < x) {
					min = mid + 1;
				}else if (invent.get(mid).getItemCode() > x){
					max = mid - 1;
				}
			}
			System.out.println("Item code not found in Inventory...");
		}catch (NullPointerException e) {
			System.out.println("Inventory is empty...");
		}catch (IndexOutOfBoundsException e) {
			System.out.println("Item code not found in Inventory...");
		}		
	}
	
	/**
	 * 
	 * @param keys : accept scannner class object.
	 */
	public void saveToFile(Scanner keys) {
		if (numsItems <= 0) {
			System.out.println("Inventory is empty.");
		}else {
			try {
				System.out.print("Enter the filename to read from: ");
				String fileName = keys.next();
				Formatter fm;
			    fm = new Formatter(new FileOutputStream(fileName));
				for (int i = 0; i<invent.size(); i++) {
					invent.get(i).outputItem(fm);
				}
				fm.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("File Not Found, ignoring...");
			}													//file writer to give a path to file
		}
	}
	
	/**
	 * 
	 * @param keys : accept scanner class object.
	 */
	public void readFromFile(Scanner keys) {
		try {
			System.out.print("Enter the filename to read from: ");
			String fileName = keys.next();
			FileReader in = new FileReader(fileName);													//file reader to get file path
			keys = new Scanner(in);
			int totatlyFinish = 0;
			while (keys.hasNext()) {
				updateHelp = 1;
//				String line = keys.nextLine();
				String type = keys.next();
				while (type.equals("f")) {
					Fruit fru = new Fruit();
					String code = keys.next();
					fru.itemCode = Integer.parseInt(code);
					while (firstTime != 0) {						
						if (this.alreadyExists(fru) != -1) {
							System.out.println("Item Code "+ fru.itemCode +" already exist in an inventory.");
							totatlyFinish = 1;
							updateHelp = 0;
							break;
						}
					}
					if(totatlyFinish == 1) {
						break;
					}
					
					keys.nextLine();
					fru.itemName = keys.nextLine();
					
					String quantity = keys.next();
					fru.itemQuantityInStock = Integer.parseInt(quantity);
					
					String cost = keys.next();
					fru.itemCost = Float.parseFloat(cost);
					
					String price = keys.next();
					fru.itemPrice = Float.parseFloat(price);
					
					keys.nextLine();
					fru.orchardName = keys.nextLine();
					invent.add(fru);
					numsItems++;
					break;
				}
				while (type.equals("v")) {
					Vegetable veg = new Vegetable();
					String code = keys.next();
					veg.itemCode = Integer.parseInt(code);
					while (firstTime != 0) {						
						if (this.alreadyExists(veg) != -1) {
							System.out.println("Item Code "+ veg.itemCode+" already exist in an inventory.");
							totatlyFinish = 1;
							updateHelp = 0;
							break;
						}
					}
					if(totatlyFinish == 1) {
						break;
					}
					
					keys.nextLine();
					veg.itemName = keys.nextLine();
					
					String quantity = keys.next();
					veg.itemQuantityInStock = Integer.parseInt(quantity);
					
					String cost = keys.next();
					veg.itemCost = Float.parseFloat(cost);
					
					String price = keys.next();
					veg.itemPrice = Float.parseFloat(price);
					
					keys.nextLine();
					veg.farmName = keys.nextLine();
					invent.add(veg);						
					numsItems++;
					break;
				}
				while (type.equals("p")) {
					Preserve pre = new Preserve();
					String code = keys.next();
					pre.itemCode = Integer.parseInt(code);
					while (firstTime != 0) {	
						if (this.alreadyExists(pre) != -1) {
							System.out.println("Item Code "+pre.itemCode+" already exist in an inventory.");
							totatlyFinish = 1;
							updateHelp = 0;
							break;
						}
					}
					if(totatlyFinish == 1) {
						break;
					}
					
					keys.nextLine();
					pre.itemName = keys.nextLine();
					
					String quantity = keys.next();
					pre.itemQuantityInStock = Integer.parseInt(quantity);
					
					String cost = keys.next();
					pre.itemCost = Float.parseFloat(cost);
					
					String price = keys.next();
					pre.itemPrice = Float.parseFloat(price);
					
					String size = keys.next();
					pre.jarSize = Integer.parseInt(size);
					invent.add(pre);						
					numsItems++;
					break;
				}
				while (type.equals("i")) {
					IceCream ice = new IceCream();
					String code = keys.next();
					ice.itemCode = Integer.parseInt(code);
					while (firstTime != 0) {
						if (this.alreadyExists(ice) != -1) {
							System.out.println("Item Code "+ice.itemCode+" already exist in an inventory.");
							totatlyFinish = 1;
							updateHelp = 0;
							break;
						}
					}
					if(totatlyFinish == 1) {
						break;
					}
					
					keys.nextLine();
					ice.itemName = keys.nextLine();
					
					String quantity = keys.next();
					ice.itemQuantityInStock = Integer.parseInt(quantity);
					
					String cost = keys.next();
					ice.itemCost = Float.parseFloat(cost);
					
					String price = keys.next();
					ice.itemPrice = Float.parseFloat(price);
					
					keys.nextLine();
					ice.companyName = keys.nextLine();
					invent.add(ice);
					numsItems++;
					break;
				}
				if(totatlyFinish == 1) {
					break;
				}
				updateHelp = 0;
			}
			firstTime = 1;
			in.close();
		}catch (IOException e) {
			System.out.println("File Not Found, ignoring...");
		}
	}
		
	
}
