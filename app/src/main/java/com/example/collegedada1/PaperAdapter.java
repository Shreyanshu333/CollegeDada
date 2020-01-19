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

public class PaperAdapter extends RecyclerView.Adapter<PaperAdapter.PaperViewHolder> {

    Context context;
    ArrayList<Papers> Papers;

    public PaperAdapter(Context c , ArrayList<Papers> p)
    {
        context = c;
        Papers = p;
    }

    @NonNull
    @Override
    public PaperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PaperViewHolder( LayoutInflater.from( context).inflate( R.layout.fragment_papers, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull PaperViewHolder holder, final int position) {
        holder.Name.setText(Papers.get(position).getPaperName());
        holder.Name.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = Papers.get( position ).getDownloadLink();
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(url), "application/pdf");
                context.startActivity(Intent.createChooser(intent, "Choose an Application:"));
            }
        } );
        holder.DownloadButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Papers.get( position ).getDownloadLink()));
                context.startActivity(browserIntent);
            }
        } );
        holder.SNo.setText(String.valueOf( position+1) );
    }

    @Override
    public int getItemCount() {
        return Papers.size();
    }

    class PaperViewHolder extends RecyclerView.ViewHolder
    {
        TextView Name,SNo;
        ImageView DownloadButton;

        public PaperViewHolder(View itemView) {
            super(itemView);
            Name = (TextView) itemView.findViewById(R.id.contentpaper);
            SNo = (TextView) itemView.findViewById(R.id.item_number);
            DownloadButton = (ImageView)itemView.findViewById(R.id.download_link);
        }
    }
}