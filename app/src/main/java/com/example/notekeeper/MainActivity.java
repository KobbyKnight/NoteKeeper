package com.example.notekeeper;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private NoteRecyclerAdapter aNoteRecyclerAdapter;
    private RecyclerView aRecyclerItems;
    private LinearLayoutManager aLayoutManager;
    private CourseRecyclerAdapter aCourseRecyclerAdapter;
    private GridLayoutManager aCourseLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NoteActivity.class));

            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        initialiseDisplayContent();
    }

    //    to ensure when note list is changed data
    //    manger knows and this prevent app from crashing
    @Override
    protected void onResume() {
        super.onResume();
        // adapterNotes.notifyDataSetChanged();
        aNoteRecyclerAdapter.notifyDataSetChanged();
    }

    private void initialiseDisplayContent() {

        aRecyclerItems = findViewById(R.id.item_list);
        aLayoutManager = new LinearLayoutManager(this);
        aCourseLayoutManager = new GridLayoutManager(this,2);
        List<NoteInfo> notes = DataManager.getInstance().getNotes();
        aNoteRecyclerAdapter = new NoteRecyclerAdapter(this,notes);
        List<CourseInfo> course = DataManager.getInstance().getCourses();
        aCourseRecyclerAdapter = new CourseRecyclerAdapter(this,course);

        displayNotes();

    }
    private void displayCourses(){
        aRecyclerItems.setLayoutManager(aCourseLayoutManager);
        aRecyclerItems.setAdapter(aCourseRecyclerAdapter);
        selectedNavigationItem(R.id.nav_courses);
    }

    private void displayNotes() {
        aRecyclerItems.setLayoutManager(aLayoutManager);
        aRecyclerItems.setAdapter(aNoteRecyclerAdapter);
        selectedNavigationItem(R.id.nav_notes);
    }

    private void selectedNavigationItem(int id) {
        NavigationView navigationView = findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        menu.findItem(id).setChecked(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_notes) {
              displayNotes();
        } else if (id == R.id.nav_courses) {
            displayCourses();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
