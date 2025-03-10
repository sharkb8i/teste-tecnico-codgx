using PedidoAPI.DTOs;
using PedidoAPI.Core;
using PedidoAPI.Repositories;

namespace PedidoAPI.Services {
  public class PedidoService {
    public void CriarPedido(PedidoDTO pedidoDTO) {
      Pedido pedido = new() {
        NomeCliente = pedidoDTO.NomeCliente,
        Produto = pedidoDTO.Produto,
        Quantidade = pedidoDTO.Quantidade,
        PrecoTotal = pedidoDTO.PrecoTotal
      };
      pedido.Validar();
      
      PedidoRepository.Add(pedido);
    }
    public List<Pedido> ObterPedidos() => PedidoRepository.GetAll();
}
}