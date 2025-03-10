using Microsoft.AspNetCore.Mvc;
using PedidoAPI.DTOs;
using PedidoAPI.Core;
using PedidoAPI.Repositories;
using PedidoAPI.Services;

namespace PedidoAPI.Controllers {
  
  [ApiController]
  [Route("api/pedidos")]
  public class PedidoController : ControllerBase {
    private readonly PedidoService _pedidoService = new();
    
    [HttpPost]
    public IActionResult CriarPedido([FromBody] PedidoDTO pedidoDTO) {
      try {
        _pedidoService.CriarPedido(pedidoDTO);
        return Ok(new { mensagem = "Pedido salvo com sucesso!" });
      } catch (System.Exception ex) {
        return BadRequest(new { erro = ex.Message });
      }
    }
    
    [HttpGet]
    public IActionResult ObterPedidos([FromQuery] int pagina = 1, [FromQuery] int tamanhoPagina = 10) {
      var pedidos = PedidoRepository.GetAll();

      var pedidosPaginados = pedidos
        .Skip((pagina - 1) * tamanhoPagina)
        .Take(tamanhoPagina)
        .ToList();
      
      var resultado = new PaginatedResultDTO<Pedido>
      {
        TotalItens = pedidos.Count,
        TotalPaginas = (int)Math.Ceiling((double)pedidos.Count / tamanhoPagina),
        PaginaAtual = pagina,
        TamanhoPagina = tamanhoPagina,
        Dados = pedidosPaginados
      };

      return Ok(resultado);
    }
  }
}