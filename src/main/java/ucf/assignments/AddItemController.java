/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Ethan Woollet
 */
package ucf.assignments;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.text.NumberFormat;

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
		// check if user input is valid
		// add item

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
		// close the add item dialog
		dialogStage.close();
	}

	private boolean isValid () {
		// check if user input is valid
			// if user input is not valid, open alert dialog requesting that the user fix incorrect input
			// if input is valid, return true

		String errorMessage = "";

		// Check price validity
		errorMessage = Validator.validatePrice(errorMessage);

		// Check serial number validity
		errorMessage = Validator.validateSerial(errorMessage);

		// Check name validity
		errorMessage = Validator.validateName(errorMessage);

		if (errorMessage.length() == 0) {
			return true;
		}
		else {
			openAlert(errorMessage);
			return false;
		}
	}

	private void openAlert(String errorMessage) {
		// open an alert dialog with the error message given

		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.initOwner(InventoryApp.mainStage);
		alert.setTitle("Invalid Input");
		alert.setHeaderText("Please correct invalid fields");
		alert.setContentText(errorMessage);

		alert.showAndWait();
	}
}
