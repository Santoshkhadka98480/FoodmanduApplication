package com.example.foodmandu.bll;

import com.example.foodmandu.api.UserAPI;
import com.example.foodmandu.serverresponse.SignUpResponse;
import com.example.foodmandu.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBLL {
    boolean isSuccess = false;

    public boolean checkUser(String email, String password){

        UserAPI userAPI = Url.getInstance().create(UserAPI.class);
        Call<SignUpResponse> call = userAPI.checkUser(email, password);

        try {
            Response<SignUpResponse> response = call.execute();
           if (response.isSuccessful()&& response.body().getStatus().equals("Login success!")){
               Url.token += response.body().getToken();
               isSuccess = true;
           }
        } catch (IOException e){
            e.printStackTrace();
        }
        return isSuccess;

    }
}
