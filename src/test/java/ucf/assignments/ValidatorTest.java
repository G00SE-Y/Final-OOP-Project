package ucf.assignments;

import javafx.collections.FXCollections;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {
	
	@Test
	void Invalid_Name_Test() {

		String error = Validator.validateName("1");
		assertTrue(error.contentEquals("Name must be between 2 and 256 characters long!\n"));

		error = Validator.validateName("257..............................................................................................................................................................................................................................................................");
		assertTrue(error.contentEquals("Name must be between 2 and 256 characters long!\n"));

		error = Validator.validateName("");
		assertTrue(error.contentEquals("Please enter a valid Name.\n"));

		error = Validator.validateName(null);
		assertTrue(error.contentEquals("Please enter a valid Name.\n"));

		error = Validator.validateName("2-");
		assertTrue(error.contentEquals("Name must not contain Tabs ('-')!\n"));

		error = Validator.validateName("Name");
		assertEquals(0, error.length());
	}

	@Test
	void Invalid_Serial_Test() {
		InventoryApp.tableData = FXCollections.observableArrayList();

		String error = Validator.validateSerial(null);
		assertTrue(error.contentEquals("Please enter a valid Serial Number.\n"));

		error = Validator.validateSerial("");
		assertTrue(error.contentEquals("Please enter a valid Serial Number.\n"));

		error = Validator.validateSerial("1");
		assertTrue(error.contentEquals("Serial Number must be 10 characters!\n"));

		error = Validator.validateSerial("!@#$%^&*()");
		assertTrue(error.contentEquals("Serial Number must only contain letters and numbers!\n"));

		InventoryApp.tableData.add(new InventoryItem("","XXXXXXXXXX",""));

		error = Validator.validateSerial("XXXXXXXXXX");
		assertTrue(error.contentEquals("Serial Number must be unique!\n"));

		error = Validator.validateSerial("XXXXXXXXXA");
		assertEquals(0, error.length());
	}

	@Test
	void Invalid_Price_Test() {

		String error = Validator.validatePrice(null);
		assertTrue(error.contentEquals("Please enter a valid Price.\n"));

		error = Validator.validatePrice("");
		assertTrue(error.contentEquals("Please enter a valid Price.\n"));

		error = Validator.validatePrice("a");
		assertTrue(error.contentEquals("Please enter a valid Price.\n"));

		error = Validator.validatePrice("1,000.00");
		assertEquals(0, error.length());
	}

}