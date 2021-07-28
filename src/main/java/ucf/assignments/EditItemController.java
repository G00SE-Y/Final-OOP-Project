/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 first_name last_name
 */
package ucf.assignments;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.text.NumberFormat;

public class EditItemController {
	private static Stage dialogStage;

	@FXML private TextField PriceField;
	@FXML private TextField SerialField;
	@FXML private TextArea NameField;

	@FXML
	public void initialize () {
		// initialize text fields with item's current information
		PriceField.setText(InventoryApp.currentItem.getPrice().replace("$",""));
		SerialField.setText(InventoryApp.currentItem.getSerial());
		NameField.setText(InventoryApp.currentItem.getName());
	}

	public void setDialogStage(Stage stage) {
		dialogStage = stage;
	}

	@FXML
	public void SubmitButtonClicked() {
		// check if user input is valid
		// change selected item's information to new information

		if(isValid()) {
			NumberFormat usd = NumberFormat.getCurrencyInstance();
			EditItem(usd.format(Double.parseDouble(this.PriceField.getText())),this.SerialField.getText(),this.NameField.getText());

			InventoryApp.currentItem = null;
			dialogStage.close();
		}
	}

	public void EditItem(String newPrice,String newSerial,String newName) {
		InventoryApp.currentItem.setPrice(newPrice);
		InventoryApp.currentItem.setSerial(newSerial);
		InventoryApp.currentItem.setName(newName);
	}

	@FXML
	public void CancelButtonClicked() {
		// close the add item dialog
		// reset table selection
		InventoryApp.currentItem = null;
		dialogStage.close();
	}

	private boolean isValid () {
		// check if user input is valid
			// if user input is not valid, open alert dialog requesting that the user fix incorrect input
			// if input is valid, return true
		String errorMessage = "";

		// Check price validity
		errorMessage += Validator.validatePrice(errorMessage);

		// Check serial number validity
		errorMessage += Validator.validateSerial(errorMessage);

		// Check name validity
		errorMessage += Validator.validateName(errorMessage);

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
		alert.initOwner(dialogStage);
		alert.setTitle("Invalid Input");
		alert.setHeaderText("Please correct invalid fields");
		alert.setContentText(errorMessage);

		alert.showAndWait();
	}


}
