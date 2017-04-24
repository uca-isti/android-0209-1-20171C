package uca.apps.isi.UbiUCA.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import uca.apps.isi.UbiUCA.Models.Place;
import uca.apps.isi.UbiUCA.Models.PlaceModel;

public interface ApiInterface {
    @GET("Places")
    Call<List<Place>> getPlaces();

}
