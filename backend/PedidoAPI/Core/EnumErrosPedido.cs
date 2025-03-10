namespace PedidoAPI.Core
{
  public static class ErroPedido
  {
    private static readonly Dictionary<string, string> Erros = new()
    {
      { "NOME_CLIENTE_VAZIO", "Nome do cliente não pode ser vazio." },
      { "PRODUTO_VAZIO", "Produto não pode ser vazio." },
      { "QUANTIDADE_MAIOR_ZERO", "Quantidade deve ser maior que zero." },
      { "PRECO_TOTAL_MAIOR_ZERO", "Preço total deve ser maior que zero." }
    };

    public static string ObterMensagem(string erro)
    {
      return Erros.ContainsKey(erro) ? Erros[erro] : "Erro desconhecido.";
    }
  }
}