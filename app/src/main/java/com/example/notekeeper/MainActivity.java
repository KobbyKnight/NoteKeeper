package com.example.notekeeper;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String NOTE_POSITION = "com.example.notekeeper.NOTE_POSITION";
    public static final String ORIGINAL_NOTE_COURSE_ID = "com.example.notekeeper.ORIGINAL_NOTE_COURSE_ID";
    public static final String ORIGINAL_NOTE_TITLE = "com.example.notekeeper.ORIGINAL_NOTE_TITLE";
    public static final String ORIGINAL_NOTE_TEXT = "com.example.notekeeper.ORIGINAL_NOTE_TEXT";


    public static final int POSITION_NOT_SET = -1;
    private NoteInfo note;
    private boolean isNewNote;
    private Spinner spinnerCourses;
    private EditText textNoteTitle;
    private EditText textNote;
    private int notePosition;
    private boolean isCancelling;
    private String originalNoteCourseId;
    private String originalTextNote;
    private String originalNoteTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinnerCourses = findViewById(R.id.spinner_courses);
        List<CourseInfo> courses = DataManager.getInstance().getCourses();
        ArrayAdapter<CourseInfo> adapterCourses =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,courses);
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourses.setAdapter(adapterCourses);

        readDisplayStateValues();
        if (savedInstanceState == null){
            saveOriginalNoteValues();
        }else{
            restoreOriginalNoteValues(savedInstanceState);
        }
        textNoteTitle = findViewById(R.id.text_note_title);
        textNote = findViewById(R.id.text_note_text);

        if (!isNewNote)
            displayNote(spinnerCourses, textNoteTitle, textNote);

    }

    // restores the original values after cancel
    private void restoreOriginalNoteValues(Bundle savedInstanceState) {
        originalNoteCourseId = savedInstanceState.getString(ORIGINAL_NOTE_COURSE_ID);
        originalNoteTitle = savedInstanceState.getString(ORIGINAL_NOTE_TITLE);
        originalTextNote = savedInstanceState.getString(ORIGINAL_NOTE_TEXT);
    }

//    OVERRIDE SAVE INSTANCE STATE TO KEEP ORIGINAL VALUES
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ORIGINAL_NOTE_COURSE_ID,originalNoteCourseId);
        outState.putString(ORIGINAL_NOTE_TEXT,originalTextNote);
        outState.putString(ORIGINAL_NOTE_TITLE,originalNoteTitle);
    }

    private void saveOriginalNoteValues() {
        if (isNewNote){
            return;
        }
        originalNoteCourseId = note.getCourse().getCourseId();
        originalTextNote = note.getText();
        originalNoteTitle = note.getTitle();

    }

    private void displayNote(Spinner spinnerCourses, EditText textNoteTitle, EditText textNote) {
        List<CourseInfo> courses = DataManager.getInstance().getCourses();
        int courseIndex = courses.indexOf(note.getCourse());
        spinnerCourses.setSelection(courseIndex);
        textNoteTitle.setText(note.getTitle());
        textNote.setText(note.getText());
    }

    private void readDisplayStateValues() {
        Intent intent = getIntent();
//        note = intent.getParcelableExtra(NOTE_POSITION);
//        isNewNote = note == null;
        int position = intent.getIntExtra(NOTE_POSITION, POSITION_NOT_SET);
        isNewNote = position == POSITION_NOT_SET;
        //Check if new note or not
        if(isNewNote){
            createNewNote();
        }else{
            note = DataManager.getInstance().getNotes().get(position);
        }
    }

    private void createNewNote() {
        DataManager dm = DataManager.getInstance();
        notePosition = dm.createNewNote();
        note =  dm.getNotes().get(notePosition);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_send_mail) {
            sendEmail();
            return true;
        }else if(id == R.id.action_cancel){
            isCancelling = true;
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (isCancelling){
            if(isNewNote){
                DataManager.getInstance().removeNote(notePosition);
            }else{
                storePreviousNoteValues();
            }
        }else{
            saveNote();
        }
    }

    private void storePreviousNoteValues() {
        CourseInfo course = DataManager.getInstance().getCourse(originalNoteCourseId);
        note.setCourse(course);
        note.setTitle(originalNoteTitle);
        note.setText(originalTextNote);
    }

    private void saveNote() {
        note.setCourse((CourseInfo) spinnerCourses.getSelectedItem());
        note.setText(textNote.getText().toString());
        note.setTitle(textNoteTitle.getText().toString());
    }

    private void sendEmail() {
        CourseInfo courseInfo = (CourseInfo) spinnerCourses.getSelectedItem();
        String subject = textNoteTitle.getText().toString();
        String body = "Check out this Pluralsight video \'"
                + courseInfo.getTitle() +"\'\n"+textNote.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc2822");
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT,body);
        startActivity(intent);
    }
}
