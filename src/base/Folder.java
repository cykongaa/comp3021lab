package base;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.io.Serializable;

public class Folder implements Comparable<Folder>, Serializable {

	private ArrayList<Note> notes;
	private static final long serialVersionUID = 1L;
	private String name;

	public Folder(String name) {
		this.name = name;
		notes = new ArrayList<Note>();
	}

	public void addNote(Note note) {
		notes.add(note);
	}

	public String getName() {
		return this.name;
	}

	public ArrayList<Note> getNotes() {
		return notes;
	}

	public void sortNotes() {
		Collections.sort(notes);
	}

	public boolean removeNotes(String title) {
		// TODO
		// Given the title of the note, delete it from the folder.
		// Return true if it is deleted successfully, otherwise return false.
		for (Note n : this.getNotes())
			if (n.getTitle().equals(title)) {
				this.getNotes().remove(n);
				return true;
			}
		return false;
	}

	public List<Note> searchNotes(String keywords) {
		List<Note> result = new ArrayList<Note>();
		String[] keys = keywords.toLowerCase().split("(\\s+)");
		for (int i = 0; i < keys.length; i++) {
			keys[i] = keys[i].toLowerCase();
			// System.out.println(i + " :" + keys[i] + " ");
		}

		ArrayList<String> orArray = new ArrayList<>();
		ArrayList<String> andArray = new ArrayList<>();

		int j = 0;

		while (j < keys.length) {
			String s = "";
			if (j + 1 < keys.length && keys[j + 1].equals("or")) {
				while (j + 1 < keys.length && keys[j + 1].equals("or")) {
					s = s + " " + keys[j];
					j = j + 2;
				}
				s = s + " " + keys[j];
				orArray.add(s);
				j = j + 1;
			} else {
				andArray.add(keys[j]);
				j = j + 1;
			}
		}
		// System.out.println(keys.length);
		// System.out.println("orArray");
		// ;
		// for (String s : orArray) {
		// System.out.println(s);
		// }
		//
		// System.out.println("andArray");
		//
		if (!orArray.isEmpty()) {
			andArray.addAll(orArray);
		}
		// for (String s : andArray) {
		// System.out.println(s);
		// }
		// System.out.println(orArray.size());

		for (Note n : this.getNotes()) {

			boolean matched = true;

			if (n instanceof ImageNote) {

				for (String k : andArray) {
					String[] orKey = k.trim().split("\\s+");
					// for (int p=0; p<orKey.length; p++){
					// System.out.println(p + ": " +orKey[p]);
					// }
					boolean tempMatched = false;
					for (int q = 0; q < orKey.length; q++) {
						if (n.getTitle().toLowerCase().contains(orKey[q])) {
							// System.out.println(n.getTitle() + ": " + orKey[q]
							// + " true");
							tempMatched = true;
						}
					}
					matched = matched && tempMatched;
				}
				if (matched == true) {
					// System.out.println(n.getTitle());
					result.add(n);
				}
			} else if (n instanceof TextNote) {

				// System.out.println("textnotes");
				// System.out.println(andArray.size());
				for (String k : andArray) {
					String[] orKey = k.trim().split("\\s+");

					// for (int p = 0; p < orKey.length; p++) {
					// System.out.println(p + ": " + orKey[p]);
					// }
					boolean tempMatched = false;
					for (int q = 0; q < orKey.length; q++) {
						if (n.getTitle().toLowerCase().contains(orKey[q])
								|| n.getContent().toLowerCase().contains(orKey[q])) {
							// System.out.println(n.getTitle() + ": " + orKey[q]
							// + " true");
							tempMatched = true;
						}
					}
					matched = matched && tempMatched;
				}

				if (matched == true) {
					// System.out.println(n.getTitle());
					result.add(n);
				}
			}

		}

		return result;

	}

	@Override
	public String toString() {
		int nText = 0;
		int nImage = 0;

		for (Note n : notes) {
			if (n instanceof TextNote)
				nText++;
			else
				nImage++;
		}
		return name + ":" + nText + ":" + nImage;
	}

	@Override
	public int compareTo(Folder o) {
		if ((this.name).compareTo(o.getName()) < 0) {
			return -1;
		} else if ((this.name).compareTo(o.getName()) == 0) {
			return 0;
		} else
			return 1;
	}
}
