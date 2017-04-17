package uca.apps.isi.UbiUCA.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import uca.apps.isi.UbiUCA.Models.Place;
import uca.apps.isi.UbiUCA.R;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {
    private List<Place> lugares;

    public PlaceAdapter(List<Place> lugares) {
        this.lugares = lugares;
    }

    @Override
    public PlaceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_places, parent, false);
        RecyclerView.ViewHolder viewHolder = new ViewHolder(view);
        return (ViewHolder) viewHolder;
    }

    @Override
    public void onBindViewHolder(PlaceAdapter.ViewHolder viewholder, int position) {
        Place place = lugares.get(position);
        viewholder.placeName.setText(place.getName());
        viewholder.placeLocationLat.setText("Latitud: "+place.getLocation().getLat());
        viewholder.placeLocationLng.setText("Longitud: "+place.getLocation().getLng());
        viewholder.place = place;
        viewholder.setOnClickListener();
    }

    @Override
    public int getItemCount() {
        return lugares.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
    {
        Context context;
        TextView placeName;
        TextView placeLocationLat;
        TextView placeLocationLng;
        CardView cardViewPlace;
        Place place;
        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            placeName = (TextView)itemView.findViewById(R.id.tv_placeName);
            placeLocationLat = (TextView)itemView.findViewById(R.id.tv_placeLocationLat);
            placeLocationLng = (TextView)itemView.findViewById(R.id.tv_placeLocationLng);
            cardViewPlace = (CardView) itemView.findViewById(R.id.card_view_lugares);
        }

        public void setOnClickListener(){
            cardViewPlace.setOnClickListener(this);
            cardViewPlace.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "click sobre el lugar: "+place.getName(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onLongClick(View view) {
            Toast.makeText(view.getContext(), "click Long sobre el lugar: "+place.getName(), Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}
