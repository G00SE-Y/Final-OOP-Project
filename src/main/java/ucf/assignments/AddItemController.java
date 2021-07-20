package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.text.NumberFormat;

public class AddItemController {
	private static Stage dialogStage;

	@FXML public TextArea NameField;
	@FXML public TextField PriceField;
	@FXML public TextField SerialField;



	public void setDialogStage(Stage stage) {
		dialogStage = stage;
	}

	@FXML
	public void AddButtonClicked(ActionEvent actionEvent) {
		NumberFormat usd = NumberFormat.getCurrencyInstance();
		System.out.println(
				"Add new Item\n" +
				"Price: " + usd.format(Double.parseDouble(this.PriceField.getText())) + "\n" +
				"SerialNumber: " + this.SerialField.getText() + "\n" +
				"Name: " + this.NameField.getText()
		);
		InventoryApp.tableData.add(new InventoryItem(usd.format(Double.parseDouble(this.PriceField.getText())), this.SerialField.getText(), this.NameField.getText()));

		dialogStage.close();
	}

	@FXML
	public void CancelButtonClicked(ActionEvent actionEvent) {
		System.out.println("Cancelled");
		dialogStage.close();
	}
}
