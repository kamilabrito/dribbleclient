package com.kamilabrito.meuaptchallenge.api;

import com.kamilabrito.meuaptchallenge.api.model.Shots;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitApi {

    @GET("shots")
    Observable<List<Shots>> getShots(@Query("per_page") int perPage, @Query("access_token") String token);

    @GET("shots/{id}")
    Observable<Shots> getShot(@Path("id") int shotId, @Query("access_token") String token);
}


