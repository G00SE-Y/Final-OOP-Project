/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 first_name last_name
 */
package ucf.assignments;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Objects;

public class MainAppController {

	@FXML
	ChoiceBox<String> SearchTypeChoiceBox;
	@FXML
	TextField SearchField;
	@FXML
	Label NameLabel;
	@FXML
	Label SerialLabel;
	@FXML
	Label PriceLabel;
	@FXML
	Label InventoryNameLabel;

	// Table View and columns
	@FXML
	private TableView<InventoryItem> table;
	@FXML
	private TableColumn<InventoryItem, String> priceColumn;
	@FXML
	private TableColumn<InventoryItem, String> serialColumn;
	@FXML
	private TableColumn<InventoryItem, String> nameColumn;

	@FXML
	public void initialize() {
		// Initialize the table display and item data
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
		SearchTypeChoiceBox.getItems().addAll( "Serial Number", "Name");
	}

	public MainAppController() {
	}

	private void showItem(InventoryItem newValue) {
		// update the item display information to the information of the currently selected table item
		if (newValue == null) {
			PriceLabel.setText("");
			SerialLabel.setText("");
			NameLabel.setText("");
		} else {
			PriceLabel.setText(newValue.getPrice());
			SerialLabel.setText(newValue.getSerial());
			NameLabel.setText(newValue.getName());
		}
		InventoryApp.currentItem = newValue;
	}

	@FXML
	void AddButtonClicked() {
		// Open Add Item dialog
		try {
			Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddItemGUI.fxml")));
			Scene AddScene = new Scene(root);
			Stage dialogStage = new Stage();

			dialogStage.setScene(AddScene);
			dialogStage.setTitle("Add Item");
			dialogStage.setResizable(false);
			dialogStage.initModality(Modality.APPLICATION_MODAL);
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
		// Open Edit Item dialog
		if (InventoryApp.currentItem == null) {
			return;
		}

		try {
			Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("EditItemGUI.fxml")));
			Scene AddScene = new Scene(root);
			Stage dialogStage = new Stage();

			dialogStage.setScene(AddScene);
			dialogStage.setTitle("Edit Item");
			dialogStage.setResizable(false);
			dialogStage.initModality(Modality.APPLICATION_MODAL);
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
		// Delete selected item
		if(InventoryApp.currentItem == null)
			return;

		deleteSelectedItem();

		// clear selection
		table.getSelectionModel().clearSelection();

		showItem(null);

	}

	public static void deleteSelectedItem() {
		InventoryApp.items.remove(InventoryApp.currentItem);
		InventoryApp.tableData.remove(InventoryApp.currentItem);
	}

	@FXML
	void SearchButtonClicked() {
		// Search for items containing the input search string within the selected field
		// update the current visible list to items containing the search string
		if(this.SearchTypeChoiceBox.getValue() == null || this.SearchField.getText().equals("") || this.SearchField.getText() == null)
			return;
		String type = this.SearchTypeChoiceBox.getValue();

		switch (type) {
			case "Serial Number" -> searchSerial(this.SearchField.getText());

			case "Name" -> searchName(this.SearchField.getText());

			default -> System.out.println("What???");
		}
	}

	@FXML
	void clearButtonClicked() {
		// revert the changes made by a search and clear the text field
		InventoryApp.tableData.clear();
		InventoryApp.tableData.addAll(InventoryApp.items);
		this.SearchField.setText("");
	}

	public static void searchSerial(String text) {
		// update the table to only show items with the desired string
		InventoryApp.tableData.clear();

		for(InventoryItem item : InventoryApp.items) {
			if(item.getSerial().toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT)))
				InventoryApp.tableData.add(item);
		}
	}

	public static void searchName(String text) {
		// update the table to only show items with the desired string
		InventoryApp.tableData.clear();

		for(InventoryItem item : InventoryApp.items) {
			if(item.getName().toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT)))
				InventoryApp.tableData.add(item);
		}
	}

	@FXML
	void SaveButtonClicked() {
		// Open File Explorer to allow user to select save location
		// Call method to save list to currently selected format in selected location

		File saveFile = getSaveFile();
		if(saveFile == null)
			return;

		parseToFile(saveFile);


	}

	private File getSaveFile() {
		FileChooser fileChooser = new FileChooser();

		fileChooser.setTitle("Select Save Location");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("All Files", "*.*"),
				new FileChooser.ExtensionFilter(".txt", "*.txt"),
				new FileChooser.ExtensionFilter(".json", "*.json"),
				new FileChooser.ExtensionFilter(".html", "*.html")
		);

		return fileChooser.showSaveDialog(InventoryApp.mainStage);
	}

	private void parseToFile(File file) {
		if(file.toString().endsWith(".txt"))
			TSVParser.parseToFile(file, InventoryApp.items);

		else if(file.toString().endsWith(".json"))
			JsonParser.parseToFile(file, InventoryApp.items);

		else if(file.toString().endsWith(".html"))
			HtmlParser.parseToFile(file, InventoryApp.items);

	}

	@FXML
	void LoadButtonClicked() {
		// Open File Explorer for the user to select a file to load
		// Call Parse from file function
		// update table and active memory

		File loadFile = getLoadFile();
		System.out.println(loadFile);
		if(loadFile == null)
			return;
		loadFromFile(loadFile);


	}

	private void loadFromFile(File file) {
		LinkedList<InventoryItem> list = new LinkedList<>();

		if(file.toString().endsWith(".txt"))
			list.addAll(TSVParser.parseFromFile(file));

		else if(file.toString().endsWith(".json"))
			list.addAll(JsonParser.parseFromFile(file));

		else if(file.toString().endsWith(".html"))
			list.addAll(HtmlParser.parseFromFile(file));

		InventoryApp.setList(list);
	}

	private File getLoadFile() {
		FileChooser fileChooser = new FileChooser();

		fileChooser.setTitle("Select File To Load");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("All Files", "*.*"),
				new FileChooser.ExtensionFilter(".txt", "*.txt"),
				new FileChooser.ExtensionFilter(".json", "*.json"),
				new FileChooser.ExtensionFilter(".html", "*.html")
		);

		return fileChooser.showOpenDialog(InventoryApp.mainStage);
	}

}