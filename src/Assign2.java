     import java.util.InputMismatchException;
import java.util.Scanner;

public class Assign2 {
	public static int option;															//Option variable used in menu.
	/**
	 * @author Owner : this is scanner class object.
	 */
	public static Scanner keys = new Scanner(System.in);								//Scanner class object.

	/**
	 * @author Owner : this method provide all the options to the user.
	 */
	public static void menu() {															//Menu method to show user what they can do and ask what they want to do.
		System.out.println("");
		System.out.println("Select your option in the menu below:");
		System.out.println("1. Add Item to Inventory");
		System.out.println("2. Display Current Inventory ");
		System.out.println("3. Buy Item(s)");
		System.out.println("4. Sell Item(s)");
		System.out.println("5. Search for Item");
		System.out.println("6. Save Inventory to File");
		System.out.println("7. Read Inventory from File");
		System.out.println("8. Exit");
		System.out.print("Select you choice: ");
		option = keys.nextInt();
	}
	
	/**
	 * 
	 * @param args : this the main method.
	 */
	public static void main(String[] args) {
		Inventory IV = new Inventory(20);
		while (option > -1) {															//While loop to show user menu again after completing one task.
			try {																		//try-catch to handle InputMismatch exception.
				menu();																	//Calling menu method
				switch (option) {														//Switch statement to do task user want to do
				case 1:
					IV.addItem(keys, true);
					break;
				case 2:	
					System.out.println("Inventory:");
					IV.toString();
					break;
				case 3:	
					IV.updateQuantity(keys, true);
					break;
				case 4:
					IV.updateQuantity(keys, true);
					break;
				case 5:
					IV.searchForItem(keys);
					break;
				case 6:
					IV.saveToFile(keys);
					break;
				case 7:
					IV.readFromFile(keys);
					break;
				case 8:
					break;
				default:																//default message is used when user entered any out put other than cases.
					System.out.println("Invalid input, enter option 1-4");
					break;
				}
				if (option == 8) {														//Case 4 print out Exit message to user.
					System.out.println("Exiting...... \nProgram by Shivam Patel.");
					break;
				}
			}catch(InputMismatchException e) {											//Catch statement
				System.out.println("Invalid input, enter option 1-4");
				keys.next();															//keys.next used for showing menu option again to user
			}
		}
		keys.close();                                  									//closing Scanner
	}
}
