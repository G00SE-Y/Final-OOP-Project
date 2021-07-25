package ucf.assignments;

import javafx.collections.FXCollections;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class ListEditingTest {
	@Test
	void Add_Item_Test() {
		InventoryApp.tableData = FXCollections.observableArrayList();
		InventoryApp.items = new LinkedList<>();

		InventoryItem testItem = new InventoryItem("1,000.00","XXXXXXXXXX","Name");
		AddItemController controller = new AddItemController();
		controller.AddItem(testItem);

		assertTrue(InventoryApp.tableData.contains(testItem));
		assertTrue(InventoryApp.items.contains(testItem));
	}

	@Test
	void Edit_Item_Test() {
		InventoryApp.currentItem = new InventoryItem("1,000.00","XXXXXXXXXX","Name");


		EditItemController controller = new EditItemController();
		controller.EditItem("$2,000.00","AAAAAAAAAA","New Name");

		assertTrue(InventoryApp.currentItem.getPrice().contentEquals("$2,000.00"));
		assertTrue(InventoryApp.currentItem.getSerial().contentEquals("AAAAAAAAAA"));
		assertTrue(InventoryApp.currentItem.getName().contentEquals("New Name"));
	}

	@Test
	void Delete_Item_Test () {
		InventoryApp.tableData = FXCollections.observableArrayList();
		InventoryApp.items = new LinkedList<>();

		InventoryItem testItem = new InventoryItem("1,000.00","XXXXXXXXXX","Name");

		InventoryApp.tableData.add(testItem);
		InventoryApp.items.add(testItem);

		InventoryApp.currentItem = testItem;

		MainAppController.deleteSelectedItem();

		assertFalse(InventoryApp.tableData.contains(testItem));
		assertFalse(InventoryApp.items.contains(testItem));
	}
}