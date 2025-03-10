using PedidoAPI.Core;

namespace PedidoAPI.Repositories {
  public static class PedidoRepository {
    private static readonly List<Pedido> pedidos = new();
    public static void Add(Pedido pedido) => pedidos.Add(pedido);
    public static List<Pedido> GetAll() => pedidos;
    public static void Clear() => pedidos.Clear();
  }
}