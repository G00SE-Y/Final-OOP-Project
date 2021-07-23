package ucf.assignments;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class TSVParser {
	public static void parseToFile(File file, LinkedList<InventoryItem> data) {
		String dataString= tabify(data);
		try {
			FileHandler.writeToFile(dataString, file);

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("File Could Not Be Written To");
		}
	}

	private static String tabify(LinkedList<InventoryItem> data) {
		StringBuilder string = new StringBuilder();
		for(InventoryItem item : data) {
			string.append(item.getPrice()).append("-").append(item.getSerial()).append("-").append(item.getName()).append("\n");
		}
		return string.toString();
	}

	public static LinkedList<InventoryItem> parseFromFile(File file) {
		LinkedList<InventoryItem> list = new LinkedList<>();

		try{
			BufferedReader in = FileHandler.getReader(file);
			String next = "";
			String[] tempArr;

			while(next != null) {
				System.out.println("text: "+next);
				if(!next.contentEquals("")) {
					tempArr = next.split("-");
					list.add(new InventoryItem(tempArr[0], tempArr[1], tempArr[2]));
				}
				next = in.readLine();

			}

			in.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could Not Read File");
		}

		return list;
	}
}
