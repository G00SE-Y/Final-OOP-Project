package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainAppController {

	@FXML ChoiceBox<String> SearchType;
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

		ObservableList<InventoryItem> tableData = FXCollections.observableArrayList();
		tableData.addAll(InventoryApp.currentList);
		table.setItems(tableData);

		table.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showItem(newValue)
		);

		showItem(null);
	}

	public MainAppController() {}

	private void showItem(InventoryItem newValue) {
		// update the item display information to the information of the currently selected table item
		if (newValue == null) {
			PriceLabel.setText("null");
			SerialLabel.setText("null");
			NameLabel.setText("null");
		}
		else {
			PriceLabel.setText(newValue.getPrice());
			SerialLabel.setText(newValue.getSerial());
			NameLabel.setText(newValue.getName());
		}
		InventoryApp.currentItem = newValue;
	}

	@FXML
	void AddButtonClicked(ActionEvent actionEvent) {
		System.out.println("Add");
		// Open Add Item dialog
	}

	@FXML
	void EditButtonClicked(ActionEvent actionEvent) {
		System.out.println("Edit");
		// Open Edit Item dialog
	}

	@FXML
	void DeleteButtonClicked(ActionEvent actionEvent) {
		System.out.println("Delete");
		// Open Confirm Delete dialog
	}

	@FXML
	void SaveButtonClicked(ActionEvent actionEvent) {
		System.out.println("Save");
		// Open File Explorer to allow user to select save location
		// Call method to save list to currently selected format in selected location
	}

	@FXML
	void LoadButtonClicked(ActionEvent actionEvent) {
		System.out.println("Load");
		// Open File Explorer for the user to select a file to load
		// Call Parse from file function
		// update table and active memory
	}

	@FXML
	void SearchButtonClicked(ActionEvent actionEvent) {
		System.out.println("Search for '"+ this.SearchField.getText() +"' in the item " + "[field]" + "s");
		// Search for items containing the input search string within the selected field
		// update the current visible list to items containing the search string
	}
}
