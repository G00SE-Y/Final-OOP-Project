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
			InventoryItem newItem = new InventoryItem(usd.format(Double.parseDouble(this.PriceField.getText())), this.SerialField.getText(), this.NameField.getText());
			InventoryApp.tableData.add(newItem);
			InventoryApp.items.add(newItem);

			dialogStage.close();
		}
	}

	@FXML
	public void CancelButtonClicked() {
		System.out.println("Cancelled");
		dialogStage.close();
	}

	private boolean isValid () {
		String errorMessage = "";

		// Check price validity
		errorMessage += validatePrice(errorMessage);

		// Check serial number validity
		errorMessage += validateSerial(errorMessage);

		// Check name validity
		errorMessage += validateName(errorMessage);

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

	private String validateName(String errorMessage) {
		if(NameField.getText() == null || NameField.getText().length() == 0) {
			errorMessage += "Please enter a valid Name.\n";
		}
		else if(!(NameField.getText().length() >= 2 && NameField.getText().length() <=256)) {
			errorMessage += "Name must be between 2 and 256 characters long!\n";
		}

		if(Objects.requireNonNull(NameField.getText()).contains("-"))
			errorMessage += "Name must not contain Tabs ('-')!\n";
		return errorMessage;
	}

	private String validateSerial(String errorMessage) {
		if(SerialField.getText() == null || SerialField.getText().length() == 0) {
			errorMessage += "Please enter a valid Serial Number.\n";
		}
		else if(SerialField.getText().length() != 10) {
			errorMessage += "Serial Number must be 10 characters!\n";
		}
		else if(!Pattern.matches("[a-zA-Z0-9]+",SerialField.getText())) {
			errorMessage += "Serial Number must only contain letters and numbers!\n";
		}

		// Checking for duplicate Serial Number
		for(InventoryItem item : InventoryApp.tableData) {
			if(item.getSerial().equalsIgnoreCase(SerialField.getText())) {
				errorMessage += "Serial Number must be unique!\n";
				break;
			}
		}
		return errorMessage;
	}

	private String validatePrice(String errorMessage) {
		try {
			Double.parseDouble(Objects.requireNonNull(this.PriceField.getText().replace(",","")));

			if ( PriceField.getText().length() == 0) {
				throw new NumberFormatException();
			}
		}
		catch (NumberFormatException | NullPointerException e) {
			errorMessage += "Please enter a valid Price.\n";
		}
		return errorMessage;
	}
}
