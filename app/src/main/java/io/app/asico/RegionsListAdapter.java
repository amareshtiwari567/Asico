package io.app.asico;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;

import java.io.InputStream;
import java.util.List;

import io.app.asico.model.Language;
import io.app.asico.model.Region;
import io.app.asico.svghandler.SvgDecoder;
import io.app.asico.svghandler.SvgDrawableTranscoder;
import io.app.asico.svghandler.SvgSoftwareLayerSetter;

public class RegionsListAdapter extends RecyclerView.Adapter<RegionsListAdapter.ViewHolder> {
    private List<Region> regionsList;
    private Context context;

    public RegionsListAdapter(Context context, List<Region> regions) {
        this.context = context;
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
        public ImageView flagImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            flagImage = itemView.findViewById(R.id.imageView_flag);
            name = (TextView) itemView.findViewById(R.id.textViewName);
            capital = itemView.findViewById(R.id.textViewCapital);
            region = itemView.findViewById(R.id.textViewRegion);
            subRegion = itemView.findViewById(R.id.textViewSubregion);
            borders = itemView.findViewById(R.id.textViewBorders);
            languages = itemView.findViewById(R.id.textViewLanguages);
            population = itemView.findViewById(R.id.textViewPopulation);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.country_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Region regionDetail = regionsList.get(position);
        String countryName = regionDetail.getName();
        String capital = regionDetail.getCapital();
        String region = regionDetail.getRegion();
        String subRegion = regionDetail.getSubregion();
        String population = regionDetail.getPopulation();
        List<Language> languages = regionDetail.getLanguages();
        String languageString = languages.get(0).toString();
        for (int i = 1; i < languages.size(); i++) {
            languageString += "\n"+", " + languages.get(i).toString();
        }
        String borderString;
        List<String> borders = regionDetail.getBorders();
        try {
             borderString = borders.get(0);
            for (int i = 1; i < borders.size(); i++) {
                borderString += "\n"+", " + borders.get(i);
            }
        }catch (IndexOutOfBoundsException e){
             borderString=null;
        }
        String flag=regionDetail.getFlag();
        Log.d("TAG",flag);



        GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder = Glide.with(context)
                .using(Glide.buildStreamModelLoader(Uri.class, context), InputStream.class)
                .from(Uri.class)
                .as(SVG.class)
                .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                .sourceEncoder(new StreamEncoder())
                .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                .decoder(new SvgDecoder())
                .listener(new SvgSoftwareLayerSetter<Uri>());

        requestBuilder.diskCacheStrategy(DiskCacheStrategy.NONE)
                .load(Uri.parse(flag))
                .into(holder.flagImage);



        holder.name.setText("Name = " + countryName);
        holder.capital.setText("Capital = " + capital);
        holder.region.setText("Region = " + region);
        holder.subRegion.setText("Subregion = " + subRegion);
        holder.population.setText("Population = " + population);
        holder.borders.setText("Borders = " + borderString);
        holder.languages.setText("Languages = " + languageString);
    }

    @Override
    public int getItemCount() {
        return regionsList.size();
    }
}
