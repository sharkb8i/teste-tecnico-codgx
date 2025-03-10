package com.example.pedidoapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pedidoapp.R;
import com.example.pedidoapp.model.Pedido;

import java.util.List;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder> {

  private Context context;
  private List<Pedido> pedidos;

  public PedidoAdapter(Context context, List<Pedido> pedidos) {
    this.context = context;
    this.pedidos = pedidos;
  }

  @Override
  public PedidoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.item_pedido, parent, false);
    return new PedidoViewHolder(view);
  }

  @Override
  public void onBindViewHolder(PedidoViewHolder holder, int position) {
    Pedido pedido = pedidos.get(position);
    holder.tvNomeCliente.setText(pedido.getNomeCliente());
    holder.tvProduto.setText(pedido.getProduto());
    holder.tvQuantidade.setText(String.valueOf(pedido.getQuantidade()));
    holder.tvPrecoTotal.setText("R$ " + String.format("%.2f", pedido.getPrecoTotal()));
  }

  @Override
  public int getItemCount() {
    return pedidos.size();
  }

  public static class PedidoViewHolder extends RecyclerView.ViewHolder {
    TextView tvNomeCliente, tvProduto, tvQuantidade, tvPrecoTotal;

    public PedidoViewHolder(View itemView) {
      super(itemView);
      tvNomeCliente = itemView.findViewById(R.id.tvNomeCliente);
      tvProduto = itemView.findViewById(R.id.tvProduto);
      tvQuantidade = itemView.findViewById(R.id.tvQuantidade);
      tvPrecoTotal = itemView.findViewById(R.id.tvPrecoTotal);
    }
  }
}