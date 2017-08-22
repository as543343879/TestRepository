package test2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Exam {

	public static void main(String[] args) throws IOException {
		String compPath = "C:/company.txt";
		File compFile = new File(compPath);
		FileInputStream fis = new FileInputStream(compFile);
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		
		BufferedReader br = new BufferedReader(isr);
		while (true) {
			String comp = br.readLine();
			if (comp == null)
				break;
			String path = "C:/new/" + comp;
			File file = new File(path);

			if (file.mkdirs()) {
				System.out.println("1");
			}
		}
		// String path = "D:/new/" + line;
		// File file = new File(path);
		//
		// if (file.mkdirs()) {
		// System.out.println("1");
		// }

	}
}
