package ucf.assignments;

import javafx.collections.FXCollections;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class SearchTest {
	@Test
	void Search_Serial_Test() {
		InventoryApp.items = new LinkedList<>();
		InventoryApp.tableData = FXCollections.observableArrayList();

		InventoryItem abc = new InventoryItem("","abc","abc");
		InventoryItem a = new InventoryItem("","a","a");
		InventoryItem b = new InventoryItem("","b","b");
		InventoryItem c = new InventoryItem("","c","c");

		InventoryApp.items.add(abc);
		InventoryApp.items.add(a);
		InventoryApp.items.add(b);
		InventoryApp.items.add(c);

		MainAppController.searchSerial("a");
		assertTrue(InventoryApp.tableData.contains(abc) && InventoryApp.tableData.contains(a));

		MainAppController.searchSerial("b");
		assertTrue(InventoryApp.tableData.contains(abc) && InventoryApp.tableData.contains(b));

		MainAppController.searchSerial("c");
		assertTrue(InventoryApp.tableData.contains(abc) && InventoryApp.tableData.contains(c));

		MainAppController.searchSerial("d");
		assertEquals(0, InventoryApp.tableData.size());
	}

	@Test
	void Search_Name_Test() {
		InventoryApp.items = new LinkedList<>();
		InventoryApp.tableData = FXCollections.observableArrayList();

		InventoryItem abc = new InventoryItem("","abc","abc");
		InventoryItem a = new InventoryItem("","a","a");
		InventoryItem b = new InventoryItem("","b","b");
		InventoryItem c = new InventoryItem("","c","c");

		InventoryApp.items.add(abc);
		InventoryApp.items.add(a);
		InventoryApp.items.add(b);
		InventoryApp.items.add(c);

		MainAppController.searchName("a");
		assertTrue(InventoryApp.tableData.contains(abc) && InventoryApp.tableData.contains(a));

		MainAppController.searchName("b");
		assertTrue(InventoryApp.tableData.contains(abc) && InventoryApp.tableData.contains(b));

		MainAppController.searchName("c");
		assertTrue(InventoryApp.tableData.contains(abc) && InventoryApp.tableData.contains(c));

		MainAppController.searchName("d");
		assertEquals(0, InventoryApp.tableData.size());
	}

}