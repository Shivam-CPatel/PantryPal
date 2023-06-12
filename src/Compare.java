import java.util.Comparator;

public class Compare implements Comparator<FoodItem> {

	@Override
	public int compare(FoodItem f1, FoodItem f2) {
		return Integer.valueOf(f1.getItemCode()).compareTo(f2.getItemCode());
	}

}
