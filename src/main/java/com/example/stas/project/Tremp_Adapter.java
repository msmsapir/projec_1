package com.example.stas.project;

/**
 * Created by stas on 17/04/16.
 */

import java.io.InputStream;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;

public class Tremp_Adapter extends ArrayAdapter<Tremps> {
    ArrayList<Tremps> actorList;
    LayoutInflater vi;
    int Resource;
    ViewHolder holder;

    public Tremp_Adapter(Context context, int resource, ArrayList<Tremps> objects) {
        super(context, resource, objects);
        vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        actorList = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convert view = design
        View v = convertView;
        if (v == null) {
            holder = new ViewHolder();
            v = vi.inflate(Resource, null);
            holder.profilePictureView = (ProfilePictureView) v.findViewById(R.id.picture);
            holder.id = (TextView) v.findViewById(R.id.id);
            holder.source = (TextView) v.findViewById(R.id.source);
            holder.dest = (TextView) v.findViewById(R.id.dest);
            holder.driver_id = (TextView) v.findViewById(R.id.driver_id);
            holder.out_time = (TextView) v.findViewById(R.id.out_time);
            holder.arrive_time = (TextView) v.findViewById(R.id.arrive_time);
            holder.posted_at = (TextView) v.findViewById(R.id.posted_at);
            holder.no_p = (TextView) v.findViewById(R.id.nof_p);
            holder.maslolim = (TextView) v.findViewById(R.id.mas);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        // holder.imageview.setImageResource(R.drawable.ic_launcher);
        // new DownloadImageTask(holder.imageview).execute(actorList.get(position).getImage());
        holder.profilePictureView.setProfileId(actorList.get(position).getTrempDriverId());
        holder.id.setText(actorList.get(position).getTrempID());
        holder.source.setText(actorList.get(position).getTrempSource());
        holder.dest.setText("B'day: " + actorList.get(position).getTrempDest());
        holder.driver_id.setText(actorList.get(position).getTrempDriverId());
        holder.out_time.setText("Height: " + actorList.get(position).getTrempOutTime());
        holder.arrive_time.setText("Spouse: " + actorList.get(position).getTrempArriveTime());
        holder.posted_at.setText("Children: " + actorList.get(position).getTrempPostedAt());
        holder.no_p.setText("Spouse: " + actorList.get(position).getTrempNumberP());
        holder.maslolim.setText("Children: " + actorList.get(position).getTrempMaslulim());
        return v;

    }

    static class ViewHolder {
        public ProfilePictureView profilePictureView;
        public TextView id;
        public TextView source;
        public TextView dest;
        public TextView driver_id;
        public TextView out_time;
        public TextView arrive_time;
        public TextView posted_at;
        public TextView no_p;
        public TextView maslolim;

    }
/*
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }

    }
    */
}