package uca.apps.isi.UbiUCA.Adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
    uca.apps.isi.UbiUCA.Models.Location location;
    //private SimpleLocation location;

    public PlaceAdapter(List<Place> lugares, uca.apps.isi.UbiUCA.Models.Location location) {
        this.lugares = lugares;
        this.location = location;
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

    public void resume(){
        /*if (location != null){
            location.beginUpdates();
            System.out.println("ACTUALIZAR LOCACION");
        }
        else
            System.out.print("Location es NULL");*/
    }

    public void pause(){
       /* if (location != null){
            location.endUpdates();
            System.out.println("PAUSAR LOCACION");
        }
        else
            System.out.print("Location es NULL");*/
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
        public boolean onLongClick(final View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Ir al lugar");
            builder.setMessage("¿Desea ir al sitio seleccionado?");
            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    verLugarNuestro(view);
                }
            });
            builder.setNegativeButton("cancelar", null);
            Dialog dialog = builder.create();
            dialog.show();
            return true;
        }
        public void verLugarNuestro(View view){
            Toast.makeText(view.getContext(), "Ir a: "+place.getName(), Toast.LENGTH_SHORT).show();

            /*location = new SimpleLocation(view.getContext());
            // if we can't access the location yet
            if (!location.hasLocationEnabled()) {
                // ask the user to enable location access
                SimpleLocation.openSettings(view.getContext());
            }

            final double latitude = location.getLatitude();
            final double longitude = location.getLongitude();*/
            final double latitude = location.getLat();
            final double longitude = location.getLng();
            System.out.println("latitud: "+latitude+", longitud: "+longitude);
            Toast.makeText(view.getContext(), "Nuestra Posicion:\nlatitud: "+latitude+"\nlongitud: "+longitude
                    , Toast.LENGTH_LONG).show();
        }
    }
}
