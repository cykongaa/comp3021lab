package test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;


import org.junit.Test;

import base.Note;
import base.NoteBook;
import base.TextNote;
import base.Folder;

public class JUnitTest {

	@Test
	public void testSearchNote() {
		NoteBook nb = new NoteBook();
		nb.createTextNote("Note1", "Java", "comp3021");
		nb.createTextNote("Note2", "Assignment", "due on 2016-10-16");
		nb.createTextNote("Note3", "lab", "need to attend weekly");
		nb.createTextNote("Note4", "lab4", "testing");
		List<Note> notes = nb.searchNotes("java or DUE or testing");
		System.out.println(notes.size());
		assertEquals("The size of the search results is not match", 3, notes.size(), 0.0);
		HashSet<String> titles = new HashSet<String>();
		for (Note note : notes) {
			titles.add(note.getTitle());
		}
		HashSet<String> expectedOutputs = new HashSet<String>();
		expectedOutputs.add("Java");
		expectedOutputs.add("Assignment");
		expectedOutputs.add("lab4");
		assertEquals("The search results is not match", expectedOutputs, titles);
	}

	// To do
	// Design the second test case which reveals the bug in function
	// unknownFunction()

	@Test
	public void testCountLetter() {
		NoteBook nb = new NoteBook();

		nb.createTextNote("Note1", "lab", "tony told us need to attend weekly");

		List<Note> notes=null;
		for (Folder f: nb.getFolders() ){
			notes = f.getNotes();
		}
		
		for (Note n : notes){
			if (n instanceof TextNote){
				ArrayList<Character> c =((TextNote) n).countLetters();
				ArrayList<Character> expected = new ArrayList<Character>();
				expected.add('e');
				expected.add('t');
				//System.out.println(c);
				for (Character k:c){
					System.out.println(k);
				}
				
				
				assertEquals("There are other characters with the same count!", expected , c);
				//assertEquals("There are other characters with the same count!", new Character('e'), c.get(1));
			}
		}
			
		
	}

}