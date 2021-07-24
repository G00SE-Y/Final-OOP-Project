package ucf.assignments;

import java.io.*;

public class FileHandler {

	public static BufferedReader getReader(File file) throws FileNotFoundException {
		//create new buffered reader from string file path
		return new BufferedReader(new FileReader(file));
	}

	public static BufferedWriter getWriter(File file) throws IOException {
		// create a new buffered writer from string file path
		if(!file.exists())
			if(!file.createNewFile())
				System.out.println("File Could Not Be Created");

		return new BufferedWriter(new FileWriter(file));
	}

	public static void writeToFile(String string, File file) throws IOException {
		BufferedWriter writer = getWriter(file);
		writer.write(string);
		writer.close();
	}

	public static String readFromFile(File file) {
		StringBuilder content = new StringBuilder();
		String next = "";
		BufferedReader reader;
		try {
			reader = FileHandler.getReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Could Not Read File");
			return null;
		}

		while(true) {
			try {
				next = reader.readLine();
				if(next == null)
					break;
			} catch (IOException e) {
				e.printStackTrace();
			}

			content.append(next).append("\n");

		}
		System.out.println(content);
		return content.toString();
	}
}
