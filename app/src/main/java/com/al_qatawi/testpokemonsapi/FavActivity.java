package com.al_qatawi.testpokemonsapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.al_qatawi.testpokemonsapi.model.Adapter;
import com.al_qatawi.testpokemonsapi.model.Pokemon;
import com.al_qatawi.testpokemonsapi.viewModels.PokemonViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FavActivity extends AppCompatActivity {

    PokemonViewModel pokemonViewModel;
    RecyclerView recyclerView;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);

        recyclerView = findViewById(R.id.recycler_fav);

        adapter = new Adapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        sutepSwiped();


        Button butTo_Home = findViewById(R.id.but_To_Home);
        butTo_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(FavActivity.this,MainActivity.class));

            }
        });


        pokemonViewModel = new ViewModelProvider(this).get(PokemonViewModel.class);

        pokemonViewModel.getPokemon_DB();


        pokemonViewModel
                .getPokemonListDB().observe(this, new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {

                ArrayList<Pokemon> list = new ArrayList<>();
                list.addAll(pokemons);
                adapter.list(list);

            }
        });






    }


    public void sutepSwiped(){


        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                int swipedPosistion = viewHolder.getAdapterPosition();

                Pokemon swiped = adapter.swipedPokemonAt(swipedPosistion);
               String pokemonName = swiped.getName();
                pokemonViewModel.deletePokemon(pokemonName);
                adapter.notifyDataSetChanged();

                Toast.makeText(FavActivity.this, "تم الحذف", Toast.LENGTH_SHORT).show();


            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }


}