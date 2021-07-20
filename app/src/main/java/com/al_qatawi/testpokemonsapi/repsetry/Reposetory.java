package com.al_qatawi.testpokemonsapi.repsetry;

import androidx.lifecycle.LiveData;

import com.al_qatawi.testpokemonsapi.dao.PokemonDao;
import com.al_qatawi.testpokemonsapi.model.Pokemon;
import com.al_qatawi.testpokemonsapi.model.PokemonResults;
import com.al_qatawi.testpokemonsapi.netWork.PokemonApiServes;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class Reposetory {

    private PokemonApiServes pokemonApiServes;
    private PokemonDao pokemonDao;


    @Inject
    public Reposetory(PokemonApiServes pokemonApiServes , PokemonDao pokemonDao) {
        this.pokemonApiServes = pokemonApiServes;
        this.pokemonDao = pokemonDao;
    }


    public Observable<PokemonResults> getPokemons(){

        return pokemonApiServes.getPokemons();
    }

    public void insertPokemon(Pokemon pokemon){

        pokemonDao.insert(pokemon);
    }

    public void deletePokemon(String pokemonName){

        pokemonDao.deletePokemon(pokemonName);
    }

    public LiveData<List<Pokemon>> getFav_Pokemon(){

        return pokemonDao.getPokemon_DB();
    }


}
