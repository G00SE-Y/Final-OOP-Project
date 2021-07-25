/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 first_name last_name
 */
package ucf.assignments;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class HtmlParser {
	public static void parseToFile(File file, LinkedList<InventoryItem> items) {
		// create string in html format with current list data
		// write string to specified html file

		StringBuilder htmlOut = new StringBuilder("""
				<!DOCTYPE html>
					<html>
						<head>
							<style>
								table {
					  				font-family: arial, sans-serif;
				  					border-collapse: collapse;
				  					width: 100%;
								}
								td, th {
				  					border: 1px solid #dddddd;
				  					text-align: left;
				  					padding: 8px;
								}
								tr:nth-child(even) {
				  					background-color: #dddddd;
								}
							</style>
						</head>
					<body>
					
				<h2>HTML Table</h2>
				    
				<table>
					<tr>
				    	<th>Price</th>
				    	<th>Serial</th>
				    	<th>Name</th>
				  	</tr>
				""");

		for (InventoryItem item : items) {
			htmlOut.append("\t<tr>\n");
			htmlOut.append("\t\t<td>").append(item.getPrice()).append("</td>\n");
			htmlOut.append("\t\t<td>").append(item.getSerial()).append("</td>\n");
			htmlOut.append("\t\t<td>").append(item.getName()).append("</td>\n");
			htmlOut.append("\t</tr>\n");
		}

		try {
			FileHandler.writeToFile(htmlOut.toString() , file);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could Not Save To File");
		}
	}

	public static LinkedList<InventoryItem> parseFromFile(File file) {
		// read string from specified html file
		// convert string into object data
		// return list with all objects read from file

		LinkedList<InventoryItem> list = new LinkedList<>();

		String fileString = FileHandler.readFromFile(file);
		if(fileString == null)
			return new LinkedList<>();

		String[] stringArr = fileString.split("\n");
		ArrayList<String> infoStrings = new ArrayList<>();

		for(String string : stringArr) {
			if(string.contains("<td>")) {
				infoStrings.add(string);
			}
		}

		for (int i = 0; i < infoStrings.size(); i++) {
			String temp = infoStrings.remove(0);

			temp = temp.replace("<td>","");
			temp = temp.replace("</td>","");
			temp = temp.strip();

			infoStrings.add(temp);
		}

		for (int i = 0; i < infoStrings.size(); i+=3) {
			list.add(new InventoryItem(infoStrings.get(i),infoStrings.get(i+1),infoStrings.get(i+2)));
		}

		System.out.println(list);
		return list;
	}
}
