using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using Xunit;

using PedidoAPI.Controllers;
using PedidoAPI.DTOs;
using PedidoAPI.Core;
using PedidoAPI.Services;
using PedidoAPI.Repositories;

public class PedidoControllerTests
{
  private readonly PedidoController _pedidoController;

  public PedidoControllerTests()
  {
    _pedidoController = new PedidoController();
  }

  [Fact]
  public void CriarPedido_DeveRetornarOk_QuandoPedidoValido()
  {
    // Arrange
    var pedidoDTO = new PedidoDTO
    {
      NomeCliente = "Maria",
      Produto = "Sofá",
      Quantidade = 1,
      PrecoTotal = 1200.00m
    };

    // Act
    var result = _pedidoController.CriarPedido(pedidoDTO) as OkObjectResult;

    // Assert
    Assert.NotNull(result);
    Assert.Equal(200, result.StatusCode);
  }

  [Fact]
  public void CriarPedido_DeveRetornarBadRequest_QuandoPedidoInvalido()
  {
    // Arrange
    var pedidoDTO = new PedidoDTO
    {
      NomeCliente = "",
      Produto = "",
      Quantidade = -5,
      PrecoTotal = 0
    };

    // Act
    var result = _pedidoController.CriarPedido(pedidoDTO) as BadRequestObjectResult;

    // Assert
    Assert.NotNull(result);
    Assert.Equal(400, result.StatusCode);
  }

  [Fact]
  public void ObterPedidos_DeveRetornarListaDePedidos()
  {
    // Act
    var result = _pedidoController.ObterPedidos() as OkObjectResult;

    // Assert
    Assert.NotNull(result);
    Assert.Equal(200, result.StatusCode);

    var paginatedResult = result.Value as PaginatedResultDTO<Pedido>;
    Assert.NotNull(paginatedResult);

    Assert.IsType<List<Pedido>>(paginatedResult.Dados);
    Assert.NotEmpty(paginatedResult.Dados);
  }
}