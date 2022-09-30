package estoque.dao;

import estoque.modelo.Produto;

import java.util.List;

public interface ProdutoDao {
	public List<Produto> lista();
	public Produto getProduto(Integer id);
}
