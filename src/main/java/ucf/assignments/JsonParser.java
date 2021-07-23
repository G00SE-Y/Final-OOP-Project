package ucf.assignments;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.LinkedList;

public class JsonParser {
	@SuppressWarnings("unchecked")
	public static void parseToFile(File file, LinkedList<InventoryItem> items) {

			JSONArray array = new JSONArray();

			for (InventoryItem item : items) {
				JSONObject object = new JSONObject();
				object.put("price", item.getPrice());
				object.put("serial", item.getSerial());
				object.put("name", String.valueOf(item.getName()));
				array.add(object);
			}
			try {
				FileHandler.writeToFile(array.toJSONString(), file);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Could Not Save To File");
			}

	}
	//@SuppressWarnings("unchecked")
	public static LinkedList<InventoryItem> parseFromFile(File file) {
		BufferedReader reader;
		JSONParser parse = new JSONParser();
		JSONArray jsonArray;

		try {
			reader = FileHandler.getReader(file);
			jsonArray = (JSONArray) parse.parse(reader);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
			System.out.println("Could Not Open File");
			return null;
		}


		LinkedList<InventoryItem> list = new LinkedList<>();

		for (Object item : jsonArray) {
			JSONObject jsonObj = (JSONObject) item;

			String price = (String) jsonObj.get("price");
			String serial = (String) jsonObj.get("serial");
			String name = (String) jsonObj.get("name");

			InventoryItem newItem = new InventoryItem(price, serial, name);
			list.add(newItem);

		}
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could Not Close File");
		}

		return list;
	}
}
