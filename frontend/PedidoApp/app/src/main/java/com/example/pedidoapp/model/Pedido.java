package com.example.pedidoapp.model;

public class Pedido {

  private int id;
  private String nomeCliente;
  private String produto;
  private int quantidade;
  private double precoTotal;

  public Pedido(String nomeCliente, String produto, int quantidade, double precoTotal) {
    this.nomeCliente = nomeCliente;
    this.produto = produto;
    this.quantidade = quantidade;
    this.precoTotal = precoTotal;
  }

  public int getId() {
    return id;
  }

  public String getNomeCliente() {
    return nomeCliente;
  }

  public void setNomeCliente(String nomeCliente) {
    this.nomeCliente = nomeCliente;
  }

  public String getProduto() {
    return produto;
  }

  public void setProduto(String produto) {
    this.produto = produto;
  }

  public int getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

  public double getPrecoTotal() {
    return precoTotal;
  }

  public void setPrecoTotal(double precoTotal) {
    this.precoTotal = precoTotal;
  }

  @Override
  public String toString() {
    return "Pedido{" +
            "nomeCliente='" + nomeCliente + '\'' +
            ", produto='" + produto + '\'' +
            ", quantidade=" + quantidade +
            ", precoTotal=" + precoTotal +
            '}';
  }
}