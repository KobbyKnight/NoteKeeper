package com.example.notekeeper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteRecyclerAdapter extends RecyclerView.Adapter<NoteRecyclerAdapter.ViewHolder> {

    private final Context aContext;
    private final List<NoteInfo> aNoteInfo;
    private LayoutInflater aLayoutInflater;
    //public int aPosition;

    public NoteRecyclerAdapter(Context sContext, List<NoteInfo> noteInfo) {
        this.aContext = sContext;
        aLayoutInflater = LayoutInflater.from(sContext);
        aNoteInfo = noteInfo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = aLayoutInflater.inflate(R.layout.item_note_list,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NoteInfo notes = aNoteInfo.get(position);
        holder.aTextCourse.setText(notes.getCourse().getTitle());
        holder.aTextTitle.setText(notes.getTitle());
        holder.aPosition = position;
    }

    @Override
    public int getItemCount() {
        return aNoteInfo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView aTextCourse;
        public TextView aTextTitle;
        public int aPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            aTextCourse = itemView.findViewById(R.id.text_course);
            aTextTitle = itemView.findViewById(R.id.text_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(aContext, NoteActivity.class);
                    intent.putExtra(NoteActivity.NOTE_POSITION,aPosition);
                    aContext.startActivity(intent);

                }
            });

        }
    }
}
