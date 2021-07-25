/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 first_name last_name
 */
package ucf.assignments;

import java.util.Objects;
import java.util.regex.Pattern;

public class Validator {
	public static String validateName(String name) {
		// check if string is 2-256 characters
		// check if string contains tabs

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
		// check if string is 10 characters
		// check if string is alphanumeric
		// check if string matches any serial numbers in active memory

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
		// check if string is a valid floating point number

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
