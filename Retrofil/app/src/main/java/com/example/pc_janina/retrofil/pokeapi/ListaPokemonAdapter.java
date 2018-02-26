package com.example.pc_janina.retrofil.pokeapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pc_janina.retrofil.R;
import com.example.pc_janina.retrofil.modelos.Pokemon;

import java.util.ArrayList;

/**
 * Created by PC-JANINA on 24/2/2018.
 */

public class ListaPokemonAdapter extends RecyclerView.Adapter<ListaPokemonAdapter.ViewHolder> {

    private ArrayList<Pokemon> dataset;
    Context context;


    public ListaPokemonAdapter(Context context) {
        this.context = context;
        this.dataset = new ArrayList<Pokemon>();
    }


    @Override
    public ListaPokemonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, null);
        // crea un viewholder
        RecyclerView.ViewHolder viewHolder = new ListaPokemonAdapter.ViewHolder(itemLayoutView);
        return (ViewHolder) viewHolder;
    }

    @Override
    public void onBindViewHolder(ListaPokemonAdapter.ViewHolder holder, int position) {
        holder.texto.setText(dataset.get(position).getName());
        //holder.imagen.setImageResource(dataset.get(position).getName());

        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+dataset.get(position).getNumber()+".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imagen);


    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adiccionarListaPokemon(ArrayList<Pokemon> listaPokemon){
        dataset.addAll(listaPokemon);  //adALl adiciona la nueva informacion sin remplazarla
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView texto;
        private ImageView imagen;

        public ViewHolder(View itemView) {
            super(itemView);
            texto = (TextView) itemView.findViewById(R.id.nombre_pokemon);
            imagen = (ImageView) itemView.findViewById(R.id.imagen_pokemon);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
