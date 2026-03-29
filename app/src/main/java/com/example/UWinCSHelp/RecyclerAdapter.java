package com.example.UWinCSHelp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<CardItem> list;

    public RecyclerAdapter(Context context, ArrayList<CardItem> list) {
        this.context = context;
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button button;
        ImageView imageView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.cardButton);
            imageView = itemView.findViewById(R.id.cardImage);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        CardItem item = list.get(position);

        holder.button.setText(item.getButtonText());
        holder.imageView.setImageResource(item.getImage());
        // clicking card opens activity
        holder.cardView.setOnClickListener(v -> {
            int currentPosition = holder.getAdapterPosition();
            if(currentPosition == 0){
                Intent intent = new Intent(v.getContext(), PastCourses.class);
                v.getContext().startActivity(intent);
            }
            if(currentPosition == 1){
                Intent intent = new Intent(v.getContext(), CurrentCourses.class);
                v.getContext().startActivity(intent);
            }
            if(currentPosition == 2){
                Intent intent = new Intent(v.getContext(), RequiredCourses.class);
                v.getContext().startActivity(intent);
            }
        });
    }
    // show all cards
    @Override
    public int getItemCount() {
        return list.size();
    }
}
