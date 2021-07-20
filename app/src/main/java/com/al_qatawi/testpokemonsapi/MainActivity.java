package com.al_qatawi.testpokemonsapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.al_qatawi.testpokemonsapi.model.Adapter;
import com.al_qatawi.testpokemonsapi.model.Pokemon;
import com.al_qatawi.testpokemonsapi.viewModels.PokemonViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    RecyclerView recyclerView;
    PokemonViewModel pokemonViewModel;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);

        adapter = new Adapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        swipedPokemonToFav();

        pokemonViewModel = new ViewModelProvider(this).get(PokemonViewModel.class);

        pokemonViewModel.getPokemon();

        pokemonViewModel.getPokemonList().observe(this, new Observer<ArrayList<Pokemon>>() {
            @Override
            public void onChanged(ArrayList<Pokemon> arrayList) {
                adapter.list(arrayList);
                Log.d(TAG, "hhh onChanged: " + arrayList.get(5).getName());

            }
        });


        Button butTo_fav = findViewById(R.id.but_To_Fav);

        butTo_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,FavActivity.class));

            }
        });

    }
    public void swipedPokemonToFav(){

        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                int swipedPokemonFav = viewHolder.getAdapterPosition();
               Pokemon swipedPokemon = adapter.swipedPokemonAt(swipedPokemonFav);
                pokemonViewModel.insertPokemon(swipedPokemon);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "تم الاضافه", Toast.LENGTH_SHORT).show();

            }
        };

        ItemTouchHelper itemTouchHelper1 = new ItemTouchHelper(callback);
        itemTouchHelper1.attachToRecyclerView(recyclerView);

    }


}