using PedidoAPI.Controllers;
using PedidoAPI.DTOs;
using PedidoAPI.Core;
using PedidoAPI.Services;
using PedidoAPI.Repositories;
using System;
using Xunit;

public class PedidoServiceTests
{
  private readonly PedidoService _pedidoService;

  public PedidoServiceTests()
  {
    _pedidoService = new PedidoService();
    PedidoRepository.Clear();
  }

  [Fact]
  public void CriarPedido_DeveAdicionarPedido_QuandoDadosValidos()
  {
    // Arrange
    var pedidoDTO = new PedidoDTO
    {
      NomeCliente = "João",
      Produto = "Mesa",
      Quantidade = 2,
      PrecoTotal = 500.00m
    };

    // Act
    _pedidoService.CriarPedido(pedidoDTO);
    var pedidos = PedidoRepository.GetAll();

    // Assert
    Assert.Single(pedidos);
    Assert.Equal("João", pedidos[0].NomeCliente);
  }

  [Fact]
  public void CriarPedido_DeveLancarExcecao_QuandoNomeInvalido()
  {
    // Arrange
    var pedidoDTO = new PedidoDTO
    {
      NomeCliente = "", // Nome inválido
      Produto = "Cadeira",
      Quantidade = 1,
      PrecoTotal = 100
    };

    // Act & Assert
    var exception = Assert.Throws<Exception>(() => _pedidoService.CriarPedido(pedidoDTO));
    Assert.Equal(ErroPedido.ObterMensagem("NOME_CLIENTE_VAZIO"), exception.Message);
  }

  [Fact]
  public void CriarPedido_DeveLancarExcecao_QuandoProdutoInvalido()
  {
    // Arrange
    var pedidoDTO = new PedidoDTO
    {
      NomeCliente = "José",
      Produto = "", // Produto inválido
      Quantidade = 1,
      PrecoTotal = 100
    };

    // Act & Assert
    var exception = Assert.Throws<Exception>(() => _pedidoService.CriarPedido(pedidoDTO));
    Assert.Equal(ErroPedido.ObterMensagem("PRODUTO_VAZIO"), exception.Message);
  }

  [Fact]
  public void CriarPedido_DeveLancarExcecao_QuandoQuantidadeInvalida()
  {
    // Arrange
    var pedidoDTO = new PedidoDTO
    {
      NomeCliente = "Carlos",
      Produto = "Cadeira",
      Quantidade = -1,  // Quantidade inválida
      PrecoTotal = 100
    };

    // Act & Assert
    var exception = Assert.Throws<Exception>(() => _pedidoService.CriarPedido(pedidoDTO));
    Assert.Equal(ErroPedido.ObterMensagem("QUANTIDADE_MAIOR_ZERO"), exception.Message);
  }

  [Fact]
  public void CriarPedido_DeveLancarExcecao_QuandoPrecoTotalInvalido()
  {
    // Arrange
    var pedidoDTO = new PedidoDTO
    {
      NomeCliente = "Maria",
      Produto = "Cadeira",
      Quantidade = 1,
      PrecoTotal = -100 // Preço inválido
    };

    // Act & Assert
    var exception = Assert.Throws<Exception>(() => _pedidoService.CriarPedido(pedidoDTO));
    Assert.Equal(ErroPedido.ObterMensagem("PRECO_TOTAL_MAIOR_ZERO"), exception.Message);
  }
}