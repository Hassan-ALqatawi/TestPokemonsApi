package com.al_qatawi.testpokemonsapi.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.al_qatawi.testpokemonsapi.dao.PokemonDao;
import com.al_qatawi.testpokemonsapi.model.Pokemon;

@Database(entities = Pokemon.class,version = 1,exportSchema = false)
public abstract class PokemonDB extends RoomDatabase {

    public abstract PokemonDao pokemonDao();

}
