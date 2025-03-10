namespace PedidoAPI.DTOs {
  public class PaginatedResultDTO<T>
  {
    public int TotalItens { get; set; }
    public int TotalPaginas { get; set; }
    public int PaginaAtual { get; set; }
    public int TamanhoPagina { get; set; }
    public List<T> Dados { get; set; }
  }
}