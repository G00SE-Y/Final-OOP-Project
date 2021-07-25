package ucf.assignments;

import java.util.Objects;
import java.util.regex.Pattern;

public class Validator {
	public static String validateName(String name) {
		String errorMessage = "";
		if(name == null || name.length() == 0) {
			return "Please enter a valid Name.\n";
		}
		else if(!(name.length() >= 2 && name.length() <=256)) {
			errorMessage += "Name must be between 2 and 256 characters long!\n";
		}

		try {
			if(Objects.requireNonNull(name).contains("-"))
				throw new NullPointerException();
		}
		catch (NullPointerException e) {
			errorMessage += "Name must not contain Tabs ('-')!\n";
		}

		return errorMessage;
	}

	public static String validateSerial(String serial) {
		String errorMessage = "";
		if(serial == null || serial.length() == 0) {
			return "Please enter a valid Serial Number.\n";
		}
		else if(serial.length() != 10) {
			errorMessage += "Serial Number must be 10 characters!\n";
		}
		else if(!Pattern.matches("[a-zA-Z0-9]+",serial)) {
			errorMessage += "Serial Number must only contain letters and numbers!\n";
		}

		// Checking for duplicate Serial Number
		for(InventoryItem item : InventoryApp.tableData) {
			if(item.getSerial().equalsIgnoreCase(serial)) {
				errorMessage += "Serial Number must be unique!\n";
				break;
			}
		}
		return errorMessage;
	}

	public static String validatePrice(String price) {
		String errorMessage = "";
		try {
			Double.parseDouble(Objects.requireNonNull(price.replace(",","")));

			if (price.length() == 0) {
				throw new NumberFormatException();
			}
		}
		catch (NumberFormatException | NullPointerException e) {
			errorMessage += "Please enter a valid Price.\n";
		}
		return errorMessage;
	}
}
