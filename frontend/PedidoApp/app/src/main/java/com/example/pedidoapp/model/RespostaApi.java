package com.example.pedidoapp.model;

import java.util.List;

public class RespostaApi {
  private String mensagem;
  private String erro;
  private int totalItens;
  private int totalPaginas;
  private int paginaAtual;
  private int tamanhoPagina;
  private List<Pedido> dados;
  public String getMensagem() {
    return mensagem;
  }

  public void setMensagem(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getErro() {
    return erro;
  }

  public void setErro(String erro) {
    this.erro = erro;
  }

  public int getTotalItens() {
    return totalItens;
  }

  public void setTotalItens(int totalItens) {
    this.totalItens = totalItens;
  }

  public int getTotalPaginas() {
    return totalPaginas;
  }

  public void setTotalPaginas(int totalPaginas) {
    this.totalPaginas = totalPaginas;
  }

  public int getPaginaAtual() {
    return paginaAtual;
  }

  public void setPaginaAtual(int paginaAtual) {
    this.paginaAtual = paginaAtual;
  }

  public int getTamanhoPagina() {
    return tamanhoPagina;
  }

  public void setTamanhoPagina(int tamanhoPagina) {
    this.tamanhoPagina = tamanhoPagina;
  }

  public List<Pedido> getDados() {
    return dados;
  }

  public void setDados(List<Pedido> dados) {
    this.dados = dados;
  }
}