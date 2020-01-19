package com.example.collegedada1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.MyViewHolder> {


    private LayoutInflater inflater;
    private ArrayList<FruitModel> imageModelArrayList;


    public FruitAdapter(Context ctx, ArrayList<FruitModel> imageModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.imageModelArrayList = imageModelArrayList;
    }

    @Override
    public FruitAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.recycler_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FruitAdapter.MyViewHolder holder, final int position)  {

        holder.iv.setImageResource(imageModelArrayList.get(position).getImage_drawable());
        holder.time.setText(imageModelArrayList.get(position).getName());
        holder.iv.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (imageModelArrayList.get(position).getName()){
                    case "Maths" :


                        break;
                    case "Chemistry" :


                        break;
                    case "Physics" :


                        break;
                    case "Petroleum" :


                        break;
                }
            }
        } );
    }

    @Override
    public int getItemCount() {
        return imageModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView time;
        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);

            time = (TextView)itemView.findViewById(R.id.tv);
            iv = (ImageView)itemView.findViewById(R.id.iv);
        }

    }
}
