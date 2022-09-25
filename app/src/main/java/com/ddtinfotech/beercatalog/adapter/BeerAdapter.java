package com.ddtinfotech.beercatalog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ddtinfotech.beercatalog.Beer;
import com.ddtinfotech.beercatalog.R;

import java.util.List;

// get the data from the model class and set it to the view holder
public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.BeerViewHolder> {

    private Context context;
    private List<Beer> beerList;

    public BeerAdapter(List<Beer> beerList, Context context) {
        this.context = context;
        this.beerList = beerList;
    }

    @NonNull
    @Override
    public BeerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.beer_item, parent, false);
        return new BeerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeerViewHolder holder, int position) {
        Beer beer = beerList.get(position);

//        remove the % sign from the alcohol content
        String alcoholContentStr = beer.getAlcoholContent().replace("%", "");
        double alcoholContent = Double.parseDouble(alcoholContentStr);

        //  alcohol content less than 5%, their name should be displayed in green
        //and the rest of beers should have their name in red.
        if (alcoholContent < 5) {
            holder.txtBeer.setTextColor(context.getResources().getColor(R.color.green));
        } else {
            holder.txtBeer.setTextColor(context.getResources().getColor(R.color.red));
        }

        String txt = beer.getName() + " by " + beer.getBrand() + " ( " + beer.getAlcoholContent() + " Alcohol "+")" ;
        holder.txtBeer.setText(txt);
    }

    @Override
    public int getItemCount() {
        return beerList.size();
    }


    public class BeerViewHolder extends RecyclerView.ViewHolder {

        TextView txtBeer;

        public BeerViewHolder(@NonNull View itemView) {
            super(itemView);
            txtBeer = itemView.findViewById(R.id.txt_beer);
        }
    }
}