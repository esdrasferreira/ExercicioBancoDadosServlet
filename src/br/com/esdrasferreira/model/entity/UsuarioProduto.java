package br.com.esdrasferreira.model.entity;

public class UsuarioProduto {
	private int idusuario;
	private String nome;
	private int idproduto;
	private String produto;

	public UsuarioProduto() {
	}

	public UsuarioProduto(int idusuario, String nome, int idproduto, String produto) {
		super();
		this.idusuario = idusuario;
		this.nome = nome;
		this.idproduto = idproduto;
		this.produto = produto;
	}

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

	public int getIdproduto() {
		return idproduto;
	}

	public void setIdproduto(int idproduto) {
		this.idproduto = idproduto;
	}
}
