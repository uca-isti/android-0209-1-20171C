package uca.apps.isi.UbiUCA.Frangments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uca.apps.isi.UbiUCA.API.Api;
import uca.apps.isi.UbiUCA.Adapters.PlaceAdapter;
import uca.apps.isi.UbiUCA.Models.Location;
import uca.apps.isi.UbiUCA.Models.Place;
import uca.apps.isi.UbiUCA.Models.PlaceModel;
import uca.apps.isi.UbiUCA.R;

import  com.chocoyo.labs.adapters.progress.AdapterProgress;

public class LugaresFragment extends Fragment {
    RecyclerView recyclerViewPlace;
    LinearLayoutManager linearLayoutManagerPlace;
    PlaceAdapter placeAdapter;
    uca.apps.isi.UbiUCA.Models.Location location;

    public LugaresFragment(uca.apps.isi.UbiUCA.Models.Location location){
        this.location = location;
    }

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

    @Override
    public void onResume() {
        super.onResume();
        /*if(placeAdapter != null){
            placeAdapter.resume();
            System.out.println("RESUMIR");
        }
        else
            System.out.println("No se hara nada pues placeAdapter es NULL");*/
    }

    @Override
    public void onPause() {
        /*placeAdapter.pause();
        System.out.println("PAUSAR");*/
        super.onPause();
    }

    private void getPlacesFromApi() {
        Call<List<Place>> callPlace = Api.instance().getPlaces();
        callPlace.enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                System.out.println("Peticion correcta, todo Bien!");
                Toast.makeText(getContext(), "Peticion correcta, todo Bien!", Toast.LENGTH_SHORT).show();
                List<Place> lugares = response.body();
                deletePlaces();
                deleteLocations();
                insertPlaces(lugares);
                List<Place> places = getLocalData();
                placeAdapter = new PlaceAdapter(places, location);
                recyclerViewPlace.setAdapter(placeAdapter);
            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {
                System.out.println("Ha ocurrido un error en la peticion a la API");
                Toast.makeText(getContext(), "Ha ocurrido un error al conectarse, cargar datos locales", Toast.LENGTH_LONG).show();
                List<Place> lugares = getLocalData();
                placeAdapter = new PlaceAdapter(lugares, location);
                recyclerViewPlace.setAdapter(placeAdapter);
            }
        });
    }

    private void deletePlaces() {
        final RealmResults<Place> lugaresLocal = (RealmResults<Place>)getLocalData();
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                lugaresLocal.deleteAllFromRealm();
            }
        });
    }
    private void deleteLocations() {
        final RealmResults<Location> locacionesLocal = (RealmResults<Location>)getLocalDataLocation();
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                locacionesLocal.deleteAllFromRealm();
            }
        });
    }

    private void insertPlaces(List<Place> places) {
        for (Place place: places) {
            insertDataAll(place);
        }
    }

    private void insertDataAll(Place place) {
        System.out.println("lugar: "+place.getName());
        System.out.println("latitud: "+place.getLocation().getLat());

        Realm realm1 = Realm.getDefaultInstance();

        realm1.beginTransaction();
        Location location = realm1.createObject(Location.class);
        location.setLat(place.getLocation().getLat());
        location.setLng(place.getLocation().getLng());
        realm1.commitTransaction();

        Realm realm2 = Realm.getDefaultInstance();

        realm2.beginTransaction();
        Place place1= realm2.createObject(Place.class);
        place1.setName(place.getName());
        place1.setLocation(location);
        realm2.commitTransaction();
    }

    private List<Place> getLocalData(){
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<Place> placeRealmResults = realm.where(Place.class).findAll();
        for(Place place : placeRealmResults) {
            System.out.println("nombre: "+place.getName());
            System.out.println("locación: "+place.getLocation().getLat()+", "+place.getLocation().getLng());
        }
        return placeRealmResults;
    }

    private List<Location> getLocalDataLocation(){
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<Location> locationRealmResults = realm.where(Location.class).findAll();
        for (Location location: locationRealmResults) {
            System.out.println("lat: "+location.getLat()+", long: "+location.getLng());
        }
        return locationRealmResults;
    }
}
