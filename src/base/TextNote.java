package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class TextNote extends Note {
	public String content;

	public TextNote(String title) {
		super(title);
	}

	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}

	@Override
	public String getContent() {
		return content;
	}

	/**
	 * load a TextNote from File f
	 *
	 * the tile of the TextNote is the name of the file the content of the
	 * TextNote is the content of the file
	 *
	 * @param File
	 *            f
	 */
	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}

	/**
	 * get the content of a file
	 *
	 * @param absolutePath
	 *            of the file
	 * @return the content of the file
	 */
	private String getTextFromFile(String absolutePath) {
		String result = "";
		File file = new File(absolutePath);
		FileInputStream fis = null;
		BufferedReader reader = null;

		try {
			fis = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(fis));
			while ((result = reader.readLine()) != null) {
				System.out.println(result);
			}
			fis.close();
		} catch (FileNotFoundException e) {
			System.out.println("The file cannot be found!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * export text note to file
	 *
	 *
	 * @param pathFolder
	 *            path of the folder where to export the note the file has to be
	 *            named as the title of the note with extension ".txt"
	 *
	 *            if the tile contains white spaces " " they has to be replaced
	 *            with underscores "_"
	 *
	 *
	 */
	public void exportTextToFile(String pathFolder) {
		FileWriter writer = null;
		BufferedWriter out = null;
		try {
			String noteTitle = this.getTitle();
			noteTitle = noteTitle.replaceAll(" ", "_");
			// File file = new File(
			// "C:\\Users\\kongchingyiii\\git\\comp3021lab" + pathFolder +
			// File.separator + noteTitle + ".txt");

			File file = new File(pathFolder + noteTitle + ".txt");
			/*
			 * if (file.canRead()) { System.out.println(file); } else {
			 * System.out.println("Can't read"); }
			 */

			writer = new FileWriter(file.getAbsolutePath());
			out = new BufferedWriter(writer);
			out.write(this.getContent());
			out.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return;

	}

}
