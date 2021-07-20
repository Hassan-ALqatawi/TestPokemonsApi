package com.al_qatawi.testpokemonsapi.netWork;

import com.al_qatawi.testpokemonsapi.model.PokemonResults;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface PokemonApiServes {

    @GET("pokemon")
    Observable<PokemonResults> getPokemons();

}
