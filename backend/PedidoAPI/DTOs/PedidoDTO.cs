namespace PedidoAPI.DTOs {
  public class PedidoDTO {
    public string NomeCliente { get; set; }
    public string Produto { get; set; }
    public int Quantidade { get; set; }
    public decimal PrecoTotal { get; set; }
  }
}