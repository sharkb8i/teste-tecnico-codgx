package com.example.pedidoapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.pedidoapp.model.Pedido;
import com.example.pedidoapp.model.RespostaApi;
import com.example.pedidoapp.network.PedidoService;
import com.example.pedidoapp.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

public class PedidoViewModel extends AndroidViewModel {

  private MutableLiveData<List<Pedido>> pedidosLiveData;
  private MutableLiveData<String> erroLiveData;
  private MutableLiveData<Boolean> mostrarMensagemVaziaLiveData;
  private PedidoService pedidoService;

  public int totalPaginas = -1;
  public boolean carregando = false;
  public int paginaAtual = 1;
  private static final int TAMANHO_PAGINA = 10;

  public PedidoViewModel(@NonNull Application application) {
    super(application);
    pedidosLiveData = new MutableLiveData<>();
    erroLiveData = new MutableLiveData<>();
    mostrarMensagemVaziaLiveData = new MutableLiveData<>();
    pedidoService = RetrofitClient.getRetrofitInstance().create(PedidoService.class);
  }

  public LiveData<List<Pedido>> obterPedidos() {
    if (pedidosLiveData.getValue() == null) {
      carregarPedidos();
    }
    return pedidosLiveData;
  }

  public LiveData<Boolean> getMostrarMensagemVazia() {
    return mostrarMensagemVaziaLiveData;
  }

  public void salvarPedido(String nomeCliente, String produto, int quantidade, double precoTotal) {
    Pedido pedido = new Pedido(nomeCliente, produto, quantidade, precoTotal);
    pedidoService.salvarPedido(pedido).enqueue(new retrofit2.Callback<RespostaApi>() {
      @Override
      public void onResponse(retrofit2.Call<RespostaApi> call, retrofit2.Response<RespostaApi> response) {
        RespostaApi resposta = response.body();

        if (response.isSuccessful()) {
          carregarPedidos();
        } else {
          if (resposta != null) {
            erroLiveData.setValue(resposta.getErro());
          }
        }
      }

      @Override
      public void onFailure(retrofit2.Call<RespostaApi> call, Throwable t) {
        erroLiveData.setValue("Falha na requisição: " + t.getMessage());
      }
    });
  }

  public void carregarPedidos() {
    if (carregando || (totalPaginas != -1 && paginaAtual > totalPaginas)) return;

    carregando = true;
    pedidoService.obterPedidos(paginaAtual, TAMANHO_PAGINA).enqueue(new retrofit2.Callback<RespostaApi>() {
      @Override
      public void onResponse(retrofit2.Call<RespostaApi> call, retrofit2.Response<RespostaApi> response) {
        if (response.isSuccessful() && response.body() != null) {
          RespostaApi respostaApi = response.body();
          List<Pedido> pedidos = respostaApi.getDados();
          totalPaginas = respostaApi.getTotalPaginas();
          paginaAtual = respostaApi.getPaginaAtual();

          if (pedidos.isEmpty()) {
            mostrarMensagemVaziaLiveData.setValue(true);
          } else {
            mostrarMensagemVaziaLiveData.setValue(false);
            List<Pedido> pedidosAtuais = pedidosLiveData.getValue();
            if (pedidosAtuais == null) {
              pedidosAtuais = new ArrayList<>();
            }
            pedidosAtuais.addAll(pedidos);
            pedidosLiveData.setValue(pedidosAtuais);

            paginaAtual++;
          }
        } else {
          erroLiveData.setValue("Erro ao carregar pedidos.");
          mostrarMensagemVaziaLiveData.setValue(true);
        }

        carregando = false;
      }

      @Override
      public void onFailure(retrofit2.Call<RespostaApi> call, Throwable t) {
        erroLiveData.setValue("Falha na requisição: " + t.getMessage());
        mostrarMensagemVaziaLiveData.setValue(true);
        carregando = false;
      }
    });
  }

  public LiveData<String> getErro() {
    return erroLiveData;
  }
}