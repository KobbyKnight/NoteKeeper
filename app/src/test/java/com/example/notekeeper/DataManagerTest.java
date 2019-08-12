package com.example.notekeeper;

import org.junit.Test;

import static org.junit.Assert.*;

public class DataManagerTest {

    @Test
    public void createNewNote() throws Exception {
        DataManager dm = DataManager.getInstance();
        final CourseInfo course = dm.getCourse("android_async");
        final String textTitle = "Test note Title";
        final String textNote = "Test note body";

        int noteIndex = dm.createNewNote();
        NoteInfo newNote = dm.getNotes().get(noteIndex);
        newNote.setCourse(course);
        newNote.setTitle(textTitle);
        newNote.setText(textNote);

        NoteInfo compareNote = dm.getNotes().get(noteIndex);
        assertEquals(course,compareNote.getCourse());
        assertEquals(textTitle,compareNote.getTitle());
        assertEquals(textNote,compareNote.getText());
    }
}