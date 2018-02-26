package com.example.pc_janina.retrofil.modelos;

import java.util.ArrayList;

/**
 * Created by PC-JANINA on 24/2/2018.
 */

public class PokemonRespuesta {
    private ArrayList<Pokemon> results; //obtengo el array

    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }
}
