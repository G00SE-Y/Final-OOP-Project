/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 first_name last_name
 */
package ucf.assignments;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class TSVParser {
	public static void parseToFile(File file, LinkedList<InventoryItem> data) {
		// create string in tsv format with current list data
		// write string to specified json file

		String dataString= tabify(data);
		try {
			FileHandler.writeToFile(dataString, file);

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("File Could Not Be Written To");
		}
	}

	private static String tabify(LinkedList<InventoryItem> data) {
		// create a string in tsv format with list data

		StringBuilder string = new StringBuilder();
		for(InventoryItem item : data) {
			string.append(item.getPrice()).append("-").append(item.getSerial()).append("-").append(item.getName()).append("\n");
		}
		return string.toString();
	}

	public static LinkedList<InventoryItem> parseFromFile(File file) {
		// read string from specified json file
		// convert string into object data
		// return list with all objects read from file

		LinkedList<InventoryItem> list = new LinkedList<>();

		try{
			BufferedReader in = FileHandler.getReader(file);
			String next = "";
			String[] tempArr;

			while(next != null) {
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
