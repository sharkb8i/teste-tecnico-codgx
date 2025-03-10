package com.example.pedidoapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.pedidoapp.R;
import com.example.pedidoapp.utils.MaskMoney;
import com.example.pedidoapp.viewmodel.PedidoViewModel;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

  private PedidoViewModel pedidoViewModel;
  private EditText etNomeCliente, etProduto, etQuantidade, etPrecoTotal;
  private Button btnSalvarPedido, btnVerPedidos;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    etNomeCliente = findViewById(R.id.etNomeCliente);
    etProduto = findViewById(R.id.etProduto);
    etQuantidade = findViewById(R.id.etQuantidade);
    etPrecoTotal = findViewById(R.id.etPrecoTotal);
    btnSalvarPedido = findViewById(R.id.btnSalvarPedido);
    btnVerPedidos = findViewById(R.id.btnVerPedidos);

    MaskMoney.apply(etPrecoTotal);

    pedidoViewModel = new ViewModelProvider(this).get(PedidoViewModel.class);

    /*
    etPrecoTotal.addTextChangedListener(new TextWatcher() {
      private boolean isFormatting = false;

      @Override
      public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

      @Override
      public void onTextChanged(CharSequence charSequence, int start, int before, int after) {}

      @Override
      public void afterTextChanged(Editable editable) {
        if (isFormatting) return;

        String input = editable.toString();
        if (!input.isEmpty()) {
          input = input.replaceAll("[^\\d]", "");

          if (input.length() > 0) {
            long valor = Long.parseLong(input);
            NumberFormat formatter = new DecimalFormat("#,###,###,##0.00");
            String formatted = formatter.format(valor / 100.0);
            int cursorPosition = etPrecoTotal.getSelectionStart();

            etPrecoTotal.setText(formatted);
            etPrecoTotal.setSelection(cursorPosition);
            return;
          }
        }
        isFormatting = false;
      }
    });
    */

    btnSalvarPedido.setOnClickListener(view -> {
      String nomeCliente = etNomeCliente.getText().toString().trim();
      String produto = etProduto.getText().toString().trim();
      String quantidadeStr = etQuantidade.getText().toString().trim();
      String precoTotalStr = etPrecoTotal.getText().toString().trim();

      if (nomeCliente.isEmpty() || produto.isEmpty() || quantidadeStr.isEmpty() || precoTotalStr.isEmpty()) {
        Toast.makeText(MainActivity.this, "Todos os campos são obrigatórios.", Toast.LENGTH_SHORT).show();
        return;
      }

      int quantidade;
      try {
        quantidade = Integer.parseInt(quantidadeStr);
        if (quantidade <= 0) {
          throw new NumberFormatException();
        }
      } catch (NumberFormatException e) {
        Toast.makeText(MainActivity.this, "Quantidade deve ser um número inteiro positivo.", Toast.LENGTH_SHORT).show();
        return;
      }

      double precoTotal;
      try {
        precoTotalStr = precoTotalStr.replaceAll("[^0-9,]", "").replace(",", ".");
        precoTotal = Double.parseDouble(precoTotalStr);
        if (precoTotal <= 0) {
          throw new NumberFormatException();
        }
      } catch (NumberFormatException e) {
        Toast.makeText(MainActivity.this, "Preço total deve ser um número decimal maior que zero.", Toast.LENGTH_SHORT).show();
        return;
      }

      pedidoViewModel.salvarPedido(nomeCliente, produto, quantidade, precoTotal);

      etNomeCliente.setText("");
      etProduto.setText("");
      etQuantidade.setText("");
      etPrecoTotal.setText("");

      Toast.makeText(MainActivity.this, "Pedido salvo com sucesso!", Toast.LENGTH_SHORT).show();
    });

    btnVerPedidos.setOnClickListener(view -> {
      Intent intent = new Intent(MainActivity.this, VerPedidosActivity.class);
      startActivity(intent);
    });
  }
}