package com.example.movie.network;

import com.example.movie.App;
import com.example.movie.activity.LoginActivity;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private static ApiManager instance;
    private static ApiService service;

    private ApiManager() {
    }

    public static ApiManager getInstance() {
        if (instance == null) {
            instance = new ApiManager();
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS);

            httpClient.addInterceptor(chain -> {
                Request original = chain.request();
                // Request customization: add request headers
                String accessToken = Constant.ACCESS_TOKEN;
                if (accessToken == null) {
                    accessToken = "";
                }
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", accessToken)
                        .method(original.method(), original.body());

                Request request = requestBuilder.build();
                Response response = chain.proceed(request);
                if (response.code() == 401){
                    LoginActivity.goLogin(App.getContext());
                }

                return response;
            });
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.level(HttpLoggingInterceptor.Level.BASIC);
            interceptor.level(HttpLoggingInterceptor.Level.BODY);
            interceptor.level(HttpLoggingInterceptor.Level.HEADERS);
            httpClient.addInterceptor(interceptor);

            OkHttpClient client = httpClient.build();

            service = new Retrofit.Builder()
                    .baseUrl(ApiService.SERVER)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                    .create(ApiService.class);
        }
        return instance;
    }

    public static ApiService getService() {
        if (service == null) {
            getInstance();
        }
        return service;
    }
}
