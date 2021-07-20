package com.al_qatawi.testpokemonsapi.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.al_qatawi.testpokemonsapi.model.Pokemon;

import java.util.List;

@Dao
public interface PokemonDao {

    @Insert
    public void insert(Pokemon pokemon);



    @Query("delete From fav_table where name =:pokemonName")
    public void deletePokemon(String pokemonName);


    @Query("SELECT * FROM FAV_TABLE")
    public LiveData<List<Pokemon>> getPokemon_DB();

}
