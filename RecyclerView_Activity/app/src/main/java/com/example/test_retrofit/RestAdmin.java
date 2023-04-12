package com.example.test_retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestAdmin {
    @GET("test")
    Call<List<admin>> getMyInfo();

}
