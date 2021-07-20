package ucf.assignments;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class MainAppController {

	@FXML ChoiceBox<String> SearchTypeChoiceBox;
	@FXML ChoiceBox<String> FileTypeChoiceBox;
	@FXML TextField SearchField;
	@FXML Label NameLabel;
	@FXML Label SerialLabel;
	@FXML Label PriceLabel;
	@FXML Label InventoryNameLabel;

	// Table View and columns
	@FXML private TableView<InventoryItem> table;
	@FXML private TableColumn<InventoryItem,String> priceColumn;
	@FXML private TableColumn<InventoryItem,String> serialColumn;
	@FXML private TableColumn<InventoryItem,String> nameColumn;


	@FXML
	public void initialize () {
		// Initialize the table display and item data
		table.setTableMenuButtonVisible(true);

		priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
		serialColumn.setCellValueFactory(cellData -> cellData.getValue().serialProperty());
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

		table.setItems(InventoryApp.tableData);

		table.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showItem(newValue)
		);

		// show blank item values
		showItem(null);

		// add items to the choice boxes
		SearchTypeChoiceBox.getItems().addAll("Price","Serial Number","Name");

		FileTypeChoiceBox.getItems().addAll(".txt",".json",".html");
	}

	public MainAppController() {}

	private void showItem(InventoryItem newValue) {
		// update the item display information to the information of the currently selected table item
		if (newValue == null) {
			PriceLabel.setText("");
			SerialLabel.setText("");
			NameLabel.setText("");
		}
		else {
			PriceLabel.setText(newValue.getPrice());
			SerialLabel.setText(newValue.getSerial());
			NameLabel.setText(newValue.getName());
		}
		InventoryApp.currentItem = newValue;
	}


	@FXML
	void AddButtonClicked() {
		System.out.println("Add");
		// Open Add Item dialog
		try {
			Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddItemGUI.fxml")));
			Scene AddScene = new Scene(root);
			Stage dialogStage = new Stage();

			dialogStage.setScene(AddScene);
			dialogStage.setTitle("Add Item");
			dialogStage.show();

			FXMLLoader loader = new FXMLLoader();
			AddItemController controller = new AddItemController();
			controller.setDialogStage(dialogStage);
			loader.setController(controller);

		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void EditButtonClicked() {
		System.out.println("Edit");
		// Open Edit Item dialog
		if(InventoryApp.currentItem == null) {
			return;
		}

		try {
			Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("EditItemGUI.fxml")));
			Scene AddScene = new Scene(root);
			Stage dialogStage = new Stage();

			dialogStage.setScene(AddScene);
			dialogStage.setTitle("Edit Item");
			dialogStage.show();

			FXMLLoader loader = new FXMLLoader();
			EditItemController controller = new EditItemController();
			controller.setDialogStage(dialogStage);
			loader.setController(controller);
		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void DeleteButtonClicked() {
		System.out.println("Delete");
		// Open Confirm Delete dialog
		try {
			Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DeleteItemGUI.fxml")));
		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void SaveButtonClicked() {
		System.out.println("Save to " + FileTypeChoiceBox.getValue());
		// Open File Explorer to allow user to select save location
		// Call method to save list to currently selected format in selected location
		if(FileTypeChoiceBox.getValue() == null)
			return;
	}

	@FXML
	void LoadButtonClicked() {
		System.out.println("Load");
		// Open File Explorer for the user to select a file to load
		// Call Parse from file function
		// update table and active memory
	}

	@FXML
	void SearchButtonClicked() {
		System.out.println("Search for '"+ this.SearchField.getText() +"' in the item " + "[field]" + "s");
		// Search for items containing the input search string within the selected field
		// update the current visible list to items containing the search string
	}
}
