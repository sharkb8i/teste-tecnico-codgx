namespace PedidoAPI.Core {

  public class Pedido {
    public string NomeCliente { get; set; }
    public string Produto { get; set; }
    public int Quantidade { get; set; }
    public decimal PrecoTotal { get; set; }

    public void Validar()
    {
      if (string.IsNullOrEmpty(NomeCliente))
        throw new Exception(ErroPedido.ObterMensagem("NOME_CLIENTE_VAZIO"));
      if (string.IsNullOrEmpty(Produto))
        throw new Exception(ErroPedido.ObterMensagem("PRODUTO_VAZIO"));
      if (Quantidade <= 0)
        throw new Exception(ErroPedido.ObterMensagem("QUANTIDADE_MAIOR_ZERO"));
      if (PrecoTotal <= 0)
        throw new Exception(ErroPedido.ObterMensagem("PRECO_TOTAL_MAIOR_ZERO"));
    }
  }
}