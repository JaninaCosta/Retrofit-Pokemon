package com.example.pc_janina.retrofil.pokeapi;

import com.example.pc_janina.retrofil.modelos.PokemonRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by PC-JANINA on 24/2/2018.
 */

public interface PokeapiService {
    //un metodo para obtener la lista de todos los pokemons}

    @GET("pokemon")
    Call<PokemonRespuesta> obtenerListaPokemon(@Query("limit") int limit , @Query("offset") int offset);

}
