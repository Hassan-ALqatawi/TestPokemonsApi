package com.al_qatawi.testpokemonsapi.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.al_qatawi.testpokemonsapi.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.AdapterView> {

    ArrayList<Pokemon> arrayList = new ArrayList<>();
    Context context;

    public Adapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterView(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterView holder, int position) {

        Glide.with(context).load(arrayList.get(position).getUrl())
                .into(holder.imageView);
        holder.textView.setText(arrayList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void list (ArrayList<Pokemon> arrayList){

        this.arrayList = arrayList;
        notifyDataSetChanged();


    }

    public Pokemon swipedPokemonAt(int position){

       return arrayList.get(position);

    }

    public class AdapterView extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public AdapterView(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imegView);
            textView = itemView.findViewById(R.id.textView);
        }
    }




}
