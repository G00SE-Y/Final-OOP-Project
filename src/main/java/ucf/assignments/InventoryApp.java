package ucf.assignments;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class InventoryApp extends Application {

	// Active Memory
	public static Stage mainStage;
	public static MainAppController mainAppController;
	public static InventoryItem currentItem;

	public static ObservableList<InventoryItem> tableData;
	public static LinkedList<InventoryItem> items;


	public static void main(String[] args) {
		tableData = FXCollections.observableArrayList();
		items = new LinkedList<>();
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainAppGUI.fxml")));
			mainStage = primaryStage;

			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Inventory Manager");
			primaryStage.setResizable(false);
			primaryStage.show();

			FXMLLoader loader = new FXMLLoader();
			mainAppController = new MainAppController();
			loader.setController(mainAppController);

		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public static void setList(LinkedList<InventoryItem> newList) {
		items.clear();
		tableData.clear();

		items.addAll(newList);
		tableData.addAll(newList);

	}
}
