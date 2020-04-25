package com.example.notekeeper;

import android.provider.BaseColumns;

public final class NoteKeeperDbContract {
    private NoteKeeperDbContract(){} //make it non-creatable

    public static final class CourseInfoEntry implements BaseColumns {
        public static final String TABLE_NAME="course_info";
        public static final String COLUMN_COURSE_ID = "course_id";
        public static final String COLUMN_COURSE_TITLE="course_title";

        // Create Table
        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE"+ TABLE_NAME +"(" +
                        _ID + "INTEGER PRIMARY KEY"+
                        COLUMN_COURSE_ID + "TEXT UNIQUE NOT NULL," +
                        COLUMN_COURSE_TITLE + " TEXT NOT NULL," +
                        ");";

    }

    public static final class NoteInfoEntry implements BaseColumns{
        private static final String TABLE_NAME = "note_info";
        public static final String COLUMN_COURSE_ID = "course_id";
        private  static final String COLUMN_NOTE_TITLE="note_title";
        private  static final String COLUMN_NOTE_TEXT="note_text";

        //CREATE TABLE
        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE"+ TABLE_NAME + "(" +
                        _ID + "INTEGER PRIMARY KEY"+
                        COLUMN_NOTE_TITLE + "TEXT NOT NULL," +
                        COLUMN_NOTE_TEXT + "TEXT ," +
                        COLUMN_COURSE_ID + "TEXT NOT NULL," +
                        ");";
    }

}
