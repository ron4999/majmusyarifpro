package com.asyabab.majmusyarifpro.network;

import com.asyabab.majmusyarifpro.model.Items;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;



public interface ApiInterface {
    @GET("{periode}/weekly.json?key=api_key")
    Call<Items> getJadwalSholat(@Path("periode") String periode);

}
