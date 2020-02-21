package com.example.namequiz_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

//The adapter represents the data that is shown with the viewholder
public class  Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    public  ArrayList<Animals> aList;

    Adapter(Context context, ArrayList<Animals> aList) {
        this.layoutInflater=LayoutInflater.from(context);
        this.aList=aList;
    }


    @NonNull
    @Override //Called when the recycler needs a new Viewholder to represent an item
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.card_view, parent, false); //infates a new viewHolder
        return new ViewHolder(view); //New ViewHolder will be used to display items of the adapter using onBindViewHolder
    }

    @Override //Called by recyclerview to display the data at specified position
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, final int position) {
       //binds views to data
        int aImage = aList.get(position).getImage();
        String aName = aList.get(position).getName();

        //after then binds data to the views
        holder.image.setImageResource(aImage);
        holder.name.setText(aName);

        //To delete an animal
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aList.remove(position);
                notifyItemRemoved(position);
            }
        });


    }
//Returns the number of items currently available in the adapter
    @Override
    public int getItemCount() {
        return aList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name;
        CardView itemLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            itemLayout = itemView.findViewById(R.id.cardView);
        }
    }
}










