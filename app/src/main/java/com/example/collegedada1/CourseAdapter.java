package com.example.collegedada1;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    Context context;
    ArrayList<Course> Course;

    public CourseAdapter(Context c , ArrayList<Course> p)
    {
        context = c;
        Course = p;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CourseViewHolder( LayoutInflater.from( context).inflate( R.layout.fragment_course, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, final int position) {
        holder.Name.setText(Course.get(position).getCourseName());
        holder.Name.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = Course.get( position ).getDownloadLink();
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(url), "application/pdf");
                context.startActivity(Intent.createChooser(intent, "Choose an Application:"));
            }
        } );
        holder.DownloadButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Course.get( position ).getDownloadLink()));
                context.startActivity(browserIntent);
            }
        } );
        holder.SNo.setText(String.valueOf( position+1) );
    }

    @Override
    public int getItemCount() {
        return Course.size();
    }

    class CourseViewHolder extends RecyclerView.ViewHolder
    {
        TextView Name,SNo;
        ImageView DownloadButton;

        public CourseViewHolder(View itemView) {
            super(itemView);
            Name = (TextView) itemView.findViewById(R.id.contentcourse);
            SNo = (TextView) itemView.findViewById(R.id.item_number_course);
            DownloadButton = (ImageView)itemView.findViewById(R.id.download_link_course);
        }
    }
}