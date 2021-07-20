package com.al_qatawi.testpokemonsapi.di;


import android.app.Application;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.al_qatawi.testpokemonsapi.dao.PokemonDB;
import com.al_qatawi.testpokemonsapi.dao.PokemonDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DatabaesMduel {

    @Provides
    @Singleton
    public static PokemonDB providerDB(Application application){

        return Room.databaseBuilder(application,PokemonDB.class,"fav_DB")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

    }

    @Provides
    @Singleton
    public static PokemonDao pokemonDao(PokemonDB pokemonDB){

        return pokemonDB.pokemonDao();
    }


}
