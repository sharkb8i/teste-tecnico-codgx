package com.example.pedidoapp.network;

import com.example.pedidoapp.model.Pedido;
import com.example.pedidoapp.model.RespostaApi;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PedidoService {
  @GET("pedidos")
  Call<RespostaApi> obterPedidos(@Query("pagina") int pagina,
                                  @Query("tamanhoPagina") int tamanhoPagina);
  @POST("pedidos")
  Call<RespostaApi> salvarPedido(@Body Pedido pedido);
}