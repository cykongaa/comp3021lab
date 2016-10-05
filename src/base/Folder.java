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

	public List<Note> searchNotes(String keywords) {
		List<Note> result = new ArrayList<Note>();
		String[] keys = keywords.split(" ");
		for (int i = 0; i < keys.length; i++) {
			keys[i] = keys[i].toLowerCase();
		}

		boolean matched = false;

		for (Note n : this.getNotes()) {
			int i = 0;

			if (n instanceof ImageNote) {
				// System.out.println("ImageNote");
				while (i < keys.length) {
					// if (!keys[i].equals("or")) {
					if (i != keys.length - 1) {
						if (!keys[i + 1].toLowerCase().equals("or")) {
							if ((n.getTitle().toLowerCase()).contains(keys[i])) {
								matched = true;
								// System.out.println("true " + n.getTitle() + "
								// " + keys[i]);
								i++;
							} else {
								matched = false;
								// System.out.println("false " + n.getTitle() +
								// " " + keys[i]);
								break;
							}
						} else if (keys[i + 1].toLowerCase().equals("or")) {
							if (((n.getTitle().toLowerCase()).contains(keys[i]))
									|| (n.getTitle().toLowerCase().contains(keys[i + 2]))) {
								matched = true;
								// System.out.println("true " + n.getTitle() + "
								// " + keys[i] + " or " + keys[i + 2]);
								i = i + 3;
							} else {
								matched = false;
								// System.out.println("false" + n.getTitle() + "
								// " + keys[i] + " or " + keys[i + 2]);
								break;
							}

						}
					} else {
						if ((n.getTitle().toLowerCase()).contains(keys[i])) {
							matched = true;
							// System.out.println("true" + n.getTitle() + " " +
							// keys[i]);
							i++;
						} else {
							matched = false;
							// System.out.println("false" + n.getTitle() + " " +
							// keys[i]);
							break;
						}

					}

				}

				if (matched == true) {
					result.add(n);
				}
			} else if (n instanceof TextNote) {
				// System.out.println("TextNote");
				while (i < keys.length) {
					// if (!keys[i].toLowerCase().equals("or")) {
					if (i != keys.length - 1) {
						if (!keys[i + 1].toLowerCase().equals("or")) {

							if ((n.getTitle().toLowerCase()).contains(keys[i])
									|| n.getContent().toLowerCase().contains(keys[i])) {
								matched = true;
								// System.out.println("true");
								i++;
							} else {
								matched = false;
								// System.out.println("false");
								break;
							}
						} else if (keys[i + 1].toLowerCase().equals("or")) {
							if (((n.getTitle().toLowerCase()).contains(keys[i]))
									|| (n.getTitle().toLowerCase().contains(keys[i + 2]))
									|| (n.getContent().toLowerCase().contains(keys[i]))
									|| (n.getContent().toLowerCase().contains(keys[i + 2]))) {
								matched = true;
								// System.out.println("true");
								i = i + 3;
							} else {
								matched = false;
								// System.out.println("false");
								break;
							}

						}
					} else {

						if ((n.getTitle().toLowerCase()).contains(keys[i])
								|| n.getContent().toLowerCase().contains(keys[i])) {
							matched = true;
							// System.out.println("true");
							i++;
						} else {
							matched = false;
							// System.out.println("false");
							break;
						}
					}

				}

				if (matched == true) {
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
