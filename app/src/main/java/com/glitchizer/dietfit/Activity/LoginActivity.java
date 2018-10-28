package com.glitchizer.dietfit.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.glitchizer.dietfit.Modules.Result;
import com.glitchizer.dietfit.Modules.User;
import com.glitchizer.dietfit.Network.WebServices;
import com.glitchizer.dietfit.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private Button bt_login;
    private EditText et_mobile;
    private Dialog dialog;
    private Retrofit retrofit;
    private WebServices apiService;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new Dialog(LoginActivity.this);
        dialog.setContentView(R.layout.activity_login);
        et_mobile = dialog.findViewById(R.id.et_mobile);
        bt_login = dialog.findViewById(R.id.bt_login);
        dialog.show();
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofit = new Retrofit.Builder()
                        .baseUrl("http://dietFit.glitchizer.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                apiService = retrofit.create(WebServices.class);
                user = new User();
                user.setMobile(et_mobile.getText().toString());
                Call<Result> reg = apiService.login(user);
                reg.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        try {
                            if (response.message().equalsIgnoreCase("ok")) {
                                startActivity(new Intent(LoginActivity.this , HomeActivity.class));
                            }
                            else
                                Toast.makeText(getBaseContext(), response.message(), Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        Toast.makeText(getBaseContext(), t.toString(), Toast.LENGTH_LONG).show();
                    }
                });


        }
        });


    }

}
