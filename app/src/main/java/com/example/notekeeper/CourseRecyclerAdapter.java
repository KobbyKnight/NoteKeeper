package com.example.notekeeper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class CourseRecyclerAdapter extends RecyclerView.Adapter<CourseRecyclerAdapter.ViewHolder> {

    private final Context aContext;
    private final List<CourseInfo> aCoursesInfo;
    private LayoutInflater aLayoutInflater;
    //public int aPosition;

    public CourseRecyclerAdapter(Context sContext, List<CourseInfo> coursesInfo) {
        this.aContext = sContext;
        aLayoutInflater = LayoutInflater.from(sContext);
        aCoursesInfo = coursesInfo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = aLayoutInflater.inflate(R.layout.item_course_list,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CourseInfo course = aCoursesInfo.get(position);
        holder.aTextCourse.setText(course.getTitle());
        holder.aPosition = position;
    }

    @Override
    public int getItemCount() {
        return aCoursesInfo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView aTextCourse;
        public int aPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            aTextCourse = itemView.findViewById(R.id.text_course);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view,aCoursesInfo.get(aPosition).getTitle(),Snackbar.LENGTH_LONG).show();

                }
            });

        }
    }
}
