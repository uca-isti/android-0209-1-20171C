package uca.apps.isi.UbiUCA.Frangments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uca.apps.isi.UbiUCA.API.Api;
import uca.apps.isi.UbiUCA.Adapters.PlaceAdapter;
import uca.apps.isi.UbiUCA.Models.Place;
import uca.apps.isi.UbiUCA.R;

import  com.chocoyo.labs.adapters.progress.AdapterProgress;

public class LugaresFragment extends Fragment {
    RecyclerView recyclerViewPlace;
    LinearLayoutManager linearLayoutManagerPlace;
    RecyclerView.Adapter adapterPlace;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lugares, container, false);
        recyclerViewPlace = (RecyclerView)view.findViewById(R.id.rv_place);
        recyclerViewPlace.setHasFixedSize(true);
        linearLayoutManagerPlace = new LinearLayoutManager(getActivity());
        linearLayoutManagerPlace.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewPlace.setLayoutManager(linearLayoutManagerPlace);
        recyclerViewPlace.setAdapter(new AdapterProgress());
        getPlacesFromApi();
        return view;
    }

    private void getPlacesFromApi() {
        Call<List<Place>> callPlace = Api.instance().getPlaces();
        callPlace.enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                System.out.println("Peticion correcta, todo Bien!");
                Toast.makeText(getContext(), "Peticion correcta, todo Bien!", Toast.LENGTH_SHORT).show();
                List<Place> lugares = response.body();
                adapterPlace = new PlaceAdapter(lugares);
                recyclerViewPlace.setAdapter(adapterPlace);
            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {
                System.out.println("Ha ocurrido un error en la peticion a la API");
                Toast.makeText(getContext(), "Ha ocurrido un error en la peticion a la API", Toast.LENGTH_SHORT).show();
                List<Place> lugares = new ArrayList<Place>();
                adapterPlace = new PlaceAdapter(lugares);
                recyclerViewPlace.setAdapter(adapterPlace);
            }
        });
    }
}
