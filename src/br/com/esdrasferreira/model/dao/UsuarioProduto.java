package br.com.esdrasferreira.model.dao;

public class UsuarioProduto {

	private int idusuario;
	private String nome;
	private String produto;
	
	
	public int getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	@Override
	public String toString() {
		return String.format("UsuarioProduto [idusuario=%s, nome=%s, produto=%s]", idusuario, nome, produto);
	}
	
	
	
	
}
