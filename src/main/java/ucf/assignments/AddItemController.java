package ucf.assignments;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.text.NumberFormat;
import java.util.Objects;
import java.util.regex.Pattern;

public class AddItemController {
	private static Stage dialogStage;

	@FXML private TextArea NameField;
	@FXML private TextField PriceField;
	@FXML private TextField SerialField;

	public void setDialogStage(Stage stage) {
		dialogStage = stage;
	}

	@FXML
	public void AddButtonClicked() {
		if(isValid()) {
			NumberFormat usd = NumberFormat.getCurrencyInstance();
			AddItem(new InventoryItem(usd.format(Double.parseDouble(this.PriceField.getText())), this.SerialField.getText(), this.NameField.getText()));

			dialogStage.close();
		}
	}

	public void AddItem(InventoryItem newItem) {

		InventoryApp.tableData.add(newItem);
		InventoryApp.items.add(newItem);
	}

	@FXML
	public void CancelButtonClicked() {
		System.out.println("Cancelled");
		dialogStage.close();
	}

	private boolean isValid () {
		String errorMessage = "";

		// Check price validity
		errorMessage += validatePrice(this.PriceField.getText());

		// Check serial number validity
		errorMessage += validateSerial(this.SerialField.getText());

		// Check name validity
		errorMessage += validateName(this.NameField.getText());

		if (errorMessage.length() == 0) {
			return true;
		}
		else {
			openAlert(errorMessage);
			return false;
		}
	}

	private void openAlert(String errorMessage) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.initOwner(InventoryApp.mainStage);
		alert.setTitle("Invalid Input");
		alert.setHeaderText("Please correct invalid fields");
		alert.setContentText(errorMessage);

		alert.showAndWait();
	}

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
			if (price.length() == 0 || price == null) {
				throw new NumberFormatException();
			}

			Double.parseDouble(price.replace(",",""));

		}
		catch (NumberFormatException e) {
			errorMessage += "Please enter a valid Price.\n";
		}
		return errorMessage;
	}
}
