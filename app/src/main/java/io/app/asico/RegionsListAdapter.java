package io.app.asico;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.app.asico.model.Border;
import io.app.asico.model.Language;
import io.app.asico.model.Region;

public class RegionsListAdapter extends RecyclerView.Adapter<RegionsListAdapter.ViewHolder> {
    private List<Region> regionsList;

    public RegionsListAdapter(List<Region> regions) {
        regionsList = regions;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView capital;
        public TextView region;
        public TextView subRegion;
        public TextView borders;
        public TextView languages;
        public TextView population;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           name= (TextView) itemView.findViewById(R.id.textViewName);
           capital=itemView.findViewById(R.id.textViewCapital);
           region=itemView.findViewById(R.id.textViewRegion);
           subRegion=itemView.findViewById(R.id.textViewSubregion);
           borders=itemView.findViewById(R.id.textViewBorders);
           languages=itemView.findViewById(R.id.textViewLanguages);
           population=itemView.findViewById(R.id.textViewPopulation);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.country_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         Region regionDetail= regionsList.get(position);
         String countryName=regionDetail.getName();
         String capital=regionDetail.getCapital();
         String region=regionDetail.getRegion();
         String subRegion=regionDetail.getSubregion();
         String population=regionDetail.getPopulation();
         List<Language> languages=regionDetail.getLanguages();
         List<Border> borders=regionDetail.getBorders();

         holder.name.setText("Name = "+countryName);
         holder.capital.setText("Capital = "+capital);
         holder.region.setText("Region = "+region);
         holder.subRegion.setText("Subregion = "+subRegion);
         holder.population.setText("Population = "+population);
         holder.languages.setText("Languages = "+languages);
         holder.borders.setText("Borders = "+borders);
    }

    @Override
    public int getItemCount() {
        return regionsList.size();
    }
}
