package com.example.pc_janina.retrofil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.pc_janina.retrofil.modelos.Pokemon;
import com.example.pc_janina.retrofil.modelos.PokemonRespuesta;
import com.example.pc_janina.retrofil.pokeapi.ListaPokemonAdapter;
import com.example.pc_janina.retrofil.pokeapi.PokeapiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Retrofit retrofit;
    public static final String TAG = "POKEDEX";

    //instanciar recycler y adapter
    private RecyclerView recyclerView;
    private ListaPokemonAdapter listaPokemonAdapter;

    private int offset;
    private boolean aptoParaCargar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recycler
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        listaPokemonAdapter = new ListaPokemonAdapter(this);
        recyclerView.setAdapter(listaPokemonAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(dy > 0) { //si el scroll es hacia abajo
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItens = layoutManager.findFirstVisibleItemPosition();

                    if(aptoParaCargar){
                        if((visibleItemCount + pastVisibleItens)>= totalItemCount){
                            Log.i (TAG, "llegamos al final");

                            aptoParaCargar = false;
                            offset +=20;
                            obtenerDatos(offset);
                        }
                    }
                }
            }
        });


        retrofit = new Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        aptoParaCargar = true;
        offset = 0;
        obtenerDatos(offset);

    }

    private void obtenerDatos(int offset){
        PokeapiService service = retrofit.create(PokeapiService.class);
        Call<PokemonRespuesta> pokemonRespuestaCall = service.obtenerListaPokemon(20,0);
        pokemonRespuestaCall.enqueue(new Callback<PokemonRespuesta>() {

            @Override
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {
                Log.i(TAG, "entra aqui 1");
                aptoParaCargar = true;
                if(response.isSuccessful()){
                    Log.i(TAG, "entra aqui 2");
                    PokemonRespuesta pokemonRespuesta = response.body();
                    ArrayList<Pokemon> listaPokemon = pokemonRespuesta.getResults();

                    listaPokemonAdapter.adiccionarListaPokemon(listaPokemon);

                }else{
                    Log.e(TAG, "onResponse ------------------> error " + response.errorBody());
                    Log.i(TAG, "entra aqui 4");
                }
                Log.i(TAG, "entra aqui 5");
            }

            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {
                aptoParaCargar = true;
                Log.e(TAG, "onFailure -------------------> error" + t.getMessage());
                Log.i(TAG, "entra aqui 6");
            }
        });
    }
}
