package com.example.notekeeper;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {
   // private NoteRecyclerAdapter aNoteRecyclerAdapter;

    //private ArrayAdapter<NoteInfo> adapterNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoteListActivity.this, NoteActivity.class));
            }
        });
       // initialiseDisplayContent();
    }

////    to ensure when note list is changed data
////    manger knows and this prevent app from crashing
//    @Override
//    protected void onResume() {
//        super.onResume();
//       // adapterNotes.notifyDataSetChanged();
//        aNoteRecyclerAdapter.notifyDataSetChanged();
//    }
//
//    private void initialiseDisplayContent() {
////        final ListView listNotes = findViewById(R.id.list_notes);
////        List <NoteInfo> notes = DataManager.getInstance().getNotes();
////        adapterNotes = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,notes);
////        listNotes.setAdapter(adapterNotes);
////
////        listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////            @Override
////            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////                Intent intent = new Intent(NoteListActivity.this,NoteActivity.class);
//////                NoteInfo note = (NoteInfo) listNotes.getItemAtPosition(i);
////                intent.putExtra(NoteActivity.NOTE_POSITION, i);
////                startActivity(intent);
////            }
////        });
//        final RecyclerView recycleNotes = findViewById(R.id.list_notes);
//        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recycleNotes.setLayoutManager(layoutManager);
//        List <NoteInfo> notes = DataManager.getInstance().getNotes();
//        aNoteRecyclerAdapter = new NoteRecyclerAdapter(this,notes);
//        recycleNotes.setAdapter(aNoteRecyclerAdapter);
//
//    }


}
