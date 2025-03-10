package com.example.pedidoapp.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import java.text.NumberFormat;
import java.util.Locale;

public class MaskMoney {

  public static void apply(final EditText editText) {
    editText.addTextChangedListener(new TextWatcher() {
      private final NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
      private String current = "";

      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!s.toString().equals(current)) {
          editText.removeTextChangedListener(this);

          String cleanString = s.toString().replaceAll("[^\\d]", "").trim();
          if (!cleanString.isEmpty()) {
            double parsed = Double.parseDouble(cleanString) / 100;
            current = format.format(parsed);
            editText.setText(current);
            editText.setSelection(current.length());
          }

          editText.addTextChangedListener(this);
        }
      }

      @Override
      public void afterTextChanged(Editable s) {}
    });
  }
}