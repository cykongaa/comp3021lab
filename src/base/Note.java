package base;

import java.util.Date;
import java.io.Serializable;

public class Note implements Comparable<Note>, Serializable {

	private Date date;
	private String title;

	public Note(String title) {
		this.title = title;
		this.date = new Date(System.currentTimeMillis());
	}

	public String getTitle() {
		return this.title;
	}

	public String getContent() {
		return null;
	}

	@Override
	public String toString() {
		return date.toString() + "\t" + title;
	}

	@Override
	public boolean equals(Object obj) {

		Note other = (Note) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public int compareTo(Note o) {
		if ((this.date).after(o.date)) {
			return -1;
		} else if ((this.date).before(o.date)) {
			return 1;
		} else
			return 0;
	}

}
