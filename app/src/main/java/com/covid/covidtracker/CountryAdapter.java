package com.covid.covidtracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.covid.covidtracker.api.CountryData;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private Context context;
    private List<CountryData> list;

    public CountryAdapter(Context context, List<CountryData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CountryViewHolder(LayoutInflater.from(context).inflate(R.layout.country_item_layout,parent,false));
    }

    public void filterList (List<CountryData> filterList){
        list = filterList;
        notifyDataSetChanged();

    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        CountryData  data = list.get(position);
        holder.country_cases.setText(NumberFormat.getInstance().format(Integer.parseInt(data.getCases())));
        holder.countryName.setText(data.getCountry());
        holder.serialnum.setText(String.valueOf(position+1));

        Map<String,String> img = data.getCountryInfo();
        Glide.with(context).load(img.get("flag")).into(holder.CountryImage);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context,MainActivity.class);
            intent.putExtra("country",data.getCountry());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        list.size();
        return 0;
    }

    public class  CountryViewHolder extends RecyclerView.ViewHolder {

        private TextView serialnum,countryName,country_cases;
        private ImageView CountryImage;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            serialnum = itemView.findViewById(R.id.serial_num);
            country_cases = itemView.findViewById(R.id.Countrycase);
            countryName = itemView.findViewById(R.id.countryName);
            CountryImage = itemView.findViewById(R.id.countryImage);
        }
    }
}
