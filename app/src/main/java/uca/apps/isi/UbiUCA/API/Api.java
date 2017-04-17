package uca.apps.isi.UbiUCA.API;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static final String API_URL = "https://api-0209-1.herokuapp.com/api/";

    public static String getBase() {
        return API_URL;
    }

    public static ApiInterface instance() {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Api.getBase())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiInterface.class);
    }
}
