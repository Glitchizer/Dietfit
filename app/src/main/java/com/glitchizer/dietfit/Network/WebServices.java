package com.glitchizer.dietfit.Network;

import com.glitchizer.dietfit.Modules.Result;
import com.glitchizer.dietfit.Modules.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WebServices {


    @POST("login")
    Call<Result> login(@Body User user);

}
