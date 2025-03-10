package com.example.pedidoapp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pedidoapp.R;
import com.example.pedidoapp.model.Pedido;
import com.example.pedidoapp.viewmodel.PedidoViewModel;

import java.util.ArrayList;
import java.util.List;

public class VerPedidosActivity extends AppCompatActivity {
  private RecyclerView recyclerViewPedidos;
  private TextView tvNoPedidos;
  ImageView ivBtnVoltar;
  private PedidoViewModel pedidoViewModel;
  private PedidoAdapter pedidoAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_ver_pedidos);

    recyclerViewPedidos = findViewById(R.id.recyclerViewPedidos);
    tvNoPedidos = findViewById(R.id.tvNoPedidos);
    ivBtnVoltar = findViewById(R.id.btnVoltar);

    pedidoViewModel = new PedidoViewModel(getApplication());

    recyclerViewPedidos.setLayoutManager(new LinearLayoutManager(this));
    pedidoAdapter = new PedidoAdapter(this, new ArrayList<>());
    recyclerViewPedidos.setAdapter(pedidoAdapter);

    recyclerViewPedidos.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int totalContagemItem = layoutManager.getItemCount();
        int posicaoUltimoItemVisivel = layoutManager.findLastVisibleItemPosition();

        if (posicaoUltimoItemVisivel == totalContagemItem - 1) {
          carregarMaisPedidos();
        }
      }
    });

    pedidoViewModel.obterPedidos().observe(this, new Observer<List<Pedido>>() {
      @Override
      public void onChanged(List<Pedido> pedidosRecebidos) {
        if (pedidosRecebidos != null) {
          pedidoAdapter = new PedidoAdapter(VerPedidosActivity.this, pedidosRecebidos);
          atualizarLista(pedidosRecebidos);
          recyclerViewPedidos.setAdapter(pedidoAdapter);
        }
      }
    });

    pedidoViewModel.getErro().observe(this, erro -> {
      Toast.makeText(this, erro, Toast.LENGTH_SHORT).show();
    });

    pedidoViewModel.getMostrarMensagemVazia().observe(this, mostrarMensagemVazia -> {
      if (mostrarMensagemVazia) {
        recyclerViewPedidos.setVisibility(View.GONE);
        tvNoPedidos.setVisibility(View.VISIBLE);
      } else {
        recyclerViewPedidos.setVisibility(View.VISIBLE);
        tvNoPedidos.setVisibility(View.GONE);
      }
    });

    ivBtnVoltar.setOnClickListener(v -> onBackPressed());
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
  }

  public void atualizarLista(List<Pedido> pedidos) {
    if (pedidos == null || pedidos.isEmpty()) {
      recyclerViewPedidos.setVisibility(View.GONE);
      tvNoPedidos.setVisibility(View.VISIBLE);
    } else {
      recyclerViewPedidos.setVisibility(View.VISIBLE);
      tvNoPedidos.setVisibility(View.GONE);
    }
  }

  private void carregarMaisPedidos() {
    pedidoViewModel.carregarPedidos();
  }
}