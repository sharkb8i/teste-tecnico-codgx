package com.example.pedidoapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

  private static final String BASE_URL_EMULADOR = "http://10.0.2.2:5123/api/";
  private static final String BASE_URL_DISP_FISICO = "http://192.168.1.16:5123/api/";
  private static String BASE_URL;

  private static Retrofit retrofit;

  public static Retrofit getRetrofitInstance() {
    if (retrofit == null) {
      BASE_URL = isRunningOnEmulator() ? BASE_URL_EMULADOR : BASE_URL_DISP_FISICO;

      retrofit = new Retrofit.Builder()
              .baseUrl(BASE_URL)
              .addConverterFactory(GsonConverterFactory.create())
              .build();
    }
    return retrofit;
  }

  private static boolean isRunningOnEmulator() {
    String knownEmulator = "generic";
    String manufacturer = android.os.Build.MANUFACTURER;
    return manufacturer != null && manufacturer.toLowerCase().contains(knownEmulator);
  }
}