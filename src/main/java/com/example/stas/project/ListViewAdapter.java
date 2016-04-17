package com.example.stas.project;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ListViewAdapter extends BaseAdapter {

    EditText editsearch;
    Context mContext;
    String stop_code;
    LayoutInflater inflater;
    private List<Stop> worldpopulationlist = null;
    private ArrayList<Stop> arraylist;

    public ListViewAdapter(Context context, List<Stop> worldpopulationlist) {
        mContext = context;
        this.worldpopulationlist = worldpopulationlist;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Stop>();
        this.arraylist.addAll(worldpopulationlist);
    }

    public class ViewHolder {
        TextView rank;
        TextView country;
        TextView population;
    }

    @Override
    public int getCount() {
        return worldpopulationlist.size();
    }

    @Override
    public Stop getItem(int position) {
        return worldpopulationlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.activity_list_view_adapter, null);

            // Locate the TextViews in listview_item.xml
            holder.rank = (TextView) view.findViewById(R.id.rank);
            holder.country = (TextView) view.findViewById(R.id.country);
            holder.population = (TextView) view.findViewById(R.id.population);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.rank.setText(worldpopulationlist.get(position).getRank());
        holder.country.setText(worldpopulationlist.get(position).getCountry());
        holder.population.setText(worldpopulationlist.get(position).getPopulation());

        // Listen for ListView Item Click
        view.setOnClickListener(new View.OnClickListener() {


            public void onClick(View arg0) {


                if (worldpopulationlist.get(position).getLine().equals("1")) {


                    Post.editsearch.setText(worldpopulationlist.get(position).getRank());


                } else {
                    Post.editsearch2.setText(worldpopulationlist.get(position).getRank());

                }
            }


        });

        return view;

    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        worldpopulationlist.clear();
        if (charText.length() == 0) {
            worldpopulationlist.addAll(arraylist);
        }
        else
        {
            for (Stop wp : arraylist)
            {
                if (wp.getCountry().toLowerCase(Locale.getDefault()).contains(charText) || wp.getPopulation().toLowerCase(Locale.getDefault()).contains(charText) ||  wp.getRank().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    worldpopulationlist.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
