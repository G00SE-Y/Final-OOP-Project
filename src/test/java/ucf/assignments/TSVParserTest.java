package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class TSVParserTest {

	@Test
	void Parse_To_File_Test() {
		LinkedList<InventoryItem> items = new LinkedList<>();
		items.add(new InventoryItem("","abc","abc"));
		items.add(new InventoryItem("","a","a"));
		items.add(new InventoryItem("","b","b"));
		items.add(new InventoryItem("","c","c"));

		File tempFile = new File(System.getProperty("user.dir")+"/Temp.txt");
		try {

			if(!tempFile.exists()) {
				if (!tempFile.createNewFile())
					throw new IOException();
			}
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}

		TSVParser.parseToFile(tempFile ,items);

		String expected = """
				-abc-abc
				-a-a
				-b-b
				-c-c
				""";

		assertEquals(expected, FileHandler.readFromFile(tempFile));

		if(!tempFile.delete())
			fail();
	}

	@Test
	void Parse_From_File_Test() {
		LinkedList<InventoryItem> expected = new LinkedList<>();
		expected.add(new InventoryItem("","abc","abc"));
		expected.add(new InventoryItem("","a","a"));
		expected.add(new InventoryItem("","b","b"));
		expected.add(new InventoryItem("","c","c"));

		String content = """
				-abc-abc
				-a-a
				-b-b
				-c-c
				""";

		File tempFile = new File(System.getProperty("user.dir")+"/Temp.txt");
		try {
			if(!tempFile.exists()) {
				if (!tempFile.createNewFile())
					throw new IOException();
			}
			FileHandler.writeToFile(content,tempFile);
		}
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}

		LinkedList<InventoryItem> actual = TSVParser.parseFromFile(tempFile);

		for (int i = 0; i < expected.size(); i++) {
			if(!expected.get(i).getPrice().equals(actual.get(i).getPrice()))
				fail();

			if(!expected.get(i).getSerial().equals(actual.get(i).getSerial()))
				fail();

			if(!expected.get(i).getName().equals(actual.get(i).getName()))
				fail();
		}

		if(!tempFile.delete())
			fail();
	}
}