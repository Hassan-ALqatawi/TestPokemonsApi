package com.al_qatawi.testpokemonsapi.viewModels;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.al_qatawi.testpokemonsapi.model.Pokemon;
import com.al_qatawi.testpokemonsapi.model.PokemonResults;
import com.al_qatawi.testpokemonsapi.repsetry.Reposetory;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PokemonViewModel extends ViewModel {
    private static final String TAG = "PokemonViewModel";

    Reposetory reposetory;
   private LiveData<List<Pokemon>> pokemonListDB = null;

    @ViewModelInject
    public PokemonViewModel(Reposetory reposetory) {
        this.reposetory = reposetory;
    }

    MutableLiveData<ArrayList<Pokemon>> pokemonList = new MutableLiveData<>();


    public LiveData<List<Pokemon>> getPokemonListDB() {
        return pokemonListDB;
    }

    public MutableLiveData<ArrayList<Pokemon>> getPokemonList() {

        return pokemonList;

    }

    @SuppressLint("CheckResult")
    public void getPokemon() {

        reposetory.getPokemons()
                .subscribeOn(Schedulers.io())
                .map(new Function<PokemonResults, ArrayList<Pokemon>>() {
                    @Override
                    public ArrayList<Pokemon> apply(PokemonResults pokemonResults) throws Throwable {

                        ArrayList<Pokemon> list = pokemonResults.getResults();
                        for (Pokemon pokemon : list) {

                            String url = pokemon.getUrl();

                            String[] pokemonIndex = url.split("/");
                            pokemon.setUrl("https://pokeres.bastionbot.org/images/pokemon/"
                                    + pokemonIndex[pokemonIndex.length - 1] + ".png");

                        }


                        return list;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(p -> pokemonList.setValue(p), error -> Log.d(TAG, "getPokemon: " + error));
    }

    public void insertPokemon(Pokemon pokemon) {

        reposetory.insertPokemon(pokemon);
    }

    public void deletePokemon(String pokemonName) {

        reposetory.deletePokemon(pokemonName);
    }

    public void getPokemon_DB() {

       pokemonListDB = reposetory.getFav_Pokemon();

    }


}
