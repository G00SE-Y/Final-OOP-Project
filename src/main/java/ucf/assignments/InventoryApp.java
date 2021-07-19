package ucf.assignments;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class InventoryApp extends Application {
	public static Stage mainStage;
	public static LinkedList<InventoryItem> currentList;
	public static InventoryItem currentItem;


	public static void main(String[] args) {
		currentList = new LinkedList<>();
		loadDummy();
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
			MainAppController controller = new MainAppController();
			loader.setController(controller);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void loadDummy() {
		// TODO
		// Load Dummy data for testing until data storage is implemented
		currentList.add(new InventoryItem("$00.00", "XXXXXXXXXX", "256_____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________"));
		currentList.add(new InventoryItem("$4.20", "6969696969", "The key to the Love Shack ;)"));
		currentList.add(new InventoryItem("$0.59", "1234567890", "Spearmint Flavored Gum"));

	}
}
